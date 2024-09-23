# Spring Boot Microservices Example

## Tech stack
- Spring Boot
- Spring REST Client: allows services to synchronously communicate with each other.
- Spring Web: for RESTful web API
- Lombok: Java annotation
- Testcontainer: lightweight, throwaway instances of common databases, Selenium web browsers, or anything else that can run in a Docker container.
- Spring Data JPA: Persist data in SQL stores with Java Persistence API using Spring Data and Hibernate.
- Flyway Migration: Version control for your database so you can migrate from any version (incl. an empty database) to the latest version of the schema.
- PostgreSQL / MySQL Driver & Spring Data MongoDB: Driver to connect to database
- Wiremock: Mock http response
- KeyCloak: Open source IAM, provide many features: SSO, Identity Broking and Social Login, etc. Check out at [this](https://www.keycloak.org/)
- Circuit Breaker: For resilient application
  - Resilience4j is a fault tolerance library designed for Java8 and functional programming
- API Gateway using Spring Cloud Gateway MVC
- Mongo DB
- MySQL
- Kafka
- Grafana Stack (Prometheus, Grafana, Loki and Tempo)

## Services Overview
- Product Service
- Order Service
- Inventory Service
- Notification Service
- API Gateway using Spring Cloud Gateway MVC


