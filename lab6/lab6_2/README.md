<h1>Lab 6_2 - Local Analysis</h1>



Command to run:

```bash
$ mvn verify sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.projectKey=lab6_2 -Dsonar.login=e551c1d6769ee5f5abd8d087f095bcd490fa535b
```

For this exercise I chose a project from previous lab classes. I chose the Euromillions one (Lab1_2).



<h3>Did the project pass the defined quality gate?</h3>

Yes it did!

However, it was not perfect. It presented the following:

* 1 Bug (affecting **reliability**)
* 1 Security Hotspot - 0.0% Reviewed (affecting **Security Review**)

|     **Issue**      |                   **Problem Description**                    |                       **How to Solve**                       |
| :----------------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
|        Bug         | Creating a new `Random` object each time a random value is needed is inefficient and may produce numbers which are not random depending on the JDK. | Creating a single `Random`as a parameter, then store and reuse it. |
| Code Smell (Major) |     Assign to a loop counter from within the loop body.      | Incrementing the loop counter on the definition of the for loop. |
| Code Smell (Major) |       Standard Outputs are being used instead of logs.       |         Use logger to write stuff into the console.          |
| Code Smell (Minor) |    Return type of function is ArrayList instead of List.     |          Change return type to a generic type List.          |
| Code Smell (Minor) |       Type specification on constructor call (<Type>)        |           Using the diamond operator (<>) instead.           |
