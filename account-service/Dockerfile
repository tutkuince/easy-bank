# Start with a base image containing Java Runtime
FROM openjdk:17-jdk-slim

# Information around who maintains the image
MAINTAINER tutkuince

# Add the aplication's jar to the image
COPY target/account-service-0.0.1-SNAPSHOT.jar account-service-0.0.1-SNAPSHOT.jar

# Execute the application
ENTRYPOINT["java", "-jar", "account-service-0.0.1-SNAPSHOT.jar"]