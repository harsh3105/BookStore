FROM java:8-jdk-alpine
ADD target/BookApp-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8010
ENTRYPOINT ["java", "-jar", "app.jar"]