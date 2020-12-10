package com.memoizr.assertk

import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.verify
import org.assertj.core.api.AbstractBooleanAssert
import org.assertj.core.api.Assertions
import org.junit.Test


class `BooleanAssert test` {
    lateinit var mockAssertion: AbstractBooleanAssert<*>
    @Suppress("UNCHECKED_CAST")
    val _expect = object : AssertionHook {
        override fun that(subjectUnderTest: Boolean?): BooleanAssert {
            val spy: AbstractBooleanAssert<*> = spy(Assertions.assertThat(subjectUnderTest))
            mockAssertion = spy
            return BooleanAssert(subjectUnderTest, mockAssertion)
        }
    }

    val chained = Any()
    infix fun BooleanAssert.andCanBe(chained: Any) = this

    @Test
    fun isTrue() {
        _expect that true _is true andCanBe chained
        verify(mockAssertion).isTrue()
        verify(mockAssertion, never()).isFalse()
    }

    @Test
    fun isFalse() {
        _expect that false _is false andCanBe chained
        verify(mockAssertion).isFalse()
        verify(mockAssertion, never()).isTrue()
    }
}
