FROM openjdk:8-jdk-alpine

ENV DB_USERNAME=${MYSQL_USERNAME}
ENV DB_PASSWORD=${MYSQL_PASSWORD}

COPY ./swagger-spring-1.0.0.jar swagger-spring-1.0.0.jar

ENTRYPOINT ["java", "-jar", "/swagger-spring-1.0.0.jar"]
