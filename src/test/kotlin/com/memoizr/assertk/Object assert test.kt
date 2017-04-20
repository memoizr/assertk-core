package com.memoizr.assertk

import com.nhaarman.mockito_kotlin.spy
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.AbstractObjectAssert
import org.assertj.core.api.Assertions
import org.junit.Test

interface FooBars
object A : FooBars
object B

class `Object assert test` {
    val nullObject: Any? = null

    lateinit var mockAssertion: AbstractObjectAssert<*, Any>
    @Suppress("UNCHECKED_CAST")
    val _expect = object : AssertionHook {
        override fun <A: Any> that(subjectUnderTest: A?): ObjectAssert<A> {
            val spy: AbstractObjectAssert<*, A?>? = spy(Assertions.assertThat(subjectUnderTest))
            mockAssertion = spy as AbstractObjectAssert<*, Any>
            return ObjectAssert(subjectUnderTest, mockAssertion) as ObjectAssert<A>
        }
    }

    @Test
    fun isEqualTo() {
        Unit isEqualTo Unit
    }

    @Test
    fun isNotEqualTo() {
        A isNotEqualTo B
    }

    @Test
    fun isInstance() {
        expect that (A as FooBars) isInstance of<A>()
        (A as FooBars) isInstance of<A>()
    }

    @Test
    fun is_() {
        A is_ notNull
    }

    @Test
    fun describedAs() {
        A describedAs "foo"
    }

    @Test
    fun `isEqualTo performs logical equality`() {
        _expect that Unit isEqualTo Unit

        val expected = Any()
        _expect thatThrownBy {
            _expect that Any() isEqualTo expected
        }

        verify(mockAssertion).isEqualTo(expected)
    }

    @Test
    fun `isNotEqualTo performs logical inequality`() {
        _expect that Any() isNotEqualTo Any()

        _expect thatThrownBy {
            _expect that Unit isNotEqualTo Unit
        }

        verify(mockAssertion).isNotEqualTo(Unit)
    }

    @Test
    fun `_is notNull checks whether object is null`() {
        _expect that Any() _is notNull

        _expect thatThrownBy {
            _expect that nullObject _is notNull
        }
        verify(mockAssertion).isNotNull()
    }

    @Test
    fun `_is null checks whether object is not null`() {
        _expect that nullObject _is null

        _expect thatThrownBy {
            _expect that Any() _is null
        }
        verify(mockAssertion).isNull()
    }

    @Test
    fun `isInstance of checks for object instance`() {
        val anObject = Any()

        _expect that anObject isInstance of<Any>()
        verify(mockAssertion).isInstanceOf(Any::class.java)

        _expect thatThrownBy {
            _expect that anObject isInstance of<Unit>()
        }

    }

    @Test
    fun `describedAs describes the object when referring to it in failure messages`() {
        _expect thatThrownBy {
            _expect that Any() describedAs "A labeled object" isInstance of<Unit>()
        } hasMessageContaining "A labeled object"

        verify(mockAssertion).describedAs("A labeled object")
    }

    @Test
    fun `block _expections are supported`() {
        _expect that Any() isSuchThat {
            it _is notNull
            it isInstance of<Any>()
            it isNotEqualTo Unit
            it isNotEqualTo Any()
        }

        assert that Any() isSuchThat {
            it _is notNull
            it isInstance of<Any>()
            it isNotEqualTo Unit
            it isNotEqualTo Any()
        }

        _expect thatThrownBy {
            _expect that Any() isSuchThat {
                it _is null
                it isInstance of<Unit>()
            }
        }
    }
}
