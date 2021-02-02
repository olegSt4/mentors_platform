# Getting Started with Spring Boot App

This project was bootstrapped with [Spring Boot App](https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#getting-started).

## Available Scripts

In the root project directory, you can run:

### `mvn clean spring-boot:run`

Runs Spring Boot app locally.
Curl command to test app is:
```bash
curl --request GET --include 'http://localhost:8080/actuator/health'
```

### `mvn clean test`

Runs all tests available in application under `src/test` directory.

### `mvn clean package -DskipTests`

Builds the project and packages the resulting WAR file into the `target` directory.
`-DskipTests` property is optional and allows you to do build without running the unit tests during.

## Learn More

You can learn more about Maven build tool, look at [Maven Getting Started Guide](https://maven.apache.org/guides/getting-started/index.html).

To learn Spring, check out the [Spring Framework Documentation](https://docs.spring.io/spring-framework/docs/5.1.2.RELEASE/spring-framework-reference/index.html).