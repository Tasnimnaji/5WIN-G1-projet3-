# Use an OpenJDK base image
FROM openjdk:11

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from your project into the container
COPY target/tpAchatProject-1.0.jar /app/tpAchatProject-1.0.jar

# Expose the port your Spring Boot application runs on (change to the actual port)
EXPOSE 8089

# Define the command to run your Spring Boot application
CMD ["java", "-jar", "tpAchatProject-1.0.jar"]