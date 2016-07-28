package com.memozr.assertk

import com.memozr.assertk.ObjectStuff.notNull
import org.junit.Test

class AbstractAssertBuilderTest {
    val nullObject: Any? = null

    @Test
    fun `isEqualTo`() {
        assert that Unit isEqualTo Unit

        assert thatExceptionIsThrownBy {
            assert that Any() isEqualTo Any()
        }
    }

    @Test
    fun `isNotEqualTo`() {
        assert that Any() isNotEqualTo Any()

        assert thatExceptionIsThrownBy {
            assert that Unit isNotEqualTo Unit
        }
    }

    @Test
    fun `is not null`() {
        assert that Any() _is notNull

        assert thatExceptionIsThrownBy {
            assert that nullObject _is notNull
        }
    }

    @Test
    fun `is null`() {
        assert that nullObject _is null

        assert thatExceptionIsThrownBy {
            assert that Any() _is null
        }
    }

    @Test
    fun `is instance of`() {
        val anObject = Any()

        assert that anObject isInstance of<Any>()

        assert thatExceptionIsThrownBy {
            assert that anObject isInstance of<Unit>()
        }
    }

    @Test
    fun `described as describes the object`() {
        assert thatExceptionIsThrownBy {
            assert that Any() describedAs "A labeled object" isInstance of<Unit>()
        } hasMessageContaining "A labeled object"
    }

    @Test
    fun `block assertions`() {
        assert that Any() isSuchThat {
            it _is notNull
            it isInstance of<Any>()
            it isNotEqualTo Unit
            it isNotEqualTo Any()
        }

        assert thatExceptionIsThrownBy {
            assert that Any() isSuchThat {
                it _is null
                it isInstance of<Unit>()
            }
        }
    }
}
