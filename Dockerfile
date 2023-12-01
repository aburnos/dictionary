FROM public.ecr.aws/docker/library/maven:3.8.3-amazoncorretto-17 as build
WORKDIR /code

COPY src src
COPY pom.xml pom.xml

RUN mvn clean package -D skipTests

FROM public.ecr.aws/amazoncorretto/amazoncorretto:17.0.4
COPY --from=build /code/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "/app.jar"]