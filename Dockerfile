# Use OpenJDK 17 base image
FROM eclipse-temurin:17-jre-alpine

# Copy jar file to container
COPY target/bankingApplication-0.0.1-SNAPSHOT.jar app.jar

# Run the jar file
ENTRYPOINT ["java","-jar","/app.jar"]
