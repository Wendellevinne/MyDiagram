FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/MyDiagram-0.0.1-SNAPSHOT.jar MyDiagram-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/MyDiagram-0.0.1-SNAPSHOT.jar"]