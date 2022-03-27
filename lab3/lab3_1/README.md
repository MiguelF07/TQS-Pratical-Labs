<h1>Lab 3 - Multi-layer application testing (with Spring Boot)</h1>



<h2>Answering the Review Questions</h2>

**A) Identify a couple of examples on the use of AssertJ expressive methods chaining.**

File `A_EmployeeRepositoryTest`:

```java
assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName());
```

File `B_EmployeeService_UnitTest`:

```java
assertThat(allEmployees).hasSize(3).extracting(Employee::getName).contains(alex.getName(), john.getName(), bob.getName());
```

File `E_EmployeeRestControllerTemplateIT`:

```java
assertThat(found).extracting(Employee::getName).containsOnly("bob");
```



**B) Identify an example in which you mock the behavior of the repository (and avoid involving a database).**

It is used on the test file `B_EmployeeService_UnitTest`.
The `EmployeeRepository employeeRepository;` is initialized with the `@Mock` annotation and then, on the setup function, Mockito is used to mock the behaviour of the repository: 

```java
 Mockito.when(employeeRepository.findByName(john.getName())).thenReturn(john);
 Mockito.when(employeeRepository.findByName(alex.getName())).thenReturn(alex);
 ...
```



**C) What is the difference between standard @Mock and @MockBean?**

We can use `@Mock` to create and inject mocked instances without having to call *Mockito.mock* manually. This annotation should only be used on Test classes.

The `@MockBean`one adds mock objects to the Spring Application. It will replace any existing bean of the same type. If none of the same type is defined, a new one will be added.

Useful on integration tests where a specific bean needs to be mocked.



**D) What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?**

The file `application-integrationtest.properties` has the properties to connect to a database and configure persistence.
For example, in the test file `D_EmployeeRestControllerIT`, if instead of the annotation `@AutoConfigureTestDatabase` we had `@TestPropertySource(locations = "application-integrationtest.properties")` we'd be accessing a real database with the `application-integrationtest.properties` file.



