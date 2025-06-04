.PHONY: build run up clean down

build:
	docker-compose build

run:
	docker-compose up

up: build
	$(MAKE) run

clean:
	docker-compose down -v --rmi all --remove-orphans 