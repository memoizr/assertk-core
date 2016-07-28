package com.memozr.assertk

val assert: AssertionHook get() = AssertionHook()

class AssertionHook {
    infix fun <T : Any> that(subjectUnderTest: T?) = AbstractAssertBuilder(subjectUnderTest)
    infix fun thatExceptionIsThrownBy(expressionUnderTest: () -> Unit) = ThrowableAssertionBuilder(expressionUnderTest)
}


