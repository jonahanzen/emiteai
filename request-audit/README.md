# request-audit

This microservice listens to a RabbitMQ queue and inserts request logs into a PostgreSQL database.

## Features
- Consumes messages from RabbitMQ (queue: `request-log-queue`)
- Persists logs to the `request_audit_log` table in PostgreSQL

## How to Run
- Configure RabbitMQ and PostgreSQL connection properties in `application.properties`.
- Build with Maven: `mvn clean package`
- Run: `java -jar target/request-audit-1.0-SNAPSHOT.jar`

## Docker
A Dockerfile is provided for containerization. See `docker-compose.yml` for multi-service orchestration.

---
