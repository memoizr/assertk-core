package com.memozr.assertk

import org.assertj.core.api.AbstractObjectAssert
import org.assertj.core.api.Assertions

class ObjectAssert<A : Any> internal constructor(
        actual: A?,
        override val assertion: AbstractObjectAssert<*, A> = Assertions.assertThat(actual)) :
        AbstractAssertBuilder<ObjectAssert<A>, A>(actual, ObjectAssert::class.java)