FROM openjdk:8-jdk-alpine
LABEL maintainer="William Rozo"
ARG JAR_FILE=target/mutant-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
LABEL maintainer="levantar"
EXPOSE 80