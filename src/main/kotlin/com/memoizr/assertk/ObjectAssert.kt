package com.memoizr.assertk

import org.assertj.core.api.AbstractObjectAssert
import org.assertj.core.api.Assertions

infix fun <A: Any> A.isEqualTo(expected: A): ObjectAssert<A> = expect that this isEqualTo expected
infix fun <A: Any> A.isNotEqualTo(expected: A): ObjectAssert<A> = expect that this isNotEqualTo expected
infix fun <A: Any> A.is_(expected: ObjectSelector): ObjectAssert<A> = expect that this _is expected
infix fun <A: Any> A.describedAs(description: String): ObjectAssert<A> = expect that this describedAs description
inline infix fun <reified A: Any> A.isInstance(expected: AbstractAssertBuilder.InstanceMatcher<out A>): ObjectAssert<A> =
        expect.that(this).isInstance(expected)

class ObjectAssert<A : Any> internal constructor(
        actual: A?,
        override val assertion: AbstractObjectAssert<*, A> = Assertions.assertThat(actual)) :
        AbstractAssertBuilder<ObjectAssert<A>, A>(actual, ObjectAssert::class.java)