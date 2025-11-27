# Use a full JDK image that exists
FROM openjdk:17-jdk


# Copy the JAR from the repo root
COPY SmartFarmingMonitor-0.0.1-SNAPSHOT.jar /app.jar

# Expose the default Spring Boot port
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "/app.jar"]
