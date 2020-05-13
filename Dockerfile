# Start with a base image containing Java runtime
FROM adoptopenjdk/openjdk11

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=gomawa-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ./target/${JAR_FILE} gomawa-0.0.1-SNAPSHOT.jar

# Run the jar file
ENTRYPOINT ["java","-jar","/gomawa-0.0.1-SNAPSHOT.jar"]