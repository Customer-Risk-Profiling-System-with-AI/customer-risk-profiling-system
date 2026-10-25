FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /workspace
COPY backend/transaction-service/pom.xml backend/transaction-service/pom.xml
RUN mvn -f backend/transaction-service/pom.xml dependency:go-offline -B
COPY backend/transaction-service/src backend/transaction-service/src
RUN mvn -f backend/transaction-service/pom.xml package -DskipTests -B

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /workspace/backend/transaction-service/target/transaction-service-*.jar app.jar
EXPOSE 9091
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
