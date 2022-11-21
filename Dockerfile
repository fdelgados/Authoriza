FROM eclipse-temurin:17-jdk-focal
#RUN useradd -s /bin/bash -m authoriza && groupadd dev && usermod -aG dev authoriza
#USER authoriza:dev

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

COPY api ./api
COPY shared ./shared

RUN #./mvnw dependency:go-offline
RUN ./mvnw clean install

CMD ["./mvnw", "spring-boot:run", "-pl", ":authoriza-api"]