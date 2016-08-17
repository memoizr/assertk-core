package com.memoizr.assertk


val assert: AssertionHook get() = RealAssertionHook()
val expect: AssertionHook get() = RealAssertionHook()

interface AssertionHook {
    infix fun <T : Any> that(subjectUnderTest: T?) = ObjectAssert(subjectUnderTest)
    infix fun that(subjectUnderTest: CharSequence?) = CharSequenceAssert(subjectUnderTest)
    infix fun thatThrownBy(expressionUnderTest: () -> Unit) = ThrowableAssertion(expressionUnderTest)
    infix fun <ELEMENT : Any?> that(subjectUnderTest: Iterable<ELEMENT>?): IterableAssert<ELEMENT, Iterable<ELEMENT>> = IterableAssert(subjectUnderTest)
    infix fun that(subjectUnderTest: Int?) = IntegerAssert(subjectUnderTest)
    infix fun that(subjectUnderTest: Float?) = FloatAssert(subjectUnderTest)
    infix fun that(subjectUnderTest: Double?) = DoubleAssert(subjectUnderTest)
    infix fun that(subjectUnderTest: Long?) = LongAssert(subjectUnderTest)
    infix fun that(subjectUnderTest: Boolean?) = BooleanAssert(subjectUnderTest)
}

class RealAssertionHook : AssertionHook


