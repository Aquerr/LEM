FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY target/lem-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "/app/lem-0.0.1-SNAPSHOT.jar"]