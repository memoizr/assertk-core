package com.memoizr.assertk

import com.nhaarman.mockito_kotlin.spy
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.AbstractIterableAssert
import org.assertj.core.api.Assertions
import org.junit.Test

class `Iterable assert test` {
    lateinit var mockAssertion: AbstractIterableAssert<*, in Iterable<Any>, in Any, *>

    @Suppress("CAST_NEVER_SUCCEEDS")
    val _expect = object : AssertionHook {
        override fun <ELEMENT : Any?> that(subjectUnderTest: Iterable<ELEMENT>?): IterableAssert<ELEMENT, Iterable<ELEMENT>> {
            val spy = spy(Assertions.assertThat(subjectUnderTest))
            mockAssertion = spy as AbstractIterableAssert<*, Iterable<Any>, Any, *>
            return IterableAssert(subjectUnderTest, mockAssertion as AbstractIterableAssert<*, Iterable<ELEMENT>, ELEMENT, *>)
        }
    }

    val chained = Any()
    infix fun <ELEMENT : Any, ACTUAL : Iterable<ELEMENT>> IterableAssert<ELEMENT, ACTUAL>.canBe(dummyValue: Any): IterableAssert<ELEMENT, ACTUAL> = this

    val emptyList = emptyList<Any>()

    @Test
    fun isEmpty() {
        _expect that emptyList _is empty canBe chained
        verify(mockAssertion).isEmpty()
    }

    @Test
    fun isNullOrEmpty() {
        _expect that emptyList _is nullOrEmpty canBe chained
        verify(mockAssertion).isNullOrEmpty()
    }

    @Test
    fun isNotEmpty() {
        _expect that listOf(Unit) _is notEmpty canBe chained
        verify(mockAssertion).isNotEmpty()
    }

    @Test
    fun hasSize() {
        _expect that listOf(Unit) hasSize 1
        verify(mockAssertion).hasSize(1)
    }

    @Test
    fun hasSameSizeAs() {
        _expect that listOf(Unit) hasSameSizeAs listOf(Unit) canBe chained
        verify(mockAssertion).hasSameSizeAs(listOf(Unit))
    }

    @Test
    fun contains() {
        _expect that listOf(1, 2, 3, 4) contains listOf(1, 2) canBe chained
        verify(mockAssertion).contains(1, 2)
    }

    @Test
    fun containsOnly() {
        _expect that listOf(1, 2) containsOnly listOf(1, 2) canBe chained
        verify(mockAssertion).containsOnly(1, 2)
    }

    @Test
    fun containsOnlyOnce() {
        _expect that listOf(1, 2) containsOnlyOnce listOf(1, 2) canBe chained
        verify(mockAssertion).containsOnlyOnce(1, 2)
    }

    @Test
    fun containsExactlyInAnyOrder() {
        _expect that listOf(1, 2) containsExactlyInAnyOrder listOf(2, 1) canBe chained
        verify(mockAssertion).containsExactlyInAnyOrder(2, 1)
    }

    @Test
    fun isSubsetOf() {
        _expect that listOf(1, 2) isSubsetOf listOf(2, 1, 3) //canBe chained
        verify(mockAssertion).isSubsetOf(2, 1, 3)
    }
}

