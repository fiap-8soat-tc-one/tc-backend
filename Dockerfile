FROM maven:3.8.4-openjdk-17-slim AS build

WORKDIR /app

COPY pom.xml /app/pom.xml

RUN mvn dependency:go-offline

COPY src /app/src

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim AS deploy

WORKDIR /app

COPY --from=build /app/target/*.jar /app/tc-backend-api.jar

ENTRYPOINT ["java", "-jar", "tc-backend-api.jar"]