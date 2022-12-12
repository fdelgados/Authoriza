# Executables (local)
DOCKER_COMP = docker compose

.PHONY: all
all: build

.PHONY: up
up:
	@$(DOCKER_COMP) up -d


.PHONY: reload
reload: compile

.PHONY: compile
compile:
	./mvnw compiler:compile

docker-build:
	@$(DOCKER_COMP) build --pull --no-cache

start: docker-build up

stop:
	@$(DOCKER_COMP) down --remove-orphans

.PHONY: build
build:
	@./mvnw package

.PHONY: run-tests
run-tests:
	@./mvnw test

.PHONY: test
test:
	@docker exec authoriza-app ./mvnw test

.PHONY: run
run:
	@./mvnw spring-boot:run -pl :authoriza-api