package com.memozr.assertk

import com.memozr.assertk.CharSequenceAssertTest.*
import org.assertj.core.api.AbstractCharSequenceAssert
import org.assertj.core.api.Assertions.assertThat
import java.util.regex.Pattern

enum class CharSequenceAssertTest {
    nullOrEmpty, empty, notEmpty
}

class CharSequenceAssert internal constructor(
        private val subject: String?,
        override protected val assertion: AbstractCharSequenceAssert<*, String> = assertThat(subject))
: AbstractAssertBuilder<String>(subject) {

    object onlyDigits

    infix fun _is(assertionTest: CharSequenceAssertTest): CharSequenceAssert {
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