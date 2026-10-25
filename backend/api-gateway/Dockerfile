FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /workspace
COPY backend/api-gateway/pom.xml backend/api-gateway/pom.xml
RUN mvn -f backend/api-gateway/pom.xml dependency:go-offline -B
COPY backend/api-gateway/src backend/api-gateway/src
RUN mvn -f backend/api-gateway/pom.xml package -DskipTests -B

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /workspace/backend/api-gateway/target/api-gateway-*.jar app.jar
EXPOSE 10000
ENV SERVER_PORT=10000
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
