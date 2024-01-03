FROM openjdk:21-slim

WORKDIR /app

COPY dist/store-api-restful-1.0.0.jar store-api.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "store-api.jar"]