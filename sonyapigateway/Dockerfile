FROM openjdk:17-jdk-alpine

WORKDIR /app

# Copy the built jar file into the container
COPY target/paymentservice-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8084 (vendorservice port)
EXPOSE 8082

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
