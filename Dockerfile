# Start with an official OpenJDK image as a base
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the jar file from the host into the container
COPY target/demo4-0.0.1-SNAPSHOT.jar my-app.jar

# Expose the port that your application will run on
EXPOSE 8078

# Run the application
ENTRYPOINT ["java", "-jar", "my-app.jar"]
