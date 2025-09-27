FROM maven:latest

LABEL authors="victoria"

WORKDIR /app

COPY pom.xml /app

COPY . /app

RUN mvn package

CMD ["java", "-jar", "target/time_cal.jar"]