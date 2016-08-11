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

    typealias IA<ELEMENT> = IterableAssert<ELEMENT, Iterable<ELEMENT>>
    infix fun <T> IA<T>.canBe(dummyValue: Any): IA<T> = this
    val chained = Any()

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
        _expect that listOf(1, 2) isSubsetOf listOf(2, 1, 3) canBe chained
        verify(mockAssertion).isSubsetOf(2, 1, 3)
    }

    @Test
    fun containsSequence() {
        _expect that listOf(1, 2, 3, 4) containsSequence listOf(2, 3) canBe chained
        verify(mockAssertion).containsSequence(2, 3)
    }

    @Test
    fun containsSubsequence() {
        _expect that listOf(1, 2, 3, 4) containsSubsequence listOf(2, 4) canBe chained
        verify(mockAssertion).containsSubsequence(2, 4)
    }

    @Test
    fun doesNotContainAnyElementsOf() {
        _expect that listOf(1, 2, 3, 4) doesNotContainAnyElementsOf listOf(6, 7) canBe chained
        verify(mockAssertion).doesNotContainAnyElementsOf(listOf(6,7))
    }

    @Test
    fun doesNotHaveDuplicates() {
        _expect that listOf(1, 2, 3, 4) doesNotHave duplicates canBe chained
        verify(mockAssertion).doesNotHaveDuplicates()
    }

    @Test
    fun `startsWith list`() {
        _expect that listOf(1, 2, 3, 4) startsWith listOf(1, 2) canBe chained
        verify(mockAssertion).startsWith(1, 2)
    }

    @Test
    fun `startsWith single`() {
        _expect that listOf(1, 2, 3, 4) startsWith 1 canBe chained
        verify(mockAssertion).startsWith(1)
    }

    @Test
    fun `endsWith list`() {
        _expect that listOf(1, 2, 3, 4) endsWith listOf(3, 4) canBe chained
        verify(mockAssertion).endsWith(3, 4)
    }

    @Test
    fun `endsWith single`() {
        _expect that listOf(1, 2, 3, 4) endsWith 4 canBe chained
        verify(mockAssertion).endsWith(4)
    }

    @Test
    fun containsNull() {
        _expect that listOf(1, 2, 3, 4, null) contains null canBe chained
        verify(mockAssertion).containsNull()
    }

    @Test
    fun containsOnlyNotNull() {
        _expect that listOf(1, 2, 3, 4) contains onlyNotNull canBe chained
        verify(mockAssertion).doesNotContainNull()
    }
}
