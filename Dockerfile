# # ---------- Build stage ----------
# FROM maven:3.9-eclipse-temurin-21 AS build
# WORKDIR /app

# COPY pom.xml ./
# RUN --mount=type=cache,target=/root/.m2 mvn -q -B -DskipTests dependency:go-offline

# COPY src ./src
# RUN --mount=type=cache,target=/root/.m2 mvn -q -B -DskipTests package

# # ---------- Run stage ----------
# FROM eclipse-temurin:21-jre
# WORKDIR /app

# # Copia o JAR gerado do stage anterior
# COPY --from=build /app/target/*.jar app.jar

# EXPOSE 8080
# ENTRYPOINT ["java","-jar","/app/app.jar"]

# Etapa 1: Build
FROM maven:3.9-eclipse-temurin-21 AS builder

WORKDIR /app

# Copie o código-fonte para o contêiner
COPY pom.xml .
COPY src ./src

# Execute o build para gerar o JAR
RUN mvn clean package -DskipTests

# Etapa 2: Runtime
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copie o arquivo JAR gerado na etapa anterior
COPY --from=builder /app/target/*.jar app.jar

# Porta usada pela aplicação
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
