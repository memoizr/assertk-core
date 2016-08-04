package com.memozr.assertk

import com.memozr.assertk.ThrowableAssertionBuilder.noCause
import org.assertj.core.api.Assertions
import org.junit.Test

class `ThrownBy test` {
    val aThrowable = Throwable()

    private fun aThrowableWithCause() = Throwable("hello", aThrowable)

    @Test
    fun `fails when no exception is thrown`() {
        Assertions.assertThatThrownBy {
            assert thatThrownBy { }
        }
    }

    @Test
    fun `passes when exception is thrown`() {
        assert thatThrownBy { throw Throwable() }
    }

    @Test
    fun `passes when throwable message matches the specified message`() {
        assert thatThrownBy { throw Throwable("hello") } hasMessage "hello"
    }

    @Test
    fun `fails when throwable message does not match specified mesage`() {
        assert thatThrownBy {
            assert thatThrownBy { throw Throwable("this") } hasMessage "that"
        }
    }

    @Test
    fun `passes when throwable cause and specified cause match`() {
        assert thatThrownBy { throw aThrowableWithCause() } hasCause aThrowable hasMessage "hello"
        assert thatThrownBy { throw aThrowableWithCause() } hasCause aThrowable
    }

    @Test
    fun `fails when throwable cause and specified cause do not match`() {
        assert thatThrownBy {
            assert thatThrownBy { throw aThrowableWithCause() } hasCause Exception()
        }
    }

    @Test
    fun `hasNoCause passes when throwable has no cause`() {
        assert thatThrownBy { throw aThrowable } has noCause
    }

    @Test
    fun `hasNoCause fails when throwable has cause`() { assert thatThrownBy {
            assert thatThrownBy { throw aThrowableWithCause() } has noCause
        }
    }

    @Test
    fun `hasMessageStartingWith checks throwable message starts with specified substring`() {
        assert thatThrownBy { throw Throwable("excepton foo") } hasMessageStartingWith "exc"
        assert thatThrownBy {
            assert thatThrownBy { throw Throwable("excepton foo") } hasMessageStartingWith "foo"
        }
    }

    @Test
    fun `hasMessageEndingWith checks throwable message ends with specified substring`() {
        assert thatThrownBy { throw Throwable("excepton foo") } hasMessageEndingWith "foo"
        assert thatThrownBy {
            assert thatThrownBy { throw Throwable("excepton foo") } hasMessageEndingWith "exec"
        }
    }

    @Test
    fun `hasMessageContaining checks throwable message ends with specified substring`() {
        assert thatThrownBy { throw Throwable("excepton foo") } hasMessageContaining "foo"
        assert thatThrownBy {
            assert thatThrownBy { throw Throwable("excepton foo") } hasMessageContaining "lololol"
        }
    }

    @Test
    fun `hasStackTraceContaining checks stacktrace contains specified string`() {
        assert thatThrownBy { throw aThrowable } hasStackTraceContaining "init"
        assert thatThrownBy {
            assert thatThrownBy { throw Throwable("excepton foo") } hasStackTraceContaining "not in there!!"
        }
    }

    @Test
    fun `hasCauseExactlyInstanceOf checks throwable cause is exactly instance of specified class`() {
        assert thatThrownBy { throw Throwable(Throwable()) } hasCauseExactlyInstanceOf Throwable::class.java

        assert thatThrownBy {
            assert thatThrownBy { throw Throwable(Exception()) } hasCauseExactlyInstanceOf Throwable::class.java
        }

        assert thatThrownBy {
            assert thatThrownBy { throw Throwable(Throwable()) } hasCauseExactlyInstanceOf Exception::class.java
        }
    }

    @Test
    fun `block syntax is supported`() {
        assert thatThrownBy {
            throw Throwable("exception foo", Throwable())
        } and {
            it hasMessage "exception foo"
            it hasCause Throwable()
            it hasCauseExactlyInstanceOf Throwable::class.java
            it hasMessageContaining "foo"
            it hasMessageStartingWith "ex"
            it hasMessageEndingWith "foo"
        }

        assert thatThrownBy {
            assert thatThrownBy { throw Throwable("exception foo", Throwable()) } and {
                it hasMessage "blah blah"
            }
        }
    }
}