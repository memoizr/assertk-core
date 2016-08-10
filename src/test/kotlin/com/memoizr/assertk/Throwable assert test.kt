package com.memoizr.assertk

import com.nhaarman.mockito_kotlin.spy
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.AbstractThrowableAssert
import org.assertj.core.api.Assertions
import org.junit.Test

class `Throwable assert test` {
    val aThrowable = Throwable()

    private fun aThrowableWithCause() = Throwable("hello", aThrowable)

    lateinit var mockAssertion: AbstractThrowableAssert<*, out Throwable>
    val _expect = object : AssertionHook {
        override fun thatThrownBy(expressionUnderTest: () -> Unit): ThrowableAssertion {
            mockAssertion = spy(Assertions.assertThatThrownBy(expressionUnderTest))
            return ThrowableAssertion(expressionUnderTest, mockAssertion)
        }
    }

    @Test
    fun `fails when no exception is thrown`() {
        Assertions.assertThatThrownBy {
            _expect thatThrownBy { }
        }
    }

    @Test
    fun `passes when exception is thrown`() {
        _expect thatThrownBy { throw Throwable() }
    }

    @Test
    fun `passes when throwable message matches the specified message`() {
        _expect thatThrownBy { throw Throwable("hello") } hasMessage "hello"
        verify(mockAssertion).hasMessage("hello")
    }

    @Test
    fun `fails when throwable message does not match specified mesage`() {
        expect thatThrownBy {
            _expect thatThrownBy { throw Throwable("this") } hasMessage "that"
        }
        verify(mockAssertion).hasMessage("that")
    }

    @Test
    fun `passes when throwable cause and specified cause match`() {
        _expect thatThrownBy { throw aThrowableWithCause() } hasCause aThrowable hasMessage "hello"
        _expect thatThrownBy { throw aThrowableWithCause() } hasCause aThrowable
        verify(mockAssertion).hasCause(aThrowable)
    }

    @Test
    fun `fails when throwable cause and specified cause do not match`() {
        val exception = Exception()
        expect thatThrownBy {
            _expect thatThrownBy { throw aThrowableWithCause() } hasCause exception
        }
        verify(mockAssertion).hasCause(exception)
    }

    @Test
    fun `hasNoCause passes when throwable has no cause`() {
        _expect thatThrownBy { throw aThrowable } has noCause
        verify(mockAssertion).hasNoCause()
    }

    @Test
    fun `hasNoCause fails when throwable has cause`() {
        expect thatThrownBy {
            _expect thatThrownBy { throw aThrowableWithCause() } has noCause
        }
        verify(mockAssertion).hasNoCause()
    }

    @Test
    fun `hasMessageStartingWith checks throwable message starts with specified substring`() {
        _expect thatThrownBy { throw Throwable("exception foo") } hasMessageStartingWith "exc"
        verify(mockAssertion).hasMessageStartingWith("exc")
        expect thatThrownBy {
            _expect thatThrownBy { throw Throwable("excepiton foo") } hasMessageStartingWith "foo"
        }
        verify(mockAssertion).hasMessageStartingWith("foo")
    }

    @Test
    fun `hasMessageEndingWith checks throwable message ends with specified substring`() {
        _expect thatThrownBy { throw Throwable("exception foo") } hasMessageEndingWith "foo"
        verify(mockAssertion).hasMessageEndingWith("foo")
        expect thatThrownBy {
            _expect thatThrownBy { throw Throwable("exception foo") } hasMessageEndingWith "exec"
        }
        verify(mockAssertion).hasMessageEndingWith("exec")
    }

    @Test
    fun `hasMessageContaining checks throwable message ends with specified substring`() {
        _expect thatThrownBy { throw Throwable("exception foo") } hasMessageContaining "foo"
        verify(mockAssertion).hasMessageContaining("foo")

        expect thatThrownBy {
            _expect thatThrownBy { throw Throwable("exception foo") } hasMessageContaining "lololol"
        }
        verify(mockAssertion).hasMessageContaining("lololol")
    }

    @Test
    fun `hasStackTraceContaining checks stacktrace contains specified string`() {
        _expect thatThrownBy { throw aThrowable } hasStackTraceContaining "init"
        verify(mockAssertion).hasStackTraceContaining("init")

        expect thatThrownBy {
            _expect thatThrownBy { throw Throwable("exception foo") } hasStackTraceContaining "not in there!!"
        }
        verify(mockAssertion).hasStackTraceContaining("not in there!!")
    }

    @Test
    fun `hasCauseExactlyInstanceOf checks throwable cause is exactly instance of specified class`() {
        _expect thatThrownBy { throw Throwable(Throwable()) } hasCauseExactlyInstanceOf Throwable::class.java
        verify(mockAssertion).hasCauseExactlyInstanceOf(Throwable::class.java)

        expect thatThrownBy {
            _expect thatThrownBy { throw Throwable(Exception()) } hasCauseExactlyInstanceOf Throwable::class.java
        }
        verify(mockAssertion).hasCauseExactlyInstanceOf(Throwable::class.java)

        expect thatThrownBy {
            _expect thatThrownBy { throw Throwable(Throwable()) } hasCauseExactlyInstanceOf Exception::class.java
        }
        verify(mockAssertion).hasCauseExactlyInstanceOf(Exception::class.java)
    }

    @Test
    fun `block syntax is supported`() {
        _expect thatThrownBy {
            throw Throwable("exception foo", Throwable())
        } and {
            it hasMessage "exception foo"
            it hasCause Throwable()
            it hasCauseExactlyInstanceOf Throwable::class.java
            it hasMessageContaining "foo"
            it hasMessageStartingWith "ex"
            it hasMessageEndingWith "foo"
        }

        _expect thatThrownBy {
            _expect thatThrownBy { throw Throwable("exception foo", Throwable()) } and {
                it hasMessage "blah blah"
            }
        }
    }
}