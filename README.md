# Java Backend Interview Assignment (Java 21, Spring Boot)

This project covers:

1. Spring Boot application (Java 21)
2. Clean project structure (api / domain / integration / config)
3. REST APIs testable via Postman (Postman collection included)
4. Logs REQUEST & RESPONSE (body + status + duration) to a file: `logs/app.log`
5. Database connectivity:
   - Profile **mssql** for Local MSSQL (DB name: `TESTDB`)
6. GET API with Pagination (10 records per page)
7. Nested call to a 3rd-party API:
   - `GET /api/external/posts/{id}` calls `jsonplaceholder.typicode.com`

## Requirements

- Java 21
- Maven 3.9+

## Run (default, no DB install needed)

```bash
mvn spring-boot:run
```

App runs on `http://localhost:8080`

## Run with MSSQL (TESTDB)

1. Ensure MSSQL is running locally and `TESTDB` exists
2. Update credentials in `src/main/resources/application.yml` (profile `mssql`)
3. Start:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=mssql
```

Spring JPA will create/update the `customers` table automatically (`ddl-auto: update`).

## APIs

### Health

- `GET /api/health`

### Customers (DB)

- `POST /api/customers`
- `PUT /api/customers/{id}`
- `GET /api/customers/{id}`
- `GET /api/customers?page=0` (Pagination: 10 per page)

### Nested Third-party Call

- `GET /api/external/posts/{id}`

## Logs

Request and response logs go to:

- `logs/app.log`

## Postman

Import the collection:

- `postman/SpringBootClient.postman_collection.json`
