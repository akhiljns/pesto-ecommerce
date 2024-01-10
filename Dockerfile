# Use lightweight Linux image with OpenJDK 11
FROM adoptopenjdk:11-jre-hotspot

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged JAR file into the container
COPY target/ecommerce-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port that the application will run on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]
