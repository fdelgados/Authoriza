FROM maven:3-openjdk-17-slim as builder

WORKDIR /build

COPY pom.xml pom.xml

#COPY src/api/pom.xml api/pom.xml
#COPY src/shared/pom.xml shared/pom.xml

COPY src/shared shared
COPY src/api api

RUN mvn -q -ntp -B -pl com.authoriza:authoriza-shared-context -am dependency:go-offline


RUN mvn -q -B -pl com.authoriza:authoriza-shared-context install

#RUN mvn -q -ntp -B -pl :authoriza-api -am dependency:go-offline
#
#
#RUN mvn -q -ntp -B -pl :authoriza-shared-context,:authoriza-api package

RUN mkdir -p /jar-layers
WORKDIR /jar-layers

RUN java -Djarmode=layertools -jar /build/api/target/*.jar extract


FROM eclipse-temurin:17-jdk-focal

WORKDIR /app
#
#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./
#
#COPY src ./src
#
#RUN ./mvnw clean install
#
#CMD ["./mvnw", "spring-boot:run", "-pl", ":authoriza-api"]

COPY --from=builder /jar-layers/dependencies/ ./
COPY --from=builder /jar-layers/spring-boot-loader/ ./
COPY --from=builder /jar-layers/snapshot-dependencies/ ./
COPY --from=builder /jar-layers/application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]