# Etapa 1: Construcción (build)
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copiar pom.xml y descargar dependencias primero (mejor cache)
COPY pom.xml .
COPY microservice-roles/pom.xml ./microservice-roles/pom.xml
RUN mvn dependency:go-offline -B

# Copiar el código fuente completo de los módulos
COPY microservice-roles/src ./microservice-roles/src

# Compilar
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final (runtime)
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app

# Copiar el JAR construido en la etapa anterior
COPY --from=build /app/microservice-roles/target/microservice-roles-0.0.1-SNAPSHOT.jar ./app.jar

# Exponer el puerto de Spring Boot (por defecto 8080)
EXPOSE 8080

# Comando de inicio
ENTRYPOINT ["java", "-jar", "app.jar"]
