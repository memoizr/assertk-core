package com.memozr.assertk

val assert: AssertionHook get() = RealAssertionHook()
val expect: AssertionHook get() = RealAssertionHook()

interface AssertionHook {
    infix fun <T : Any> that(subjectUnderTest: T?) = ObjectAssert(subjectUnderTest)
    infix fun that(subjectUnderTest: CharSequence?) = CharSequenceAssert(subjectUnderTest)
    infix fun thatThrownBy(expressionUnderTest: () -> Unit) = ThrowableAssertion(expressionUnderTest)
}

class RealAssertionHook : AssertionHook


