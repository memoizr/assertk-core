package com.memoizr.assertk

import org.assertj.core.api.AbstractDoubleAssert
import org.assertj.core.api.Assertions

class DoubleAssert(
        subjectUnderTest: Double?,
        override val assertion: AbstractDoubleAssert<*> = Assertions.assertThat(subjectUnderTest)) :
        AbstractAssertBuilder<DoubleAssert, Double>(subjectUnderTest, DoubleAssert::class.java) {

    infix fun isLessThan(expected: Double): DoubleAssert {
        assertion.isLessThan(expected)
        return this
    }

    infix fun isLessThanOrEqualTo(expected: Double): DoubleAssert {
        assertion.isLessThanOrEqualTo(expected)
        return this
    }

    infix fun isGreaterThan(expected: Double): DoubleAssert {
        assertion.isGreaterThan(expected)
        return this
    }

    infix fun isGreaterThanOrEqualTo(expected: Double): DoubleAssert {
        assertion.isGreaterThanOrEqualTo(expected)
        return this
    }

    infix fun isBetween(expected: ClosedRange<Double>): DoubleAssert {
        assertion.isBetween(expected.start, expected.endInclusive)
        return this
    }

    infix fun isStrictlyBetween(expected: ClosedRange<Double>): DoubleAssert {
        assertion.isStrictlyBetween(expected.start, expected.endInclusive)
        return this
    }

    infix fun isCloseTo(expected: Double): Close {
        return Close(expected, assertion, this)
    }

    infix fun _is(expected: NumberSelector): DoubleAssert {
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

    class Close(private val actual: Double, private val assertion: AbstractDoubleAssert<*>, private val assert: DoubleAssert) {
        infix fun withinOffset(expected: Double): DoubleAssert {
            assertion.isCloseTo(actual, Assertions.within(expected))
            return assert
        }

        infix fun withinPercentage(expected: Number): DoubleAssert {
            assertion.isCloseTo(actual, Assertions.withinPercentage(expected.toDouble()))
            return assert
        }
    }
}