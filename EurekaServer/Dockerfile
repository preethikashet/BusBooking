# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set working directory inside the container
WORKDIR /app

# Copy the built jar file into the container
COPY target/EurekaServer-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8762 (Eureka Server port)
EXPOSE 8762

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
