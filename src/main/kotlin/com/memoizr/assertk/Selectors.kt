package com.memoizr.assertk

sealed class SequenceSelector
object nullOrEmpty : SequenceSelector()
object empty : SequenceSelector()
object notEmpty : SequenceSelector()

object onlyDigits

sealed class ObjectSelector
object notNull : ObjectSelector()

sealed class CauseSelector
object noCause : CauseSelector()

object duplicates

object onlyNotNull

sealed class NumberSelector
object zero : NumberSelector()
object notZero : NumberSelector()
object positive : NumberSelector()
object notPositive : NumberSelector()
object negative : NumberSelector()
object notNegative : NumberSelector()

inline fun <reified R : Any> of() = AbstractAssertBuilder.InstanceMatcher<R>()

