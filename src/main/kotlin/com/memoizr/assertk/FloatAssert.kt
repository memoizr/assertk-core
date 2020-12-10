package com.memoizr.assertk

import org.assertj.core.api.AbstractFloatAssert
import org.assertj.core.api.Assertions

class FloatAssert internal constructor(
        subjectUnderTest: Float?,
        override val assertion: AbstractFloatAssert<*> = Assertions.assertThat(subjectUnderTest)) :
        AbstractAssertBuilder<FloatAssert, Float>(subjectUnderTest, FloatAssert::class.java) {

    infix fun isLessThan(expected: Float): FloatAssert {
        assertion.isLessThan(expected)
        return this
    }

    infix fun isLessThanOrEqualTo(expected: Float): FloatAssert {
        assertion.isLessThanOrEqualTo(expected)
        return this
    }

    infix fun isGreaterThan(expected: Float): FloatAssert {
        assertion.isGreaterThan(expected)
        return this
    }

    infix fun isGreaterThanOrEqualTo(expected: Float): FloatAssert {
        assertion.isGreaterThanOrEqualTo(expected)
        return this
    }

    infix fun isBetween(expected: ClosedRange<Float>): FloatAssert {
        assertion.isBetween(expected.start, expected.endInclusive)
        return this
    }

    infix fun isStrictlyBetween(expected: ClosedRange<Float>): FloatAssert {
        assertion.isStrictlyBetween(expected.start, expected.endInclusive)
        return this
    }

    infix fun isCloseTo(expected: Float): Close {
        return Close(expected, assertion, this)
    }

    infix fun _is(expected: NumberSelector): FloatAssert {
        when (expected) {
            zero -> assertion.isZero()
            notZero -> assertion.isNotZero()
            positive -> assertion.isPositive()
            notPositive -> assertion.isNotPositive()
            negative -> assertion.isNegative()
            notNegative -> assertion.isNotNegative()
        }
        return this
    }

    class Close(private val actual: Float, private val assertion: AbstractFloatAssert<*>, private val assert: FloatAssert) {
        infix fun withinOffset(expected: Float): FloatAssert {
            assertion.isCloseTo(actual, Assertions.within(expected))
            return assert
        }

        infix fun withinPercentage(expected: Number): FloatAssert {
            assertion.isCloseTo(actual, Assertions.withinPercentage(expected.toDouble()))
            return assert
        }
    }
}

infix fun Float.isLessThan(expected: Float): FloatAssert =
        expect that this isLessThan expected

infix fun Float.isLessThanOrEqualTo(expected: Float): FloatAssert =
    expect that this isLessThanOrEqualTo expected

infix fun Float.isGreaterThan(expected: Float): FloatAssert =
        expect that this isGreaterThan expected

infix fun Float.isGreaterThanOrEqualTo(expected: Float): FloatAssert =
        expect that this isGreaterThanOrEqualTo expected

infix fun Float.isBetween(expected: ClosedRange<Float>): FloatAssert =
        expect that this isBetween expected

infix fun Float.isStrictlyBetween(expected: ClosedRange<Float>): FloatAssert =
        expect that this isStrictlyBetween expected

infix fun Float.isCloseTo(expected: Float): FloatAssert.Close =
        expect that this isCloseTo expected

infix fun Float._is(expected: NumberSelector): FloatAssert =
        expect that this _is expected
