package com.memozr.assertk

val assert: AssertionHook get() = RealAssertionHook()
val expect: AssertionHook get() = RealAssertionHook()

interface AssertionHook {
    infix fun <T : Any> that(subjectUnderTest: T?) = AbstractAssertBuilder(subjectUnderTest)
    infix fun that(subjectUnderTest: String?) = CharSequenceAssert(subjectUnderTest)
    infix fun thatThrownBy(expressionUnderTest: () -> Unit) = ThrowableAssertionBuilder(expressionUnderTest)
}

class RealAssertionHook : AssertionHook


