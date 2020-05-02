package com.memoizr.assertk

import org.assertj.core.api.AbstractIterableAssert
import org.assertj.core.api.Assertions.assertThat


class IterableAssert<ELEMENT : Any?, ACTUAL : Iterable<ELEMENT>> internal constructor(
        subjectUnderTest: ACTUAL?,
        override val assertion: AbstractIterableAssert<*, Iterable<ELEMENT>, ELEMENT, *> = assertThat(subjectUnderTest))// {
: AbstractAssertBuilder<IterableAssert<ELEMENT, ACTUAL>, Iterable<ELEMENT>>(subjectUnderTest, IterableAssert::class.java) {

    infix fun _is(selector: SequenceSelector): IterableAssert<ELEMENT, ACTUAL> {
        when (selector) {
            empty -> assertion.isEmpty()
            nullOrEmpty -> assertion.isNullOrEmpty()
            notEmpty -> assertion.isNotEmpty()
        }
        return this
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> spread(expected: Iterable<T>): Array<T> = expected.toList().toTypedArray<Any?>() as Array<T>

    infix fun hasSize(size: Int): IterableAssert<ELEMENT, ACTUAL> {
        assertion.hasSize(size)
        return myself
    }

    infix fun hasSameSizeAs(expected: ACTUAL): IterableAssert<ELEMENT, ACTUAL> {
        assertion.hasSameSizeAs(expected)
        return myself
    }

    infix fun contains(expected: ACTUAL): IterableAssert<ELEMENT, ACTUAL> {
        assertion.contains(*spread(expected))
        return myself
    }

    infix fun containsOnly(expected: ACTUAL): IterableAssert<ELEMENT, ACTUAL> {
        assertion.containsOnly(*spread(expected))
        return myself
    }

    infix fun containsOnlyOnce(expected: ACTUAL): IterableAssert<ELEMENT, ACTUAL> {
        assertion.containsOnlyOnce(*spread(expected))
        return myself
    }

    infix fun containsExactlyInAnyOrder(expected: ACTUAL): IterableAssert<ELEMENT, ACTUAL> {
        assertion.containsExactlyInAnyOrder(*spread(expected))
        return myself
    }

    infix fun isSubsetOf(expected: ACTUAL): IterableAssert<ELEMENT, ACTUAL> {
        assertion.isSubsetOf(*spread(expected))
        return myself
    }

    infix fun containsSequence(expected: ACTUAL): IterableAssert<ELEMENT, ACTUAL> {
        assertion.containsSequence(*spread(expected))
        return myself
    }

    infix fun containsSubsequence(expected: ACTUAL): IterableAssert<ELEMENT, ACTUAL> {
        assertion.containsSubsequence(*spread(expected))
        return myself
    }

    infix fun doesNotContainAnyElementsOf(expected: ACTUAL): IterableAssert<ELEMENT, ACTUAL> {
        assertion.doesNotContainAnyElementsOf(expected)
        return myself
    }

    infix fun doesNotHave(duplicates: duplicates): IterableAssert<ELEMENT, ACTUAL> {
        assertion.doesNotHaveDuplicates()
        return myself
    }

    infix fun startsWith(expected: ACTUAL): IterableAssert<ELEMENT, ACTUAL> {
        assertion.startsWith(*spread(expected))
        return myself
    }

    infix fun startsWith(firstElement: ELEMENT): IterableAssert<ELEMENT, ACTUAL> {
        assertion.startsWith(firstElement)
        return myself
    }

    infix fun endsWith(expected: Iterable<ELEMENT>): IterableAssert<ELEMENT, ACTUAL> {
        assertion.endsWith(spread(expected))
        return myself
    }

    infix fun endsWith(firstElement: ELEMENT): IterableAssert<ELEMENT, ACTUAL> {
        assertion.endsWith(firstElement)
        return myself
    }

    infix fun contains(onlyNotNull: onlyNotNull?): IterableAssert<ELEMENT, ACTUAL> {
        if (onlyNotNull == null) assertion.containsNull() else assertion.doesNotContainNull()
        return myself
    }
}
