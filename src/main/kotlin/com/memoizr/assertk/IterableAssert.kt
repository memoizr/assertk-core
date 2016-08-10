package com.memoizr.assertk

import org.assertj.core.api.AbstractIterableAssert
import org.assertj.core.api.Assertions.assertThat

class IterableAssert<ELEMENT : Any?, ACTUAL : Iterable<ELEMENT>>(
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

    private fun <T> arrayOfAnys(expected: Iterable<T>): Array<T> = expected.toList().toTypedArray<Any?>() as Array<T>

    infix fun hasSize(size: Int): SELF {
        assertion.hasSize(size)
        return myself
    }

    infix fun hasSameSizeAs(expected: ACTUAL): SELF {
        assertion.hasSameSizeAs(expected)
        return myself
    }

    infix fun contains(expected: ACTUAL): SELF {
        assertion.contains(*arrayOfAnys(expected))
        return myself
    }

    infix fun containsOnly(expected: ACTUAL): SELF {
        assertion.containsOnly(*arrayOfAnys(expected))
        return myself
    }

    infix fun containsOnlyOnce(expected: ACTUAL): SELF {
        assertion.containsOnlyOnce(*arrayOfAnys(expected))
        return myself
    }

    infix fun containsExactlyInAnyOrder(expected: ACTUAL): SELF {
        assertion.containsExactlyInAnyOrder(*arrayOfAnys(expected))
        return myself
    }

    infix fun isSubsetOf(expected: Iterable<ELEMENT>): SELF {
        assertion.isSubsetOf(*arrayOfAnys(expected))
        return myself
    }
}
