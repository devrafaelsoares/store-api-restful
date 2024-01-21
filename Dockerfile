FROM maven:3-openjdk-17 as maven

WORKDIR /app

COPY . .

RUN mvn package -Dmaven.test.skip

FROM openjdk:17

COPY --from=maven /app/target/store-api-restful-1.2.3.jar store-api.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "store-api.jar"]