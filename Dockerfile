#########################################################
#                 Build stage
#########################################################
FROM openjdk:11 as build

WORKDIR /workspace

COPY . ./

RUN ./mvnw clean package -DskipTests

#########################################################
#                  Run stage
#########################################################
FROM openjdk:11

WORKDIR /app

COPY --from=build /workspace/target/internship-be2.war /app/internship-be2.war

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=container", "/app/internship-be2.war"]