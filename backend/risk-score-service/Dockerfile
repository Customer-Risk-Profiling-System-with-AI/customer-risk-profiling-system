FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /workspace
COPY backend/risk-score-service/pom.xml backend/risk-score-service/pom.xml
RUN mvn -f backend/risk-score-service/pom.xml dependency:go-offline -B
COPY backend/risk-score-service/src backend/risk-score-service/src
RUN mvn -f backend/risk-score-service/pom.xml package -DskipTests -B

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /workspace/backend/risk-score-service/target/risk-score-service-*.jar app.jar
EXPOSE 9092
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
