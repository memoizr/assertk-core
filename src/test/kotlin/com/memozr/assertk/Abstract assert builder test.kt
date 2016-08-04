package com.memozr.assertk

import com.memozr.assertk.ObjectStuff.notNull
import org.junit.Test

class `Abstract assert builder test` {
    val nullObject: Any? = null

    @Test
    fun `isEqualTo performs logical equality`() {
        assert that Unit isEqualTo Unit

        assert thatThrownBy {
            assert that Any() isEqualTo Any()
        }
    }

    @Test
    fun `isNotEqualTo peforms logical inequality`() {
        assert that Any() isNotEqualTo Any()

        assert thatThrownBy {
            assert that Unit isNotEqualTo Unit
        }
    }

    @Test
    fun `_is notNull checks whether object is null`() {
        assert that Any() _is notNull

        assert thatThrownBy {
            assert that nullObject _is notNull
        }
    }

    @Test
    fun `_is null checks whether object is not null`() {
        assert that nullObject _is null

        assert thatThrownBy {
            assert that Any() _is null
        }
    }

    @Test
    fun `isInstance of checks for object instance`() {
        val anObject = Any()

        assert that anObject isInstance of<Any>()

        assert thatThrownBy {
            assert that anObject isInstance of<Unit>()
        }
    }

    @Test
    fun `describedAs describes the object when referring to it in failure messages`() {
        assert thatThrownBy {
            assert that Any() describedAs "A labeled object" isInstance of<Unit>()
        } hasMessageContaining "A labeled object"
    }

    @Test
    fun `block assertions are supported`() {
        assert that Any() isSuchThat {
            it _is notNull
            it isInstance of<Any>()
            it isNotEqualTo Unit
            it isNotEqualTo Any()
        }

        assert thatThrownBy {
            assert that Any() isSuchThat {
                it _is null
                it isInstance of<Unit>()
            }
        }
    }
}
