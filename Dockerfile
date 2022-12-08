FROM eclipse-temurin:17-jdk-focal

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

COPY src ./src

RUN ./mvnw clean install

CMD ["./mvnw", "spring-boot:run", "-pl", ":authoriza-api"]