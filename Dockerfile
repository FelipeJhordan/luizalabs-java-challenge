# Image for build the project
FROM maven:3.8.3-openjdk-17 as maven
# copy pom to get off-line dependencies
COPY ./pom.xml ./pom.xml
# copy your other files
COPY ./src ./src
# build up project
RUN mvn dependency:go-offline package -B
# Create a new light container just with compiled jar
FROM openjdk:17-alpine
COPY --from=maven target/game-log-parser*.jar ./app.jar
ENTRYPOINT ["java","-jar","app.jar"]