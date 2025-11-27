FROM openjdk:17-jdk-slim
ARG JAR_FILE=build/libs/SmartFarmingMonitor-0.0.1-SNAPSHOT.jar
COPY build/libs/SmartFarmingMonitor-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
