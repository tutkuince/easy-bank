# Start with a base image containing Java Runtime
FROM openjdk:17-jdk-slim

# Information around who maintains the image
LABEL maintainer="Tutku Ince <incetutku@gmail.com>"

# The application's jar file - defines the JAR_FILE variable set by dockerfile-maven-plugin
ARG JAR_FILE=target/*.jar

# Add the application's jar to the container
COPY ${JAR_FILE} /app.jar

# Execute the application
ENTRYPOINT ["java", "-jar", "app.jar"]