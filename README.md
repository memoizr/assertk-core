[![Build Status](https://travis-ci.org/memoizr/assertk-core.svg?branch=master)](https://travis-ci.org/memoizr/assertk-core) [![](https://jitpack.io/v/memoizr/assertk-core.svg)](https://jitpack.io/#memoizr/assertk-core) [![GitHub license](https://img.shields.io/github/license/kotlintest/kotlintest.svg)](http://www.apache.org/licenses/LICENSE-2.0.html) [![codecov](https://codecov.io/gh/memoizr/assertk-core/branch/master/graph/badge.svg)](https://codecov.io/gh/memoizr/assertk-core)

# AssertK - Fluent assertions for Kotlin
AssertK provides a Kotlin-friendly syntax for using the amazing AssertJ assertion framework.

This is early stages work and the library is not yet complete. Only a small subset of the AssertJ-core features are already implemented, but those that are, should be ready to be used in your projects. Contributions are welcome with regards to adding functionality to match that of AssertJ.

###Simple assertions
```kotlin
assert that Unit isEqualTo Unit
assert that Any() isNotEqualTo Any()
assert that Any() _is notNull
assert that nullObject _is null
assert that anObject isInstance of<Any>()
assert that Any() describedAs "A labeled object" isInstance of<Unit>()
```

###Chained syntax
```kotlin
assert that Unit isNotEqualTo Any() isEqualTo Unit _is notNull isInstance of<Any>()
```

###Block syntax
```kotlin
assert that Any() isSuchThat {
    it _is notNull
    it isInstance of<Any>()
    it isNotEqualTo Unit
    it isNotEqualTo Any()
}
```

##Assertions on exceptions
###Chained syntax
```kotlin
assert thatExceptionIsThrownBy { failFunction() } hasMessageContaining "foo" hasCause Throwable()
```

###Block syntax
```kotlin
assert thatExceptionIsThrownBy {
    throw Throwable("exception foo", Throwable())
} and {
    it hasMessage "exception foo"
    it hasCause Throwable()
    it hasCauseExactlyInstanceOf Throwable::class.java
    it hasMessageContaining "foo"
    it hasMessageStartingWith "ex"
    it hasMessageEndingWith "foo"
}
```

##Get it
```groovy
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
dependencies {
    ...
    testCompile 'com.github.memoizr:assertk-core:0.0.2-Alpha'
    ...
}
```
####License
Copyright 2016 memoizr

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
