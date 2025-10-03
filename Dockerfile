# Etapa de build
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copia el pom.xml y el src (todo el proyecto)
COPY . .

# Compila el proyecto
RUN mvn clean package -DskipTests

# Etapa de ejecución
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copia el JAR que se generó en /target
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
