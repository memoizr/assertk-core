package com.memoizr.assertk

import org.assertj.core.api.AbstractIterableAssert
import org.assertj.core.api.Assertions.assertThat

class IterableAssert<ELEMENT : Any?, ACTUAL : Iterable<ELEMENT>> internal constructor(
        subjectUnderTest: ACTUAL?,
        override val assertion: AbstractIterableAssert<*, Iterable<ELEMENT>, ELEMENT, *> = assertThat(subjectUnderTest))// {
: AbstractAssertBuilder<IterableAssert<ELEMENT, ACTUAL>, Iterable<ELEMENT>>(subjectUnderTest, IterableAssert::class.java) {

    typealias SELF = IterableAssert<ELEMENT, ACTUAL>

    infix fun _is(selector: SequenceSelector): SELF {
        when (selector) {
            empty -> assertion.isEmpty()
            nullOrEmpty -> assertion.isNullOrEmpty()
            notEmpty -> assertion.isNotEmpty()
        }
        return this
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> spread(expected: Iterable<T>): Array<T> = expected.toList().toTypedArray<Any?>() as Array<T>

    infix fun hasSize(size: Int): SELF {
        assertion.hasSize(size)
        return myself
    }

    infix fun hasSameSizeAs(expected: ACTUAL): SELF {
        assertion.hasSameSizeAs(expected)
        return myself
    }

    infix fun contains(expected: ACTUAL): SELF {
        assertion.contains(*spread(expected))
        return myself
    }

    infix fun containsOnly(expected: ACTUAL): SELF {
        assertion.containsOnly(*spread(expected))
        return myself
    }

    infix fun containsOnlyOnce(expected: ACTUAL): SELF {
        assertion.containsOnlyOnce(*spread(expected))
        return myself
    }

    infix fun containsExactlyInAnyOrder(expected: ACTUAL): SELF {
        assertion.containsExactlyInAnyOrder(*spread(expected))
        return myself
    }

    infix fun isSubsetOf(expected: ACTUAL): SELF {
        assertion.isSubsetOf(*spread(expected))
        return myself
    }

    infix fun containsSequence(expected: ACTUAL): SELF {
        assertion.containsSequence(*spread(expected))
        return myself
    }

    infix fun containsSubsequence(expected: ACTUAL): SELF {
        assertion.containsSubsequence(*spread(expected))
        return myself
    }

    infix fun doesNotContainAnyElementsOf(expected: ACTUAL): SELF {
        assertion.doesNotContainAnyElementsOf(expected)
        return myself
    }

    infix fun doesNotHave(duplicates: duplicates): SELF {
        assertion.doesNotHaveDuplicates()
        return myself
    }

    infix fun startsWith(expected: ACTUAL): SELF {
        assertion.startsWith(*spread(expected))
        return myself
    }

    infix fun startsWith(firstElement: ELEMENT): SELF {
        assertion.startsWith(firstElement)
        return myself
    }

    infix fun endsWith(expected: ACTUAL): SELF {
        assertion.endsWith(*spread(expected))
        return myself
    }

    infix fun endsWith(firstElement: ELEMENT): SELF {
        assertion.endsWith(firstElement)
        return myself
    }

    infix fun contains(onlyNotNull: onlyNotNull?): SELF {
        if (onlyNotNull == null) assertion.containsNull() else assertion.doesNotContainNull()
        return myself
    }
}
