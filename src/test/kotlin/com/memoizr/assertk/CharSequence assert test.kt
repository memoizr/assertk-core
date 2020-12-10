package com.memoizr.assertk

import org.assertj.core.api.AbstractCharSequenceAssert
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.verify
import java.util.regex.Pattern

class `CharSequence assert test` {
    lateinit var mockAssertion: AbstractCharSequenceAssert<*, out CharSequence>
    val _expect = object : AssertionHook {
        override fun that(subjectUnderTest: CharSequence?): CharSequenceAssert {
            mockAssertion = spy(assertThat(subjectUnderTest))
            return CharSequenceAssert(subjectUnderTest, mockAssertion)
        }
    }

    val chained = ""
    infix fun CharSequenceAssert.canBe(dummyValue: CharSequence) : CharSequenceAssert = this

    @Test
    fun `isNullOrEmpty checks for nullity or emptiness`() {
        val nullString: String? = null
        _expect that "" _is nullOrEmpty canBe chained
        _expect that nullString _is nullOrEmpty
        _expect thatThrownBy {
            _expect that "not null or empty" _is nullOrEmpty
            "not null or empty" _is nullOrEmpty
        }
        verify(mockAssertion).isNullOrEmpty()

        "" _is nullOrEmpty canBe chained
    }

    @Test
    fun `isEmpty checks only for emptiness`() {
        _expect that "" _is empty canBe chained
        verify(mockAssertion).isEmpty()

        "" _is empty canBe chained
    }

    @Test
    fun `isNotEmpty checks for non emptiness`() {
        _expect that "not empty" _is notEmpty canBe chained
        verify(mockAssertion).isNotEmpty()

        "not empty" _is notEmpty canBe chained
    }

    @Test
    fun hasSize() {
        _expect that "four" hasSize 4 canBe chained
        verify(mockAssertion).hasSize(4)

        "four" hasSize 4 canBe chained
    }

    @Test
    fun hasLineCount() {
        _expect that "foo \n bar" hasLineCount 2 canBe chained
        verify(mockAssertion).hasLineCount(2)

        "foo \n bar" hasLineCount 2 canBe chained
    }

    @Test
    fun isEqualToIgnoreCase() {
        _expect that "FOO" isEqualToIgnoringCase "foo" canBe chained
        verify(mockAssertion).isEqualToIgnoringCase("foo")

       "FOO" isEqualToIgnoringCase "foo" canBe chained
    }

    @Test
    fun isNotEqualToIgnoringCase() {
        _expect that "FOO" isNotEqualToIgnoringCase "bar" canBe chained
        verify(mockAssertion).isNotEqualToIgnoringCase("bar")

        "FOO" isNotEqualToIgnoringCase "bar" canBe chained
    }

    @Test
    fun containsOnlyDigits() {
        _expect that "1234" contains onlyDigits canBe chained
        verify(mockAssertion).containsOnlyDigits()

        "1234" contains onlyDigits canBe chained
    }

    @Test
    fun containsOnlyOnce() {
        _expect that "foo bar" containsOnlyOnce "foo" canBe chained
        verify(mockAssertion).containsOnlyOnce("foo")

        "foo bar" containsOnlyOnce "foo" canBe chained
    }

    @Test
    fun `contains CharSequence`() {
        _expect that "foo" contains "oo" canBe chained
        verify(mockAssertion).contains("oo")

       "foo" contains "oo" canBe chained
    }

    @Test
    fun `contains iterable`() {
        _expect that "foo bar" contains listOf("ar", "oo") canBe chained
        verify(mockAssertion).contains(listOf("ar", "oo"))

        "foo bar" contains listOf("ar", "oo") canBe chained
    }

    @Test
    fun `containsSequence CharSequence`() {
        _expect that "foo bar" containsSequence listOf("foo", " ", "bar") canBe chained
        verify(mockAssertion).containsSequence(listOf("foo", " ", "bar"))

        "foo bar" containsSequence listOf("foo", " ", "bar") canBe chained
    }

    @Test
    fun containsIgnoringCase() {
        _expect that "foo bar" containsIgnoringCase "FOO" canBe chained
        verify(mockAssertion).containsIgnoringCase("FOO")

        "foo bar" containsIgnoringCase "FOO" canBe chained
    }

    @Test
    fun doesNotContain() {
        _expect that "foo" doesNotContain "bar" canBe chained
        verify(mockAssertion).doesNotContain("bar")

        "foo" doesNotContain "bar" canBe chained
    }

    @Test
    fun startsWith() {
        _expect that "foo" startsWith "fo" canBe chained
        verify(mockAssertion).startsWith("fo")

        "foo" startsWith "fo" canBe chained
    }

    @Test
    fun doesNotStartWith() {
        _expect that "foo" doesNotStartWith "oo" canBe chained
        verify(mockAssertion).doesNotStartWith("oo")

        "foo" doesNotStartWith "oo" canBe chained
    }

    @Test
    fun endsWith() {
        _expect that "foo" endsWith "oo" canBe chained
        verify(mockAssertion).endsWith("oo")

        "foo" endsWith "oo" canBe chained
    }

    @Test
    fun doesNotEndWith() {
        _expect that "foo" doesNotEndWith "fo" canBe chained
        verify(mockAssertion).doesNotEndWith("fo")

        "foo" doesNotEndWith "fo" canBe chained
    }

    @Test
    fun `matches Pattern`() {
        val expected = Pattern.compile("f\\w*\\sb.*")
        _expect that "foo bar" matches expected canBe chained
        verify(mockAssertion).matches(expected)

        "foo bar" matches expected canBe chained
    }

    @Test
    fun `doesNotMatch Pattern`() {
        val expected = Pattern.compile("f\\w*\\sf.*")
        _expect that "foo bar" doesNotMatch expected canBe chained
        verify(mockAssertion).doesNotMatch(expected)

        "foo bar" doesNotMatch expected canBe chained
    }

    @Test
    fun `matches CharSequence`() {
        val expected = "f\\w*\\sb.*"
        _expect that "foo bar" matches expected canBe chained
        verify(mockAssertion).matches(expected)

        "foo bar" matches expected canBe chained
    }

    @Test
    fun `doesNotMatch CharSequence`() {
        val expected = "f\\w*\\sf.*"
        _expect that "foo bar" doesNotMatch expected canBe chained
        verify(mockAssertion).doesNotMatch(expected)

        "foo bar" doesNotMatch expected canBe chained
    }

    @Test
    fun isEqualToIgnoringWhitespace() {
        _expect that "foobar" isEqualToIgnoringWhitespace "foobar " canBe chained
        verify(mockAssertion).isEqualToIgnoringWhitespace("foobar ")

        "foobar" isEqualToIgnoringWhitespace "foobar " canBe chained
    }

    @Test
    fun isNotEqualToIgnoringWhitespace() {
        _expect that "foobar" isNotEqualToIgnoringWhitespace "bar " canBe chained
        verify(mockAssertion).isNotEqualToIgnoringWhitespace("bar ")

        "foobar" isNotEqualToIgnoringWhitespace "bar " canBe chained
    }

    @Test
    fun isSubstringOf() {
        val expected = "foobar"
        _expect that "foo" isSubstringOf expected canBe chained
        verify(mockAssertion).isSubstringOf(expected)

        "foo" isSubstringOf expected canBe chained
    }

    @Test
    fun `containsPattern CharSequence`() {
        val pattern = "f\\w"
        _expect that "foo bar" containsPattern pattern canBe chained
        verify(mockAssertion).containsPattern(pattern)

        "foo bar" containsPattern pattern canBe chained
    }

    @Test
    fun `containsPattern Pattern`() {
        val pattern = Pattern.compile("f\\w")
        _expect that "foo bar" containsPattern pattern canBe chained
        verify(mockAssertion).containsPattern(pattern)

        "foo bar" containsPattern pattern canBe chained
    }

    @Test
    fun `Block syntax is supported`() {
        _expect that "foo" isSuchThat {
            it contains "foo"
            it isSubstringOf "fooBar"
        }

        expect that "foo" isSuchThat {
            it contains "foo"
            it isSubstringOf "fooBar"
        }
    }
}