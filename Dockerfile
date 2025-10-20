FROM eclipse-temurin:21-jdk-jammy AS builder
WORKDIR /opt/app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY mvnw [pom.xml](http://_vscodecontentref_/3) ./
RUN chmod +x mvnw 
COPY ./src ./src
RUN ./mvnw clean install -DskipTests

FROM eclipse-temurin:21-jre-jammy
WORKDIR /opt/app
COPY --from=builder /opt/app/target/*.jar app.jar
COPY .env .env
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]