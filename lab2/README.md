# Lab 2 - Mocking Dependencies (for unit testing)



Unit tests verify the local contract. If some testing object requires collaboration from others we need to “fake” the behaviour of the real object in order to keep isolation. We need to simulate **just enough** logic to get the tests done.

### Glossary

**SuT** - Subject Under Test

**Stubs** - “Mini-Implementation” that provide canned answers to calls made during the test.

**Mocks** - Objects pre-programmed with expectations (specification of the interactions they are expected to receive).

**In-Container Testing** - Containers are activated to enable the testing environment. Requires mechanisms to deploy and execute tests in a container.

### Stubs Approach

Stubs require implementing the same logic as the systems they are replacing.

Stubs are used to replace a full-blown external system, such as file systems, a connection to a server, databases, ...

### Mocking Approach

The mock object is a test double.

Allows the test case to describe the expected calls and allows to check that, during the tests, all calls happened with the right parameters and in the right order.

The mocks can be instructed to return specific values and are easy to simulare an expected scenario.

### Annotations

`@ExtendWith(MockitoExtension.class)` is needed to use Annotations

`@Mock` prepares a mock to substitute the remote service

`@InjectMocks` creates an instance of the Subject under Test using the created mock as a field

`@Spy` is used on Partial Mocking. Real methods are invoked but can still be verified and stubbed.

`@Captor` gets the arguments used in a previous expectation.

### Steps

- Instantiate the mock substitute
- Instantiate the Subject Under Test (SuT) and Inject the mock
- “Teach” the required expectations (prepare the mock, teach it how to behave)
- Execute the test in the SuT

To mock we’ll use the Mockito Framework

[Best Practices when writing tests](https://github.com/mockito/mockito/wiki/How-to-write-good-tests)

