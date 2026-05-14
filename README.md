# 🛒 E-Commerce Microservices Project

A scalable and production-style E-Commerce Backend System built using Java Spring Boot Microservices architecture with Kafka-based event-driven communication, Saga orchestration, Redis caching, JWT authentication, Docker, and MySQL.

---

# 🚀 Tech Stack

- Java 17
- Spring Boot
- Spring Cloud Gateway
- Spring Security + JWT
- Apache Kafka
- Saga Pattern
- Redis
- MySQL
- Docker & Docker Compose
- Maven

---

# 🏗️ Architecture

This project follows a Microservices Architecture where each service is independently deployable and communicates using REST APIs and Kafka events.

## Services Included

| Service | Responsibility |
|---|---|
| gateway-service | API Gateway and routing |
| user-service | User registration and authentication |
| product-service | Product management |
| inventory-service | Inventory stock management |
| order-service | Order creation and management |
| payment-service | Payment processing |
| notification-service | Sending notifications |
| saga-orchestrator | Distributed transaction orchestration |
| common-kafka-models | Shared Kafka DTO/events |

---

# 🔥 Features

- Microservices-based architecture
- API Gateway implementation
- JWT Authentication & Authorization
- Kafka Event-Driven Communication
- Saga Pattern for Distributed Transactions
- Redis Caching
- Dockerized Services
- MySQL Integration
- Inter-service Communication
- Centralized Event Models
- Scalable Backend Design

---

# 🔄 Order Flow

1. User places order
2. Order Service creates order
3. Saga Orchestrator starts transaction
4. Inventory Service validates stock
5. Payment Service processes payment
6. Notification Service sends confirmation
7. Kafka events coordinate communication between services

---

# 📦 Kafka Usage

Kafka is used for asynchronous communication between services.

### Example Events

- ORDER_CREATED
- PAYMENT_SUCCESS
- PAYMENT_FAILED
- INVENTORY_RESERVED
- INVENTORY_FAILED
- ORDER_COMPLETED
- ORDER_CANCELLED

---

# 🔁 Saga Pattern

Saga Orchestrator handles distributed transactions across multiple services.

### Example Compensation Flow

- Payment failed
- Inventory rollback triggered
- Order marked as cancelled
- Notification sent to user

---

# ⚡ Redis Caching

Redis is used for:
- Product caching
- Faster API responses
- Reducing database load

---

# 🐳 Docker Support

All services are containerized using Docker.

## Run Project

```bash
docker-compose up
