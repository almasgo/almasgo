FROM openjdk:11.0.8-jdk
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /app/almasgo-core.jar
ENV JAVA_OPTS="-Xms2g -Xmx2g"
CMD ["java","-jar","/app/almasgo-core.jar"]