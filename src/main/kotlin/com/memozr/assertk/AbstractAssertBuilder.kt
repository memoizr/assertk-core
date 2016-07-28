package com.memozr.assertk

import com.memozr.assertk.ObjectStuff.notNull
import org.assertj.core.api.Assertions

enum class ObjectStuff {
    notNull
}

inline fun <reified R : Any> of() = AbstractAssertBuilder.InstanceMatcher<R>()

class AbstractAssertBuilder<T : Any>(other: T?) {
    class InstanceMatcher<R>

    val assertion = Assertions.assertThat(other)

    infix fun isEqualTo(other: T): AbstractAssertBuilder<T> {
        assertion.isEqualTo(other)
        return this
    }

    infix fun isNotEqualTo(other: T): AbstractAssertBuilder<T> {
        assertion.isNotEqualTo(other)
        return this
    }

    @Suppress("UNUSED_PARAMETER")
    inline infix fun <reified R: Any> isInstance(bar: InstanceMatcher<R>) : AbstractAssertBuilder<T> {
        assertion.isInstanceOf(R::class.java)
        return this
    }

    infix fun _is(objectStuff: ObjectStuff?): AbstractAssertBuilder<T> {
        return when (objectStuff) {
            notNull -> {
                assertion.isNotNull()
                this
            }
            else -> {
                assertion.isNull()
                this
            }
        }
    }

    infix fun  describedAs(description: String): AbstractAssertBuilder<T> {
        assertion.`as`(description)
        return this
    }

    infix fun isSuchThat(assertionBlock: AbstractAssertBuilder<T>.(AbstractAssertBuilder<T>) -> Unit): AbstractAssertBuilder<T> {
        assertionBlock(this)
        return this
    }
}