# ---- Build Stage ----
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /courses

# Copy project files
COPY pom.xml .
COPY src ./src

# Build the app (skip tests)
RUN mvn clean package -DskipTests

# ---- Runtime Stage ----
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /courses

# Copy the JAR from build stage
COPY --from=build /courses/target/*.jar courses.jar

# Expose the port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "courses.jar"]
