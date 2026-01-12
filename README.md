# ru.faickoff.api.auth

A secure and scalable JWT-based authentication microservice designed specifically for Google Gemini AI REST API ecosystems.

## Features

- JWT (JSON Web Token) based authentication and authorization
- Seamless integration with Google Gemini AI REST API ecosystems
- Containerized microservice architecture using Docker
- PostgreSQL for persistent data storage
- Redis for high-performance caching and session management
- Spring Boot backend with Maven

## Table of Contents

1. [Prerequisites](#prerequisites)
2. [Quick Start (Docker - Production)](#quick-start-docker---production)
3. [Detailed Setup](#detailed-setup)
4. [Running the Application](#running-the-application)
   - [Option 1: Local Development with Maven](#option-1-local-development-with-maven)
   - [Option 2: Development Mode (Docker Compose)](#option-2-development-mode-docker-compose)
   - [Option 3: Production Mode (Docker Compose)](#option-3-production-mode-docker-compose)
5. [Services & Ports](#services--ports)
6. [Cache Maven Repository (Performance Tip)](#cache-maven-repository-performance-tip)

## Prerequisites

- **Docker** and **Docker Compose**
- **Java 21+** (for local Maven execution)
- **Maven** (or use the included `./mvnw` wrapper)

## Quick Start (Docker - Production)

For a quick production-like setup (with persistent database volumes):

1. Clone the repository:
   ```bash
   git clone <your-repository-url>
   cd ru.faickoff.api.auth
   ```

2. Configure environment variables and application settings:
   ```bash
   cp .env.example .env
   # Edit the .env file with your actual values (passwords, ports, etc.)
   cp src/main/resources/application.properties.example src/main/resources/application.properties
   # VERY IMPORTANT: In application.properties, specify ports and addresses
   # that match the values set in your .env file (e.g., DB and Redis ports).
   ```

3. Start all services:
   ```bash
   docker-compose up -d --build
   ```

The application will be available at: `http://localhost:{TOMCAT_PORT from .env}`

## Detailed Setup

1. **Environment Configuration:** Copy and configure the necessary files:
   ```bash
   cp .env.example .env
   # Edit .env (set your passwords, ports, network name)
   cp src/main/resources/application.properties.example src/main/resources/application.properties
   # Edit application.properties.
   # Key point: ensure database connection parameters (jdbc:postgresql://...)
   # and Redis settings (spring.redis.host, spring.redis.port) match the service names
   # and ports specified in your docker-compose files and .env.
   ```

   *Example of critical parameters in `application.properties`:*
   ```
   server.port=${TOMCAT_PORT}
   spring.datasource.url=jdbc:postgresql://database:${DB_PORT}/${DB_NAME}
   spring.redis.host=redis
   spring.redis.port=${REDIS_PORT}
   ```

## Running the Application

You can run the project in several ways depending on your needs.

### Option 1: Local Development with Maven

This approach is suitable for active development when you need hot code reload.

1. Start auxiliary services (Redis and PostgreSQL) in the background:
   ```bash
   docker-compose -f docker-compose.redis.yml up -d --build
   docker-compose -f docker-compose.database.yml up -d --build
   ```

2. Run the Spring Boot application locally:
   ```bash
   ./mvnw spring-boot:run
   ```

The application will start with hot reload and connect to Docker services.

### Option 2: Development Mode (Docker Compose)

Run the entire stack (app + DB + Redis) via Docker Compose in development mode **without persisting database data to the local host** (volumes are not mounted).

```bash
docker-compose -f docker-compose.develop.yml up -d --build
```

### Option 3: Production Mode (Docker Compose)

Run the entire stack in production mode. PostgreSQL data will be persisted in Docker volumes.

```bash
docker-compose up -d --build
```

## Services & Ports

After starting the services, they will be available at the following addresses:

| Service     | Host URL                          | Internal Network Hostname | Purpose                     |
|-------------|-----------------------------------|---------------------------|-----------------------------|
| **API Auth** | `http://localhost:{TOMCAT_PORT}`  | `app`                     | Main Spring Boot application |
| PostgreSQL  | `localhost:{DB_PORT}`             | `database`                | Primary database            |
| Redis       | `localhost:{REDIS_PORT}`          | `redis`                   | Caching and sessions        |

*All ports (`{TOMCAT_PORT}`, `{DB_PORT}`, `{REDIS_PORT}`) are defined in the `.env` file.*

## Cache Maven Repository (Performance Tip)

To speed up subsequent Docker builds and avoid downloading dependencies on every `docker build`, you can cache the local Maven repository between builds.

1. **Create cache volume:** Copy dependencies from the container to a local `m2` folder (run once or when dependencies are updated):
   ```bash
   docker cp $(docker-compose ps -q tomcat):/root/.m2/repository ./m2
   ```
   *Note: Ensure the service name in your `docker-compose.yml` matches (`tomcat`).*

2. **Use cache in Dockerfile:** Make sure your `Dockerfile` copies the `pom.xml` file and runs `mvn dependency:go-offline` before copying the source code. Docker will use the cached dependency layer as long as `pom.xml` doesn't change.
