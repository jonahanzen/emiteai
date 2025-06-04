# EmiteAi

EmiteAi is a microservices-based application that leverages RabbitMQ for messaging, PostgreSQL for data storage, and consists of three main components:

- **Backend**: A Spring Boot application that provides the main API and business logic.
- **Frontend**: A React application for the user interface.
- **Request Audit**: A Spring Boot service for auditing requests.

## Architecture

- **RabbitMQ**: Message broker for asynchronous communication between services.
- **PostgreSQL**: Database for persistent storage.
- **Docker Compose**: Orchestrates all services for local development.

## Prerequisites

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/)

## Getting Started

1. **Clone the repository**

   ```bash
   git clone <repo-url>
   cd EmiteAi
   ```

2. **Configure Environment Variables**

   The project uses a `.env` file at the root directory. Default values are provided, but you can adjust them as needed.

3. **Build and Start All Services**

   Run the following command to build and start all services:

   ```bash
   docker-compose up --build
   ```

   This will start:
   - RabbitMQ (management UI at [http://localhost:15672](http://localhost:15672), default user/password from `.env`)
   - PostgreSQL (on port 5433)
   - Backend API (on port 8080)
   - Frontend (on port 3000)
   - Request Audit service (on port 8081)

4. **Access the Applications**

   - **Frontend**: [http://localhost:3000](http://localhost:3000)
   - **Backend API**: [http://localhost:8080](http://localhost:8080)
   - **Request Audit**: [http://localhost:8081](http://localhost:8081)
   - **RabbitMQ UI**: [http://localhost:15672](http://localhost:15672)

## Stopping the Services

To stop all running containers:

```bash
docker-compose down
```

## Project Structure

- `backend/` - Spring Boot backend service
- `frontend/` - React frontend application
- `request-audit/` - Spring Boot audit service
- `docker-compose.yml` - Docker Compose configuration
- `.env` - Environment variables

## Notes

- The backend and request-audit services depend on RabbitMQ and PostgreSQL being healthy before starting.
- The frontend expects the backend to be available at `http://backend:8080` (Docker network).
- For development, you can modify the `.env` file to change credentials or ports as needed.

---

Feel free to contribute or open issues for improvements!
