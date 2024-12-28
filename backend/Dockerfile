FROM maven:3.9.9-amazoncorretto-21-alpine AS builder

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:resolve

COPY src ./src

RUN mvn clean package -DskipTests

FROM amazoncorretto:21-alpine

LABEL maintainer=kievluizfg@gmail.com

WORKDIR /app

COPY --from=builder /app/target/CaronasUFRN-0.0.1-SNAPSHOT.jar ./app.jar

ENTRYPOINT ["java", "-jar", "./app.jar", "--spring.profiles.active=prod"]