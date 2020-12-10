package com.memoizr.assertk


import com.nhaarman.mockitokotlin2.capture
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import io.mockk.slot
import io.mockk.spyk
import io.mockk.verify
import org.assertj.core.api.AbstractIterableAssert
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

class `Iterable assert test` {
    lateinit var mockAssertion: AbstractIterableAssert<*, in Iterable<Int>, in Int, *>

    @Suppress("CAST_NEVER_SUCCEEDS", "UNCHECKED_CAST")
    var _expect = object : AssertionHook {
        override fun <ELEMENT : Any?> that(subjectUnderTest: Iterable<ELEMENT>?): IterableAssert<ELEMENT, Iterable<ELEMENT>> {
            val spy = spyk(assertThat(subjectUnderTest))
            mockAssertion = spy as AbstractIterableAssert<*, Iterable<Int>, Int, *>
            return IterableAssert(subjectUnderTest, mockAssertion as AbstractIterableAssert<*, Iterable<ELEMENT>, ELEMENT, *>)
        }
    }

    infix fun <T> IterableAssert<T, Iterable<T>>.canBe(dummyValue: Any): IterableAssert<T, Iterable<T>> = this
    val chained = Any()

    val emptyList = emptyList<Any>()

    @Suppress("UNCHECKED_CAST")
    private fun <T> spread(expected: Iterable<T>): Array<T> = expected.toList().toTypedArray<Any?>() as Array<T>

    @Test
    fun isEmpty() {
        _expect that emptyList _is empty canBe chained
        verify { mockAssertion.isEmpty() }

        emptyList _is empty canBe chained
    }

    @Test
    fun isNullOrEmpty() {
        _expect that emptyList _is nullOrEmpty canBe chained
        verify { mockAssertion.isNullOrEmpty() }

        emptyList _is nullOrEmpty canBe chained
    }

    @Test
    fun isNotEmpty() {
        _expect that listOf(Unit) _is notEmpty canBe chained
        verify { mockAssertion.isNotEmpty() }

        listOf(Unit) _is notEmpty canBe chained
    }

    @Test
    fun hasSize() {
        _expect that listOf(Unit) hasSize 1
        verify { mockAssertion.hasSize(1) }

        listOf(Unit) hasSize 1
    }

    @Test
    fun hasSameSizeAs() {
        _expect that listOf(Unit) hasSameSizeAs listOf(Unit) canBe chained
        verify { mockAssertion.hasSameSizeAs(listOf(Unit)) }

        listOf(Unit) hasSameSizeAs listOf(Unit) canBe chained
    }

    @Test
    fun contains() {
        _expect that listOf(1, 2, 3, 4) contains listOf(1, 2) canBe chained
        verify { mockAssertion.contains(1, 2) }

        listOf(1, 2, 3, 4) contains listOf(1, 2) canBe chained
    }

    @Test
    fun containsOnly() {
        _expect that listOf(1, 2) containsOnly listOf(1, 2) canBe chained
        verify { mockAssertion.containsOnly(1, 2) }

        listOf(1, 2) containsOnly listOf(1, 2) canBe chained
    }

    @Test
    fun containsOnlyOnce() {
        _expect that listOf(1, 2) containsOnlyOnce listOf(1, 2) canBe chained
        verify { mockAssertion.containsOnlyOnce(1, 2) }

        listOf(1, 2) containsOnlyOnce listOf(1, 2) canBe chained
    }

    @Test
    fun containsExactlyInAnyOrder() {
        _expect that listOf(1, 2) containsExactlyInAnyOrder listOf(2, 1) canBe chained
        verify { mockAssertion.containsExactlyInAnyOrder(2, 1) }

        listOf(1, 2) containsExactlyInAnyOrder listOf(2, 1) canBe chained
    }

    @Test
    fun isSubsetOf() {
        _expect that listOf(1, 2) isSubsetOf listOf(2, 1, 3) canBe chained
        verify { mockAssertion.isSubsetOf(2, 1, 3) }

        listOf(1, 2) isSubsetOf listOf(2, 1, 3) canBe chained
    }

    @Test
    fun containsSequence() {
        _expect that listOf(1, 2, 3, 4) containsSequence listOf(2, 3) canBe chained
        verify { mockAssertion.containsSequence(2, 3) }

        listOf(1, 2, 3, 4) containsSequence listOf(2, 3) canBe chained
    }

    @Test
    fun containsSubsequence() {
        _expect that listOf(1, 2, 3, 4) containsSubsequence listOf(2, 4) canBe chained
        verify { mockAssertion.containsSubsequence(2, 4) }

        listOf(1, 2, 3, 4) containsSubsequence listOf(2, 4) canBe chained
    }

    @Test
    fun doesNotContainAnyElementsOf() {
        _expect that listOf(1, 2, 3, 4) doesNotContainAnyElementsOf listOf(6, 7) canBe chained
        verify { mockAssertion.doesNotContainAnyElementsOf(listOf(6, 7)) }

        listOf(1, 2, 3, 4) doesNotContainAnyElementsOf listOf(6, 7) canBe chained
    }

    @Test
    fun doesNotHaveDuplicates() {
        _expect that listOf(1, 2, 3, 4) doesNotHave duplicates canBe chained
        verify { mockAssertion.doesNotHaveDuplicates() }

        listOf(1, 2, 3, 4) doesNotHave duplicates canBe chained
    }

    @Test
    fun `startsWith list`() {
        _expect that listOf(1, 2, 3, 4) startsWith listOf(1, 2) canBe chained
        verify { mockAssertion.startsWith(1, 2) }

        listOf(1, 2, 3, 4) startsWith listOf(1, 2) canBe chained
    }

    @Test
    fun `startsWith single`() {
        _expect that listOf(1, 2, 3, 4) startsWith 1 canBe chained
        verify { mockAssertion.startsWith(1) }

        listOf(1, 2, 3, 4) startsWith 1 canBe chained
    }

    @Test
    fun `endsWith list`() {
        _expect that listOf(1, 2, 3, 4) endsWith listOf(3, 4) canBe chained
        verify { mockAssertion.endsWith(arrayOf(3, 4)) }

        listOf(1, 2, 3, 4) endsWith listOf(3, 4) canBe chained
    }

    @Test
    fun `endsWith list of size 1`() {
        _expect that listOf(1, 2, 3, 4) endsWith listOf(4) canBe chained
        verify { mockAssertion.endsWith(arrayOf(4)) }

        listOf(1, 2, 3, 4) endsWith listOf(4) canBe chained
    }

    @Test
    fun `endsWith an empty list`() {
        _expect that listOf(1, 2, 3, 4) endsWith emptyList() canBe chained
        verify { mockAssertion.endsWith(emptyArray()) }

        listOf(1, 2, 3, 4) endsWith emptyList() canBe chained
    }

    @Test
    fun `endsWith single`() {
        _expect that listOf(1, 2, 3, 4) endsWith 4 canBe chained
        verify { mockAssertion.endsWith(4) }

        listOf(1, 2, 3, 4) endsWith 4 canBe chained
    }

    @Test
    fun containsNull() {
        _expect that listOf(1, 2, 3, 4, null) contains null canBe chained
        verify { mockAssertion.containsNull() }

        listOf(1, 2, 3, 4, null) contains null canBe chained
    }

    @Test
    fun containsOnlyNotNull() {
        _expect that listOf(1, 2, 3, 4) contains onlyNotNull canBe chained
        verify { mockAssertion.doesNotContainNull() }

        listOf(1, 2, 3, 4) contains onlyNotNull canBe chained
    }

    @Test
    fun `block syntax is supported`() {
        _expect that listOf(1, 2, 3, 4) isSuchThat {
            it contains onlyNotNull
            it endsWith 4
            it endsWith listOf(3, 4)
            it startsWith 1
            it startsWith listOf(1, 2)
            it doesNotHave duplicates
            it doesNotContainAnyElementsOf listOf(5, 6)
        }

        assert that listOf(1, 2, 3, 4) isSuchThat {
            it contains onlyNotNull
            it endsWith 4
            it endsWith listOf(3, 4)
            it startsWith 1
            it startsWith listOf(1, 2)
            it doesNotHave duplicates
            it doesNotContainAnyElementsOf listOf(5, 6)
        }
    }
}
