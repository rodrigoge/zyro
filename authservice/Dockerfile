FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .
RUN ./mvnw dependency:go-offline

COPY src src
RUN ./mvnw clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/authservice-1.0.0.jar"]
