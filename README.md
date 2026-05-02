# URL Shortener

A REST API service for shortening URLs, built with Spring Boot 4 and Java 25.

## Features

- Shorten any URL to a compact 8-character code
- Redirect from short code to original URL (HTTP 302)
- Click count tracking per shortened URL
- Input validation with meaningful error responses
- Database migrations via Liquibase

## Tech Stack

- **Java 25** / **Spring Boot 4.0.6**
- **PostgreSQL** — persistent storage
- **Liquibase** — database schema migrations
- **Spring Actuator** — health & conditions endpoints
- **Lombok** — boilerplate reduction
- **Docker Compose** — local database setup

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/urls` | Create a short URL |
| `GET` | `/api/{shortCode}` | Redirect to original URL |

### POST /api/urls

**Request:**
```json
{
  "originalUrl": "https://www.example.com/very/long/url"
}
```

**Response (201 Created):**
```json
{
  "code": 201,
  "message": "Success",
  "shortCode": "a1b2c3d4",
  "shortUrl": "http://localhost:8080/api/a1b2c3d4",
  "originalUrl": "https://www.example.com/very/long/url"
}
```

### GET /api/{shortCode}

Redirects to the original URL with HTTP `302 Found`.

## Getting Started

### Prerequisites

- Java 25+
- Docker & Docker Compose

### Run locally

1. **Start the database:**
```bash
docker-compose up -d
```

2. **Set environment variables (optional, defaults shown):**
```bash
export DB_USERNAME=postgres
export DB_PASSWORD=postgres
```

3. **Run the application:**
```bash
./gradlew bootRun
```

The app will be available at `http://localhost:8080`.  
Liquibase will automatically apply migrations on startup.

### Run tests

```bash
./gradlew test
```

## Project Structure

```
src/main/java/com/kmateyev/url/shortener/
├── config/         # AppConfig (base URL property)
├── constant/       # Shared constants
├── controller/     # REST endpoints
├── dto/            # Request/Response DTOs
├── entity/         # JPA entity
├── exception/      # GlobalExceptionHandler, custom exceptions
├── model/          # BaseResponse
├── repository/     # Spring Data JPA repository
└── service/        # Service interface + implementation
```