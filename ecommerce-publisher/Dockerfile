FROM eclipse-temurin:20.0.2_9-jdk-alpine

WORKDIR /app

RUN apk add --no-cache maven

COPY .mvn/ .mvn
COPY pom.xml ./
RUN mvn dependency:go-offline

COPY src ./src

CMD ["mvn", "spring-boot:run"]
