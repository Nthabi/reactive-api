# Use an official OpenJDK 21 runtime as a parent image
FROM eclipse-temurin:21-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven wrapper and pom.xml files
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Copy the source code
COPY src src

# Build the application
RUN ./mvnw clean package -DskipTests

# Copy the built JAR file to the container
COPY target/*.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
