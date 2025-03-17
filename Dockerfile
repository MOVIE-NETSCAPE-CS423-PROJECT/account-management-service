FROM eclipse-temurin:23-jre-alpine AS builder
LABEL authors="Jones Omoyibo"


WORKDIR /app

COPY . .

RUN ./mvnw clean package -DskipTests

FROM openjdk:23-jdk-slim

WORKDIR /app

COPY --from=builder /app/target/account-management-service-0.0.1-SNAPSHOT.jar account-management-service.jar

EXPOSE 8085

ENTRYPOINT ["java", "jar", "/app/account-management-service.jar"]