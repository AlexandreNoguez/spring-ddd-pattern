# ---------- Build stage ----------
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copia pom e resolve dependências (cache eficiente)
COPY pom.xml ./
RUN mvn -q -B -DskipTests dependency:go-offline

# Copia o código e compila
COPY src ./src
RUN mvn -q -B -DskipTests package

# ---------- Run stage ----------
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copia o JAR gerado do stage anterior
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
