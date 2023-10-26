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
# docker images -> shows all images
# docker inspect image 5efa30aad16f (Image ID) -> get all the details about your Docker image
# docker run -p 8080:8080 tutkuince/accounts:s4 -> we can run a container from our Docker image. -p is port.
    # first:second
    # first port: represents the external port
    # second port: represents the container port

# docker run -d -p 8080:8080 tutkuince/accounts:s4 -> Docker Server run the container behind the scenes in adetached mode.
    # We won't see any logs inside the terminal.
    # We can see a container ID is displayed like this.

# docker ps -> We can see all the existing running containers with the help of Docker
# docker ps -a -> It is going to display all type of containers that right now.

# docker start 5b38b045222e5a1c6c6c933bed7d26563ed2909b708178e90ce0145244800466 (container id)
    # existing container have started with id.