# API First
# TDD your integration tests
# Cloud microservice architecture

## Development flow
1. **Write** your contract @ api/src/test/resources/contracts
2. **Watch** your application will fail tests
3. **Design** the api Specification @ api/src/main/java/resources/
4. **Watch** your application fail compilation due to unattended api
5. **Implement** the code on your controller @ server/src/main/java/
6. **Watch** your tests pass
7. **Enjoy** some coffee

## Pros
 - Free yourself from updating swagger docs on the controller
 - Free yourself from models boilerplate
 - Free yourself from client boilerplate
 - Use stubs to test your application against this microservice without lifting it