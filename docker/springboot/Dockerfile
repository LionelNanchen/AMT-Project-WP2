FROM openjdk:8-jdk-alpine

ENV DB_USERNAME=root
ENV DB_PASSWORD=root

COPY ./swagger-spring-1.0.0.jar swagger-spring-1.0.0.jar

ENTRYPOINT ["java", "-jar", "/swagger-spring-1.0.0.jar"]
