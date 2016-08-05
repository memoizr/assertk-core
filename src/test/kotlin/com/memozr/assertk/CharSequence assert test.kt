package com.memozr.assertk

import com.memozr.assertk.CharSequenceAssert.onlyDigits
import com.memozr.assertk.CharSequenceAssertTest.*
import com.nhaarman.mockito_kotlin.spy
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.AbstractCharSequenceAssert
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
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
        }
        verify(mockAssertion).isNullOrEmpty()
    }

    @Test
    fun `isEmpty checks only for emptiness`() {
        _expect that "" _is empty canBe chained
        verify(mockAssertion).isEmpty()
    }

    @Test
    fun `isNotEmpty checks for non emptiness`() {
        _expect that "not empty" _is notEmpty canBe chained
        verify(mockAssertion).isNotEmpty()
    }

    @Test
    fun hasSize() {
        _expect that "four" hasSize 4 canBe chained
        verify(mockAssertion).hasSize(4)
    }

    @Test
    fun hasLineCount() {
        _expect that "foo \n bar" hasLineCount 2 canBe chained
        verify(mockAssertion).hasLineCount(2)
    }

    @Test
    fun isEqualToIgnoreCase() {
        _expect that "FOO" isEqualToIgnoringCase "foo" canBe chained
        verify(mockAssertion).isEqualToIgnoringCase("foo")
    }

    @Test
    fun isNotEqualToIgnoringCase() {
        _expect that "FOO" isNotEqualToIgnoringCase "bar" canBe chained
        verify(mockAssertion).isNotEqualToIgnoringCase("bar")
    }

    @Test
    fun containsOnlyDigits() {
        _expect that "1234" contains onlyDigits canBe chained
        verify(mockAssertion).containsOnlyDigits()
    }

    @Test
    fun containsOnlyOnce() {
        _expect that "foo bar" containsOnlyOnce "foo" canBe chained
        verify(mockAssertion).containsOnlyOnce("foo")
    }

    @Test
    fun `contains CharSequence`() {
        _expect that "foo" contains "oo" canBe chained
        verify(mockAssertion).contains("oo")
    }

    @Test
    fun `contains iterable`() {
        _expect that "foo bar" contains listOf("ar", "oo") canBe chained
        verify(mockAssertion).contains(listOf("ar", "oo"))
    }

    @Test
    fun `containsSequence CharSequence`() {
        _expect that "foo bar" containsSequence listOf("oo", "ar") canBe chained
        verify(mockAssertion).containsSequence(listOf("oo", "ar"))
    }

    @Test
    fun containsIgnoringCase() {
        _expect that "foo bar" containsIgnoringCase "FOO" canBe chained
        verify(mockAssertion).containsIgnoringCase("FOO")
    }

    @Test
    fun doesNotContain() {
        _expect that "foo" doesNotContain "bar" canBe chained
        verify(mockAssertion).doesNotContain("bar")
    }

    @Test
    fun startsWith() {
        _expect that "foo" startsWith "fo" canBe chained
        verify(mockAssertion).startsWith("fo")
    }

    @Test
    fun doesNotStartWith() {
        _expect that "foo" doesNotStartWith "oo" canBe chained
        verify(mockAssertion).doesNotStartWith("oo")
    }

    @Test
    fun endsWith() {
        _expect that "foo" endsWith "oo" canBe chained
        verify(mockAssertion).endsWith("oo")
    }

    @Test
    fun doesNotEndWith() {
        _expect that "foo" doesNotEndWith "fo" canBe chained
        verify(mockAssertion).doesNotEndWith("fo")
    }

    @Test
    fun `matches Pattern`() {
        val expected = Pattern.compile("f\\w*\\sb.*")
        _expect that "foo bar" matches expected canBe chained
        verify(mockAssertion).matches(expected)
    }

    @Test
    fun `doesNotMatch Pattern`() {
        val expected = Pattern.compile("f\\w*\\sf.*")
        _expect that "foo bar" doesNotMatch expected canBe chained
        verify(mockAssertion).doesNotMatch(expected)
    }

    @Test
    fun `matches CharSequence`() {
        val expected = "f\\w*\\sb.*"
        _expect that "foo bar" matches expected canBe chained
        verify(mockAssertion).matches(expected)
    }

    @Test
    fun `doesNotMatch CharSequence`() {
        val expected = "f\\w*\\sf.*"
        _expect that "foo bar" doesNotMatch expected canBe chained
        verify(mockAssertion).doesNotMatch(expected)
    }

    @Test
    fun isEqualToIgnoringWhitespace() {
        _expect that "foobar" isEqualToIgnoringWhitespace "foobar " canBe chained
        verify(mockAssertion).isEqualToIgnoringWhitespace("foobar ")
    }

    @Test
    fun isNotEqualToIgnoringWhitespace() {
        _expect that "foobar" isNotEqualToIgnoringWhitespace "bar " canBe chained
        verify(mockAssertion).isNotEqualToIgnoringWhitespace("bar ")
    }

    @Test
    fun isSubstringOf() {
        val expected = "foobar"
        _expect that "foo" isSubstringOf expected canBe chained
        verify(mockAssertion).isSubstringOf(expected)
    }

    @Test
    fun `containsPattern CharSequence`() {
        val pattern = "f\\w"
        _expect that "foo bar" containsPattern pattern canBe chained
        verify(mockAssertion).containsPattern(pattern)
    }

    @Test
    fun `containsPattern Pattern`() {
        val pattern = Pattern.compile("f\\w")
        _expect that "foo bar" containsPattern pattern canBe chained
        verify(mockAssertion).containsPattern(pattern)
    }

    @Test
    fun `Block syntax is supported`() {
        _expect that "foo" isSuchThat {
            it contains "foo"
            it isSubstringOf "fooBar"
        }
    }
}