# Use a valid OpenJDK base image
FROM eclipse-temurin:17-jdk-jammy

# Copy the JAR to the container
COPY SmartFarmingMonitor-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
