package com.memoizr.assertk

import org.assertj.core.api.AbstractDoubleAssert
import org.assertj.core.api.Assertions

infix fun Double.isLessThan(expected: Double): DoubleAssert = expect that this isLessThan expected
infix fun Double.isLessThanOrEqualTo(expected: Double): DoubleAssert = expect that this isLessThanOrEqualTo expected
infix fun Double.isGreaterThan(expected: Double): DoubleAssert = expect that this isGreaterThan expected
infix fun Double.isGreaterThanOrEqualTo(expected: Double): DoubleAssert = expect that this isGreaterThanOrEqualTo  expected
infix fun Double.isBetween(expected: ClosedRange<Double>): DoubleAssert = expect that this isBetween expected
infix fun Double.isStrictlyBetween(expected: ClosedRange<Double>): DoubleAssert = expect that this isStrictlyBetween  expected
infix fun Double.isCloseTo(expected: Double): DoubleAssert.Close = expect that this isCloseTo expected
infix fun Double.isEqualTo(expected: Double): DoubleAssert = expect that this isEqualTo expected
infix fun Double.isNotEqualTo(expected: Double): DoubleAssert = expect that this isNotEqualTo expected
infix fun Double.isInstance(expected: AbstractAssertBuilder.InstanceMatcher<Double>): DoubleAssert = expect that this isInstance expected
infix fun Double.is_(expected: NumberSelector): DoubleAssert = expect that this _is expected
infix fun Double.is_(expected: ObjectSelector): DoubleAssert = expect that this _is expected
infix fun Double._is(expected: NumberSelector): DoubleAssert = expect that this _is expected
infix fun Double._is(expected: ObjectSelector): DoubleAssert = expect that this _is expected
infix fun Double.describedAs(description: String): DoubleAssert = expect that this describedAs description

class DoubleAssert internal constructor(
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

    infix fun is_(expected: NumberSelector): DoubleAssert = _is(expected)

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