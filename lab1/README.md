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

<h2>Link with a JUnit CheatSheet</h2>

[JUnit CheatSheet](https://www.jrebel.com/blog/junit-cheat-sheet)



<h2>Code Coverage</h2>

I'll be running a Jacoco analysis to check the coverage of the tests made.
To configure Jacoco I followed [this](https://www.baeldung.com/jacoco) tutorial.

Command to generate a readable report of the code coverage:

```bash
$ mvn clean test jacoco:report
```

The result can be found on `target/site/jacoco/index.html` (print of the result on folder `prints`)

<img width="959" alt="coverage" src="https://user-images.githubusercontent.com/66647922/158036321-f7291ccd-6048-42d4-962d-58207eacf206.png">

