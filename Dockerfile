FROM maven:3.9.6-eclipse-temurin-24 AS build

WORKDIR /app

COPY src ./src

RUN mvn clean package -DskipTests

FROM amazoncorretto:24-alpine-jdk

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
