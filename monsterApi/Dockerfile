FROM maven:3.9.6-amazoncorretto-21 as build
RUN mkdir -p /app
WORKDIR /app
COPY pom.xml /app
COPY src /app/src
RUN mvn -f pom.xml clean package


FROM openjdk:21
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","/app.jar"]
EXPOSE 8080/tcp