FROM maven:3.8.1-openjdk-17 AS build

WORKDIR /medConnect

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /API

COPY --from=build /medConnect/target/*.jar ./app.jar

EXPOSE 8080

COPY run.sh run.sh
RUN ["chmod", "u+x", "./run.sh"]
CMD [ "./run.sh" ]


