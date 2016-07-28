package com.memozr.assertk

import com.memozr.assertk.ThrowableAssertionBuilder.noCause
import org.assertj.core.api.Assertions
import org.junit.Test

class ThrowableAssertionTest {
    val aThrowable = Throwable()

    private fun aThrowableWithCause() = Throwable("hello", aThrowable)

    @Test
    fun `fails when no exceptin is thrown`() {
        Assertions.assertThatThrownBy {
            assert thatExceptionIsThrownBy { }
        }
    }

    @Test
    fun `passes when exception is thrown`() {
        assert thatExceptionIsThrownBy { throw Throwable() }
    }

    @Test
    fun `passes when matches the message`() {
        assert thatExceptionIsThrownBy { throw Throwable("hello") } hasMessage "hello"
    }

    @Test
    fun `fails when message does not match`() {
        assert thatExceptionIsThrownBy {
            assert thatExceptionIsThrownBy { throw Throwable("this") } hasMessage "that"
        }
    }

    @Test
    fun `passes when cause matches`() {
        assert thatExceptionIsThrownBy { throw aThrowableWithCause() } hasCause aThrowable hasMessage "hello"
        assert thatExceptionIsThrownBy { throw aThrowableWithCause() } hasCause aThrowable
    }

    @Test
    fun `fails when cause does not match`() {
        assert thatExceptionIsThrownBy {
            assert thatExceptionIsThrownBy { throw aThrowableWithCause() } hasCause Exception()
        }
    }

    @Test
    fun `hasNoCause passes when has no cause`() {
        assert thatExceptionIsThrownBy { throw aThrowable } has noCause
    }

    @Test
    fun `hasNoCause fails when has cause`() {
        assert thatExceptionIsThrownBy {
            assert thatExceptionIsThrownBy { throw aThrowableWithCause() } has noCause
        }
    }

    @Test
    fun `hasMessageStartingWtih message`() {
        assert thatExceptionIsThrownBy { throw Throwable("excepton foo") } hasMessageStartingWith "exc"
        assert thatExceptionIsThrownBy {
            assert thatExceptionIsThrownBy { throw Throwable("excepton foo") } hasMessageStartingWith "foo"
        }
    }

    @Test
    fun `hasMessageEndingWith message`() {
        assert thatExceptionIsThrownBy { throw Throwable("excepton foo") } hasMessageEndingWith "foo"
        assert thatExceptionIsThrownBy {
            assert thatExceptionIsThrownBy { throw Throwable("excepton foo") } hasMessageEndingWith "exec"
        }
    }

    @Test
    fun `hasMessageContaining message`() {
        assert thatExceptionIsThrownBy { throw Throwable("excepton foo") } hasMessageContaining "foo"
        assert thatExceptionIsThrownBy {
            assert thatExceptionIsThrownBy { throw Throwable("excepton foo") } hasMessageContaining "lololol"
        }
    }

    @Test
    fun `hasStackTraceContaining message`() {
        assert thatExceptionIsThrownBy { throw aThrowable } hasStackTraceContaining "init"
        assert thatExceptionIsThrownBy {
            assert thatExceptionIsThrownBy { throw Throwable("excepton foo") } hasStackTraceContaining "not in there!!"
        }
    }

    @Test
    fun `hasCauseExactlyInstanceOf cause`() {
        assert thatExceptionIsThrownBy { throw Throwable(Throwable()) } hasCauseExactlyInstanceOf Throwable::class.java

        assert thatExceptionIsThrownBy {
            assert thatExceptionIsThrownBy { throw Throwable(Exception()) } hasCauseExactlyInstanceOf Throwable::class.java
        }

        assert thatExceptionIsThrownBy {
            assert thatExceptionIsThrownBy { throw Throwable(Throwable()) } hasCauseExactlyInstanceOf Exception::class.java
        }
    }

    @Test
    fun `block also works`() {
        assert thatExceptionIsThrownBy {
            throw Throwable("exception foo", Throwable())
        } and {
            it hasMessage "exception foo"
            it hasCause Throwable()
            it hasCauseExactlyInstanceOf Throwable::class.java
            it hasMessageContaining "foo"
            it hasMessageStartingWith "ex"
            it hasMessageEndingWith "foo"
        }

        assert thatExceptionIsThrownBy {
            assert thatExceptionIsThrownBy { throw Throwable("exception foo", Throwable()) } and {
                it hasMessage "blah blah"
            }
        }
    }
}