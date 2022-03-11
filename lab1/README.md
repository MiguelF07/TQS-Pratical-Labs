# Lab 1 - Unit Testing (with JUnit 5)
1. Unit testing is when you (as a programmer) write test code to verify units of (production) code. A unit is a small, coherent subset of a much larger solution. A true “unit” should not depend of the behavior of other (collaborating) modules.
2. Unit tests help the developers to (i) understand the module contract (what to construct); (ii) document the intended use of a component; (iii) prevent regression errors; (iv) increase confidence in the code.

`assertEquals(expected,result,msg)`

* `msg`is not required. It will only show when the test fails.

* `expected`is the expected result 
* `result`is the actual result given.

To run the tests from the terminal we execute `mvn test`.

In this [link](https://howtodoinjava.com/junit5/junit-5-assertions-examples/) we can find some methods to use in testing. Those are:

* `assertEquals()`
* `assertNotEquals()`
* `assertArrayEquals()`
* `assertIterableEquals()`
* `assertLinesMatch()`
* `assertNotNull()`
* `assertNull()`
* `assertNotSame()`
* `assertSame()`
* `assertTimeout()`
* `assertTimeoutPreemptively()`
* `assertTrue()`
* `assertFalse()`
* `assertThrows()`
* `fail()`





