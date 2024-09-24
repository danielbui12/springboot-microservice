# Spring Boot Microservices

## Tech stack
- Spring Boot
- Spring REST Client: allows services to synchronously communicate with each other.
- Spring Web: for REST API
- API Gateway using Spring Cloud Gateway MVC
- Lombok: Java annotation
- Testcontainer: lightweight, throwaway instances of common databases, Selenium web browsers, or anything else that can run in a Docker container.
- Wiremock: Mock http response
- KeyCloak: Open source IAM, provide many features: SSO, Identity Broking and Social Login, etc. Check out at [this](https://www.keycloak.org/)
- Circuit Breaker: For resilient application
  - Resilience4j is a fault tolerance library designed for Java8 and functional programming
- Kafka: Distributed event streaming
- Grafana Stack (Prometheus, Grafana, Loki and Tempo) for logging, monitoring and tracing
- Database: Mongo DB, MySQL, PostgreSQL
- Spring Data JPA: Persist data in SQL stores with Java Persistence API using Spring Data and Hibernate.
- Flyway Migration: Version control for your database so you can migrate from any version (incl. an empty database) to the latest version of the schema.
- PostgreSQL / MySQL Driver & Spring Data MongoDB: Driver to connect to database

## Services Overview
- Product Service
- Order Service
- Inventory Service
- Notification Service
- API Gateway using Spring Cloud Gateway MVC

## Architecture

![architecture](https://github.com/user-attachments/assets/d2409732-d33c-4d58-8d3a-e1de6791ddc5)

