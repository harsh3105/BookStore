FROM java:8-jdk-alpine
ADD target/BookAppApiUsers-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 2222
ENTRYPOINT ["java", "-jar", "app.jar"]