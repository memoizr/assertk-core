package com.memoizr.assertk

import org.assertj.core.api.AbstractAssert
import org.assertj.core.api.Assertions

abstract class AbstractAssertBuilder<S : AbstractAssertBuilder<S, A>, A : Any?>(actual: A?, selfType: Class<*>) {
    class InstanceMatcher<R>

    @Suppress("UNCHECKED_CAST", "LeakingThis")
    val myself: S = selfType.cast(this) as S

    open val assertion: AbstractAssert<*, out A?> = Assertions.assertThat(actual)

    infix fun isEqualTo(other: A): S {
        assertion.isEqualTo(other)
        return myself
    }

    infix fun isNotEqualTo(other: A): S {
        assertion.isNotEqualTo(other)
        return myself
    }

    @Suppress("UNUSED_PARAMETER")
    inline infix fun <reified R : Any> isInstance(bar: InstanceMatcher<out R>): S {
        assertion.isInstanceOf(R::class.java)
        return myself
    }

    infix fun _is(objectSelector: ObjectSelector?): S {
        return when (objectSelector) {
            notNull -> {
                assertion.isNotNull()
                myself
            }
            null -> {
                assertion.isNull()
                myself
            }
        }
    }

    infix fun describedAs(description: String): S {
        assertion.`as`(description)
        return myself
    }

    @Suppress("UNCHECKED_CAST")
    infix fun isSuchThat(assertionBlock: S.(S) -> Unit): S {
        (this as S).assertionBlock(this)
        return myself
    }
}
