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

## Sample Requests

```bash
# Create a Customer (POST /customers)
curl -X POST http://localhost:8080/customers \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john.doe@example.com",
    "annualSpend": 9500,
    "lastPurchaseDate": "2024-10-01T00:00:00"
  }'

# Get Customer by ID (GET /customers/{id})
curl http://localhost:8080/customers/123e4567-e89b-12d3-a456-426614174000

# Get Customer by Name (GET /customers?name={name})
curl http://localhost:8080/customers?name=John%20Doe

# Get Customer by Email (GET /customers?email={email})
curl http://localhost:8080/customers?email=john.doe@example.com

# Update a Customer (PUT /customers/{id})
curl -X PUT http://localhost:8080/customers/123e4567-e89b-12d3-a456-426614174000 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Johnathan Doe",
    "email": "johnathan.doe@example.com",
    "annualSpend": 11000,
    "lastPurchaseDate": "2024-12-01T00:00:00"
  }'

# Delete a Customer (DELETE /customers/{id})
curl -X DELETE http://localhost:8080/customers/123e4567-e89b-12d3-a456-426614174000


## Getting Started

### Build and Run

```bash
# Clone the repo
git clone https://github.com/baljeetsinghs1992/customer-management.git

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
