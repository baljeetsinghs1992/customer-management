# customer-management


## Features

- CRUD operations for customers.
- Tier calculation logic (Silver, Gold, Platinum) applied at runtime.
- H2 in-memory database for testing.
- OpenAPI specification.
- Unit tests for CRUD, validation, and tier logic.

This is a Spring Boot REST API for managing customers.


## Customer Tier Logic

- **Silver**: `annualSpend < 1000`
- **Gold**: `1000 <= annualSpend < 10000` and last purchase within 12 months
- **Platinum**: `annualSpend >= 10000` and last purchase within 6 months


## Getting Started

### Build and Run

```bash
# Clone the repo
git clone [customer-management](https://github.com/baljeetsinghs1992/customer-management.git)

# Navigate to project directory
cd customer-management-api

## How to Run
```bash
mvn spring-boot:run
```

## H2 Console
Visit `http://localhost:8080/h2-console`.

## API Documentation
Visit `http://localhost:8080/swagger-ui.html`
