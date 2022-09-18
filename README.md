# Swiss Travel Service
Simple tool that would accept 2 locations and return the amount of time required to go between them using Swiss public transport.

## Requirements

For building and running the application you need:

- [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Swiss Travel application on your local machine. One way is to execute the `main` method in the `com.obuciina.swisstravel.SwissTravelApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn swiss-travel:run
```
