package com.memozr.assertk

import org.assertj.core.api.Assertions

class ThrowableAssertionBuilder(func: () -> Unit) {
    private val assertion = Assertions.assertThatThrownBy(func)

    infix fun hasMessage(message: String): ThrowableAssertionBuilder {
        assertion.hasMessage(message)
        return this
    }

    infix fun hasCause(throwable: Throwable): ThrowableAssertionBuilder {
        assertion.hasCause(throwable)
        return this
    }

    infix fun has(noCause: noCause): ThrowableAssertionBuilder {
        assertion.hasNoCause()
        return this
    }

    infix fun hasMessageStartingWith(message: String): ThrowableAssertionBuilder {
        assertion.hasMessageStartingWith(message)
        return this
    }

    infix fun hasMessageEndingWith(message: String): ThrowableAssertionBuilder {
        assertion.hasMessageEndingWith(message)
        return this
    }

    infix fun hasMessageContaining(message: String): ThrowableAssertionBuilder {
        assertion.hasMessageContaining(message)
        return this
    }

    infix fun hasStackTraceContaining(message: String): ThrowableAssertionBuilder {
        assertion.hasStackTraceContaining(message)
        return this
    }

    infix fun hasCauseExactlyInstanceOf(throwable: Class<out Throwable>): ThrowableAssertionBuilder {
        assertion.hasCauseExactlyInstanceOf(throwable)
        return this
    }

    infix fun and(assertions: ThrowableAssertionBuilder.(ThrowableAssertionBuilder) -> Unit) {
        assertions(this)
    }

    object noCause
}