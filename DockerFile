FROM openjdk:11-jdk
LABEL maintainer="yskkjdh@gmail.com"
ARG JAR_FILE=build/libs/chumaengi-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} chumaengi-docker.jar
ENTRYPOINT ["java","-jar","/chumaengi-docker.jar"]
