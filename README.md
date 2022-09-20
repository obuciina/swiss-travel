# Swiss Travel Service
Simple tool that would accept 2 locations and return the amount of time required to go between them using Swiss public transport.

## Requirements

For building and running the application you need:

- [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven 3](https://maven.apache.org)

# Building and running locally

## Running the application locally

There are several ways to run a Swiss Travel application on your local machine. One way is to execute the `main` method in the `main.java.com.obuciina.swisstravel.SwissTravelApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Running test

You can find unit test packages in `main.test.java.com.obuciina.swisstravel` and run them separately from classes.

Alternatively you can run all test using Maven: 
```shell
mvn clean test
```

## Running with Docker
```shell
docker run -p 8080:8080 obucina/swiss-travel
```