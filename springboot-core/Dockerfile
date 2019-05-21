FROM openjdk:8-jdk-alpine
COPY target/*.jar app.jar
EXPOSE 80
ENTRYPOINT ["java","-jar","/app.jar","--spring.profiles.active=docker"]