package com.memoizr.assertk

import com.nhaarman.mockito_kotlin.spy
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.AbstractIntegerAssert
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.within
import org.junit.Test

class `Integer assert test` {
    lateinit var mockAssertion: AbstractIntegerAssert<*>
    @Suppress("UNCHECKED_CAST")
    val _expect = object : AssertionHook {
        override fun that(subjectUnderTest: Int?): IntegerAssert {
            val spy: AbstractIntegerAssert<*> = spy(Assertions.assertThat(subjectUnderTest))
            mockAssertion = spy
            return IntegerAssert(subjectUnderTest, mockAssertion)
        }
    }

    val chained = Any()
    infix fun IntegerAssert.andCanBe(chained: Any) = this

    private val verify by lazy { verify(mockAssertion) }

    private val four = 4
    private val three = 3
    private val five = 5
    private val one = 1
    private val negativeOne = -one

    @Test
    fun isLessThan() {
        _expect that three isLessThan four andCanBe chained
        verify.isLessThan(four)
    }

    @Test
    fun isLessThanOrEqualTo() {
        _expect that four isLessThanOrEqualTo four andCanBe chained
        verify.isLessThanOrEqualTo(four)
    }

    @Test
    fun isGreaterThan() {
        _expect that five isGreaterThan four andCanBe chained
        verify.isGreaterThan(four)
    }

    @Test
    fun isGreaterThanOrEqualTo() {
        _expect that four isGreaterThanOrEqualTo four andCanBe chained
        verify.isGreaterThanOrEqualTo(four)
    }

    @Test
    fun isBetween() {
        _expect that four isBetween (three..five) andCanBe chained
        verify.isBetween(three,five)
    }

    @Test
    fun isStrictlyBetween() {
        _expect that four isStrictlyBetween (three..five) andCanBe chained
        verify.isStrictlyBetween(three,five)
    }

    @Test
    fun `isCloseTo within`() {
        _expect that four isCloseTo three withinOffset one andCanBe chained
        verify.isCloseTo(three, within(one))
    }

    @Test
    fun `isCloseTo percentage int`() {
        _expect that three isCloseTo four withinPercentage 25 andCanBe chained
        verify.isCloseTo(four, Assertions.withinPercentage(25))
    }

    @Test
    fun `isCloseTo percentage double`() {
        _expect that three isCloseTo four withinPercentage 25.3 andCanBe chained
        verify.isCloseTo(four, Assertions.withinPercentage(25.3))
    }

    @Test
    fun `isCloseTo percentage float`() {
        _expect that three isCloseTo four withinPercentage 25f andCanBe chained
        verify.isCloseTo(four, Assertions.withinPercentage(25))
    }

    @Test
    fun isZero() {
        val tzero = 0
        _expect that tzero _is zero andCanBe chained
        verify.isZero()
    }

    @Test
    fun isNotZero() {
        _expect that one _is notZero andCanBe chained
        verify.isNotZero()
    }

    @Test
    fun isPositive() {
        _expect that one _is positive andCanBe chained
        verify.isPositive()
    }


    @Test
    fun isNotPositive() {
        _expect that negativeOne _is notPositive andCanBe chained
        verify.isNotPositive()
    }

    @Test
    fun isNegative() {
        _expect that negativeOne _is negative andCanBe chained
        verify.isNegative()
    }

    @Test
    fun isNotNegative() {
        _expect that one _is notNegative andCanBe chained
        verify.isNotNegative()
    }

    @Test
    fun `supports block syntax`() {
        _expect that one isSuchThat {
            it _is positive
            it _is notNegative
            it isBetween (negativeOne..three)
        }

        assert that one isSuchThat {
            it _is positive
            it _is notNegative
            it isBetween (negativeOne..three)
        }
    }
}
