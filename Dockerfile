# ===== Builder =====
FROM eclipse-temurin:21-jdk-jammy AS builder
WORKDIR /opt/app

# Kopiera Maven-wrapper och pom.xml
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN chmod +x mvnw

# Kopiera källkod
COPY ./src ./src

# Bygg projektet
RUN ./mvnw clean install -DskipTests

# ===== Runtime =====
FROM eclipse-temurin:21-jre-jammy
WORKDIR /opt/app

# Kopiera byggd jar från builder
COPY --from=builder /opt/app/target/*.jar app.jar

# EXPOSE port
EXPOSE 8080

# Starta appen med produktionsprofil
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]
