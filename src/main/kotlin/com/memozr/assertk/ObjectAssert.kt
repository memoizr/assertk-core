package com.memozr.assertk

class ObjectAssert<A : Any>(actual: A?) : AbstractAssertBuilder<ObjectAssert<A>, A>(actual, ObjectAssert::class.java)