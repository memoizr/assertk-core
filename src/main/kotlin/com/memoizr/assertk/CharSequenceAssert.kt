package com.memoizr.assertk

import org.assertj.core.api.AbstractCharSequenceAssert
import org.assertj.core.api.Assertions.assertThat
import java.util.regex.Pattern

class CharSequenceAssert internal constructor(
        private val subject: CharSequence?,
        override val assertion: AbstractCharSequenceAssert<*, out CharSequence> = assertThat(subject)) :
        AbstractAssertBuilder<CharSequenceAssert, CharSequence>(subject, CharSequenceAssert::class.java) {

    infix fun _is(assertionTest: SequenceSelector): CharSequenceAssert {
        when (assertionTest) {
            empty -> assertion.isEmpty()
            notEmpty -> assertion.isNotEmpty()
            nullOrEmpty -> assertion.isNullOrEmpty()
        }
        return this
    }

    infix fun hasSize(expectedSize: Int): CharSequenceAssert {
        assertion.hasSize(expectedSize)
        return this
    }

    infix fun hasLineCount(expectedLineCount: Int): CharSequenceAssert {
        assertion.hasLineCount(expectedLineCount)
        return this
    }

    infix fun isEqualToIgnoringCase(expected: CharSequence): CharSequenceAssert {
        assertion.isEqualToIgnoringCase(expected)
        return this
    }

    infix fun isNotEqualToIgnoringCase(expected: CharSequence): CharSequenceAssert {
        assertion.isNotEqualToIgnoringCase(expected)
        return this
    }

    infix fun contains(onlyDigits: onlyDigits): CharSequenceAssert {
        assertion.containsOnlyDigits()
        return this
    }

    infix fun contains(expected: CharSequence): CharSequenceAssert {
        assertion.contains(expected)
        return this
    }

    infix fun containsOnlyOnce(expected: CharSequence): CharSequenceAssert {
        assertion.containsOnlyOnce(expected)
        return this
    }

    infix fun contains(expected: Iterable<CharSequence>): CharSequenceAssert {
        assertion.contains(expected)
        return this
    }

    infix fun containsSequence(expected: Iterable<CharSequence>): CharSequenceAssert {
        assertion.containsSequence(expected)
        return this
    }

    infix fun containsIgnoringCase(expected: CharSequence): CharSequenceAssert {
        assertion.containsIgnoringCase(expected)
        return this
    }

    infix fun doesNotContain(expected: CharSequence): CharSequenceAssert {
        assertion.doesNotContain(expected)
        return this
    }

    infix fun startsWith(expected: CharSequence): CharSequenceAssert {
        assertion.startsWith(expected)
        return this
    }

    infix fun doesNotStartWith(expected: CharSequence): CharSequenceAssert {
        assertion.doesNotStartWith(expected)
        return this
    }

    infix fun endsWith(expected: CharSequence): CharSequenceAssert {
        assertion.endsWith(expected)
        return this
    }

    infix fun doesNotEndWith(expected: CharSequence): CharSequenceAssert {
        assertion.doesNotEndWith(expected)
        return this
    }

    infix fun matches(expected: Pattern): CharSequenceAssert {
        assertion.matches(expected)
        return this
    }

    infix fun doesNotMatch(expected: Pattern): CharSequenceAssert {
        assertion.doesNotMatch(expected)
        return this
    }

    infix fun matches(expected: CharSequence): CharSequenceAssert {
        assertion.matches(expected)
        return this
    }

    infix fun doesNotMatch(expected: CharSequence): CharSequenceAssert {
        assertion.doesNotMatch(expected)
        return this
    }

    infix fun isEqualToIgnoringWhitespace(expected: CharSequence): CharSequenceAssert {
        assertion.isEqualToIgnoringWhitespace(expected)
        return this
    }

    infix fun isNotEqualToIgnoringWhitespace(expected: CharSequence): CharSequenceAssert {
        assertion.isNotEqualToIgnoringWhitespace(expected)
        return this
    }

    infix fun isSubstringOf(expected: CharSequence): CharSequenceAssert {
        assertion.isSubstringOf(expected)
        return this
    }

    infix fun containsPattern(pattern: CharSequence): CharSequenceAssert {
        assertion.containsPattern(pattern)
        return this
    }

    infix fun containsPattern(pattern: Pattern): CharSequenceAssert {
        assertion.containsPattern(pattern)
        return this
    }
}

infix fun CharSequence._is(assertionTest: SequenceSelector): CharSequenceAssert =
        expect that this _is assertionTest

infix fun CharSequence.hasSize(expectedSize: Int): CharSequenceAssert =
        expect that this hasSize expectedSize

infix fun CharSequence.hasLineCount(expectedLineCount: Int): CharSequenceAssert =
        expect that this hasLineCount expectedLineCount

infix fun CharSequence.isEqualToIgnoringCase(expected: CharSequence): CharSequenceAssert =
        expect that this isEqualToIgnoringCase expected

infix fun CharSequence.isNotEqualToIgnoringCase(expected: CharSequence): CharSequenceAssert =
        expect that this isNotEqualToIgnoringCase expected

infix fun CharSequence.contains(onlyDigits: onlyDigits): CharSequenceAssert =
        expect that this contains onlyDigits

infix fun CharSequence.contains(expected: CharSequence): CharSequenceAssert =
        expect that this contains expected

infix fun CharSequence.containsOnlyOnce(expected: CharSequence): CharSequenceAssert =
        expect that this containsOnlyOnce expected

infix fun CharSequence.contains(expected: Iterable<CharSequence>): CharSequenceAssert =
        expect that this contains expected

infix fun CharSequence.containsSequence(expected: Iterable<CharSequence>): CharSequenceAssert =
        expect that this containsSequence expected

infix fun CharSequence.containsIgnoringCase(expected: CharSequence): CharSequenceAssert =
        expect that this containsIgnoringCase expected

infix fun CharSequence.doesNotContain(expected: CharSequence): CharSequenceAssert =
        expect that this doesNotContain expected

infix fun CharSequence.startsWith(expected: CharSequence): CharSequenceAssert =
        expect that this startsWith expected

infix fun CharSequence.doesNotStartWith(expected: CharSequence): CharSequenceAssert =
        expect that this doesNotStartWith expected

infix fun CharSequence.endsWith(expected: CharSequence): CharSequenceAssert =
        expect that this endsWith expected

infix fun CharSequence.doesNotEndWith(expected: CharSequence): CharSequenceAssert =
        expect that this doesNotEndWith expected

infix fun CharSequence.matches(expected: Pattern): CharSequenceAssert =
        expect that this matches expected

infix fun CharSequence.doesNotMatch(expected: Pattern): CharSequenceAssert =
        expect that this doesNotMatch expected

infix fun CharSequence.matches(expected: CharSequence): CharSequenceAssert =
        expect that this matches expected

infix fun CharSequence.doesNotMatch(expected: CharSequence): CharSequenceAssert =
        expect that this doesNotMatch expected

infix fun CharSequence.isEqualToIgnoringWhitespace(expected: CharSequence): CharSequenceAssert =
        expect that this isEqualToIgnoringWhitespace expected

infix fun CharSequence.isNotEqualToIgnoringWhitespace(expected: CharSequence): CharSequenceAssert =
        expect that this isNotEqualToIgnoringWhitespace  expected

infix fun CharSequence.isSubstringOf(expected: CharSequence): CharSequenceAssert =
        expect that this isSubstringOf expected

infix fun CharSequence.containsPattern(pattern: CharSequence): CharSequenceAssert =
        expect that this containsPattern pattern

infix fun CharSequence.containsPattern(pattern: Pattern): CharSequenceAssert =
        expect that this containsPattern pattern
