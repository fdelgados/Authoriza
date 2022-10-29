FROM eclipse-temurin:17-jdk-focal
RUN useradd -s /bin/bash -m authoriza && groupadd dev && usermod -aG dev authoriza
USER authoriza:dev

WORKDIR /app

RUN mkdir src
RUN mkdir lib
RUN mkdir META-INF

COPY src ./src

ENTRYPOINT ["java", "-Dspring.devtools.restart.enabled=true", "-cp","/app/classes:/app/lib/*","com.authoriza.AuthorizaApplication"]