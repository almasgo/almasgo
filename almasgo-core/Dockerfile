FROM openjdk:11.0.8-jdk
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /app/almasgo-core.jar
CMD ["java","-Xms2g","-Xmx2g","-jar","/app/almasgo-core.jar"]