package com.memoizr.assertk

import org.assertj.core.api.AbstractThrowableAssert
import org.assertj.core.api.Assertions

class ThrowableAssertion internal constructor(
        func: () -> Unit,
        private val assertion: AbstractThrowableAssert<*, out Throwable> = Assertions.assertThatThrownBy(func)) {

    infix fun hasMessage(message: String): ThrowableAssertion {
        assertion.hasMessage(message)
        return this
    }

    infix fun hasCause(throwable: Throwable): ThrowableAssertion {
        assertion.hasCause(throwable)
        return this
    }

    infix fun has(noCause: CauseSelector): ThrowableAssertion {
        assertion.hasNoCause()
        return this
    }

    infix fun hasMessageStartingWith(message: String): ThrowableAssertion {
        assertion.hasMessageStartingWith(message)
        return this
    }

    infix fun hasMessageEndingWith(message: String): ThrowableAssertion {
        assertion.hasMessageEndingWith(message)
        return this
    }

    infix fun hasMessageContaining(message: String): ThrowableAssertion {
        assertion.hasMessageContaining(message)
        return this
    }

    infix fun hasStackTraceContaining(message: String): ThrowableAssertion {
        assertion.hasStackTraceContaining(message)
        return this
    }

    infix fun hasCauseExactlyInstanceOf(throwable: Class<out Throwable>): ThrowableAssertion {
        assertion.hasCauseExactlyInstanceOf(throwable)
        return this
    }

    infix fun and(assertions: ThrowableAssertion.(ThrowableAssertion) -> Unit) {
        assertions(this)
    }

}