FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY target/project-compliance-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/mydatabase
ENV SPRING_DATASOURCE_USERNAME=admin
ENV SPRING_DATASOURCE_PASSWORD=admin

ENTRYPOINT ["java", "-jar", "app.jar"]