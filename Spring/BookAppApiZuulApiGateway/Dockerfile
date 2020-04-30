FROM java:8-jdk-alpine
ADD target/BookAppApiZuulApiGateway-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8011
ENTRYPOINT ["java", "-jar", "app.jar"]