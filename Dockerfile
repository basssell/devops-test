# Use an official OpenJDK runtime as a parent image
FROM openjdk:17

# Set the working directory in the container
WORKDIR /usr/src/app

# Copy the JAR file into the container at /usr/src/app
COPY target/myapp.jar /usr/src/app

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "myapp.jar"]
