# Blog Application

### Task description
- Design a data model for a blog application and implement the corresponding JPA entities.
- Write a Spring Data JPA repository query to find all articles published before a certain date.
- Create a REST API that enables CRUD operations for articles.
- Implement exception handling in Spring Boot to return custom error messages for a REST API.

### How to run locally
```
docker-compose up -d
./gradlew bootRun --args='--spring.profiles.active=local'
```

### Summary
Below is a concise summary of the project setup and key features:

- Simplified Blog Model

The application consists of Author and Article entities with a one-to-many relationship.
No Comment entity is included, keeping the model minimal and focused on core blog functionality.


- Validation of API Requests

DTOs (Data Transfer Objects) are used to receive and validate input data.
Validation ensures that incoming requests meet required constraints before persisting to the database.

- Entity Projections

Spring Data JPA projections are used to fetch only certain fields or reduce the data payload.
This optimizes performance and network usage by avoiding the retrieval of unused entity fields.

- Auditing

The Article entity leverages Spring Data JPA auditing annotations (@CreatedDate, @LastModifiedDate) to automatically record creation and update timestamps.
The auditing is enabled via @EnableJpaAuditing in the configuration, ensuring timestamps are reliably tracked.


- Optimistic Locking

Implemented via @Version on the Article entity.
This prevents overwriting changes when multiple clients modify the same article concurrently, throwing an exception if a stale version is detected.


- Database Migration Scripts

Flyway SQL scripts define and evolve the schema over time.
One script creates and populates the blog schema and tables, while subsequent scripts handle updates such as adding the version column or new indexes.


- Index in the Database

An index was added (e.g., on publishedDate or createdDate) to improve query performance when filtering or sorting articles.


- OpenAPI Specification

OpenAPI documentation is generated, detailing the endpoints, request/response models, and validation rules.
This ensures clear, up-to-date API docs for consumers.


- Integration Tests

Tests use Spring Boot’s testing support (e.g., @SpringBootTest or @DataJpaTest) and Testcontainers to spin up a real PostgreSQL instance.
They verify repository logic, migrations, and REST endpoints in an environment close to production, increasing reliability.
