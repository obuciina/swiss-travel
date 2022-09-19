FROM openjdk:17-oracle
LABEL maintainer="obuciina"
ADD target/swiss-travel-1.0.0.jar swiss-travel.jar
ENTRYPOINT ["java", "-jar", "swiss-travel.jar"]