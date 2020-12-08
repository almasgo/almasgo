FROM openjdk:11.0.8-jdk
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /app/almasgo-core.jar
CMD ["java","-jar","/app/almasgo-core.jar"]