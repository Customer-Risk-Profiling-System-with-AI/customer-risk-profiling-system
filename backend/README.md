# Backend

Backend API and services for the Customer Risk Profiling System.

## Overview

This folder contains all backend code, APIs, and server logic.

## Spring Boot Backend — Setup Guide

This section explains how to set up and run the Spring Boot backend locally and with Docker.

- **Prerequisites:**
	- Java JDK 17 or later installed and `JAVA_HOME` set.
	- Maven 3.6+ (or Gradle if the project uses Gradle).
	- Git to clone the repository.
	- (Optional) Docker & Docker Compose to run in containers.

- **Clone repository:**

	git clone https://your-repo-url.git
	cd customer-risk-profiling-system/backend

- **Build with Maven:**

	mvn clean package

	- This produces a runnable JAR in `target/` (e.g., `target/backend-0.0.1-SNAPSHOT.jar`).

- **Run locally (development):**

	mvn spring-boot:run

	or run the built JAR:

	java -jar target/backend-0.0.1-SNAPSHOT.jar

- **Environment & config:**
	- Provide application configuration via `src/main/resources/application.properties` or `application.yml`.
	- For secrets and environment-specific values, use environment variables or an external config file. Example environment variables:
		- `SPRING_PROFILES_ACTIVE=dev`
		- `DATABASE_URL`, `DATABASE_USERNAME`, `DATABASE_PASSWORD`

- **Run tests:**

	mvn test

- **Run with Docker (optional):**
	- Add a `Dockerfile` to the backend module (example below) and build an image:

	docker build -t backend:latest .
	docker run -e SPRING_PROFILES_ACTIVE=prod -p 8080:8080 backend:latest

	Example minimal Dockerfile:

	FROM eclipse-temurin:17-jdk-jammy
	ARG JAR_FILE=target/*.jar
	COPY ${JAR_FILE} app.jar
	ENTRYPOINT ["java","-jar","/app.jar"]

- **Common development tasks:**
	- Start with hot-reload while developing: add `spring-boot-devtools` to dependencies and run via your IDE or `mvn spring-boot:run`.
	- Use an IDE (IntelliJ IDEA / Eclipse): open the `backend` module as a Maven/Gradle project and run the main application class.

- **Ports & endpoints:**
	- Default port: `8080` (changeable via `server.port` in application properties).
	- Health check: `/actuator/health` (if Spring Actuator is enabled).

- **Troubleshooting:**
	- If Java version errors occur, ensure `java -version` matches the required JDK and that `JAVA_HOME` is correct.
	- On database connection issues, confirm the JDBC URL and credentials and that the DB is reachable.

- **Deployment:**
	- Create CI pipeline to build artifact (`mvn package`) and publish Docker image to your registry.
	- Use environment variables or secrets in your target environment for credentials and configs.

If you'd like, I can also:
- add a `Dockerfile` to this folder,
- add a `Makefile` / helper scripts for common commands, or
- add sample `application.yml` and `.env` examples.


