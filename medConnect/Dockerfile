FROM maven:3.8.1-openjdk-17 AS build

WORKDIR /medConnect

COPY . .
RUN sleep 60 && mvn package
EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "target/medConnect-0.0.1-SNAPSHOT.jar" ]

