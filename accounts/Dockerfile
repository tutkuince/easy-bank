# Start with a base image containing Java Runtime
FROM openjdk:17-jdk-slim

# Information around who maintains the image
MAINTAINER tutkuince.io

# Add the application's jar to image
# Take the present inside my target folder(target/accounts-0.0.1-SNAPSHOT.jar) and copy the same jar into my Docker image(accounts-0.0.1-SNAPSHOT.jar)
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

# execute the application
ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]

# docker build . -t tutkuince/accounts:s4
# doccker images -> shows all images
# docker inspect image 5efa30aad16f (Image ID) -> get all the details about your Docker image