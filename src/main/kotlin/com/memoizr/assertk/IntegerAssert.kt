package com.memoizr.assertk

import org.assertj.core.api.AbstractIntegerAssert
import org.assertj.core.api.Assertions

class IntegerAssert internal constructor(
        subjectUnderTest: Int?,
        override val assertion: AbstractIntegerAssert<*> = Assertions.assertThat(subjectUnderTest)) :
        AbstractAssertBuilder<IntegerAssert, Int>(subjectUnderTest, IntegerAssert::class.java) {

    infix fun isLessThan(expected: Int): IntegerAssert {
        assertion.isLessThan(expected)
        return this
    }

    infix fun isLessThanOrEqualTo(expected: Int): IntegerAssert {
        assertion.isLessThanOrEqualTo(expected)
        return this
    }

    infix fun isGreaterThan(expected: Int): IntegerAssert {
        assertion.isGreaterThan(expected)
        return this
    }

    infix fun isGreaterThanOrEqualTo(expected: Int): IntegerAssert {
        assertion.isGreaterThanOrEqualTo(expected)
        return this
    }

    infix fun isBetween(expected: IntRange): IntegerAssert {
        assertion.isBetween(expected.start, expected.endInclusive)
        return this
    }

    infix fun isStrictlyBetween(expected: IntRange): IntegerAssert {
        assertion.isStrictlyBetween(expected.start, expected.endInclusive)
        return this
    }

    infix fun isCloseTo(expected: Int): Close {
        return Close(expected, assertion, this)
    }

    infix fun _is(expected: NumberSelector): IntegerAssert {
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

    class Close(private val actual: Int, private val assertion: AbstractIntegerAssert<*>, private val assert: IntegerAssert) {
        infix fun withinOffset(expected: Int): IntegerAssert {
            assertion.isCloseTo(actual, Assertions.within(expected))
            return assert
        }

        infix fun withinPercentage(expected: Number): IntegerAssert {
            assertion.isCloseTo(actual, Assertions.withinPercentage(expected.toDouble()))
            return assert
        }
    }
}