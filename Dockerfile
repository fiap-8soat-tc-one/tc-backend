FROM openjdk:17-alpine3.14 as tc-backend
 WORKDIR /app
 COPY target/tc-backend-api-0.0.1-SNAPSHOT.jar /app/tc-backend-api.jar
 ENTRYPOINT ["java", "-jar", "tc-backend-api.jar"]