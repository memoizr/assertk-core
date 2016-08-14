package com.memoizr.assertk

import org.assertj.core.api.AbstractBooleanAssert
import org.assertj.core.api.Assertions

class BooleanAssert internal constructor(
        subjectUnderTest: Boolean?,
        override val assertion: AbstractBooleanAssert<*> = Assertions.assertThat(subjectUnderTest)) :
        AbstractAssertBuilder<BooleanAssert, Boolean>(subjectUnderTest, BooleanAssert::class.java) {

    infix fun _is(other: Boolean): BooleanAssert {
        if (other) assertion.isTrue() else assertion.isFalse()
        return this
    }
}