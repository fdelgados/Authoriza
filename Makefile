# Executables (local)
DOCKER_COMP = docker compose

.PHONY: all
all: build

.PHONY: up
up:
	@$(DOCKER_COMP) up -d


.PHONY: reload
reload: compile deps

deps:
	mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

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

#.PHONY: run-tests
#run-tests:
#	@./gradlew test --warning-mode all
#
#.PHONY: test
#test:
#	@docker exec codelytv-ddd_skeleton-java ./gradlew test --warning-mode all
#
#.PHONY: run
#run:
#	@./gradlew :run