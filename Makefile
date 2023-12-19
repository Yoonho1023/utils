.PHONY: init
init:
	@echo "###### Application Initialize ######"
	@echo "mysql docker up"
	@docker pull mysql
	@docker build -t db -f Dockerfile .
	@docker run --name application-mysql -e MYSQL_ROOT_PASSWORD=welcome -e MYSQL_DATABASE=temp -d -p 3306:3306 mysql:latest

.PHONY: gen
gen:
	@./gradlew compileJava

.PHONY: clean
	@./gradlew clean

.PHONY: compile
compile:
	@./gradlew compileJava

.PHONY: run
run: compile
	@./gradlew bootRun

clean:
	@./gradlew clean

.PHONY: test
test:
	@./gradlew test

build.application:
	@./gradlew build -x test

.PHONY: build
build: build.docker
