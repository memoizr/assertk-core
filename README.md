[![Build Status](https://travis-ci.org/memoizr/assertk-core.svg?branch=master)](https://travis-ci.org/memoizr/assertk-core) [![](https://jitpack.io/v/memoizr/assertk-core.svg)](https://jitpack.io/#memoizr/assertk-core) [![GitHub license](https://img.shields.io/github/license/kotlintest/kotlintest.svg)](http://www.apache.org/licenses/LICENSE-2.0.html) [![codecov](https://codecov.io/gh/memoizr/assertk-core/branch/master/graph/badge.svg)](https://codecov.io/gh/memoizr/assertk-core)

# AssertK - Fluent assertions for Kotlin
AssertK provides a Kotlin-friendly syntax for using the amazing AssertJ assertion framework.

###Simple assertions
```kotlin
assert that Unit isEqualTo Unit
```

you can use `assert that` or `expect that` interchangeably as `assert` tends to be a bit noisy given when relying on autocomplete.
```kotlin
expect that Any() isNotEqualTo Any()
expect that Any() _is notNull
expect that nullObject _is null
expect that anObject isInstance of<Any>()
expect that Any() describedAs "A labeled object" isInstance of<Unit>()
```

###Chained syntax
```kotlin
expect that Unit isNotEqualTo Any() isEqualTo Unit _is notNull isInstance of<Any>()
```

###Block syntax
```kotlin
expect that Any() isSuchThat {
    it _is notNull
    it isInstance of<Any>()
    it isNotEqualTo Unit
    it isNotEqualTo Any()
}
```

##Assertions on exceptions
###Chained syntax
```kotlin
expect thatExceptionIsThrownBy { failFunction() } hasMessageContaining "foo" hasCause Throwable()
```

###Block syntax
```kotlin
expect thatExceptionIsThrownBy {
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

#Further Examples
##Iterable Assert
```kotlin
expect that myListOfInts hasSize 10 contains 3 startsWith 1
```
##Boolean Assert
```kotlin
expect that myBoolean _is false
```
##Float, Double, Int, Long Assert
```kotlin
expect that myFloat isGreaterThan otherFloat
expect that myInt _is notPositive isLessThan expectedResult
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
    testCompile 'com.github.memoizr:assertk-core:0.1.0'
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
