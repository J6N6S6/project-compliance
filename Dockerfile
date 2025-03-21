FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/project-compliance-0.0.1-SNAPSHOT.jar /app/compliance.jar
CMD ["java", "-jar", "compliance.jar"]