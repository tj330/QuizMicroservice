# QuizMicroservice

A microservices-based quiz platform built with Spring Boot and Java. The system is composed of several independent microservices, each responsible for a specific domain function, and orchestrated via an API Gateway. Service discovery and registration is managed through a Service Registry.

---

## Microservices Overview

### 1. **ApiGateway**
- **Purpose:** Central entry point for all client requests. Handles routing, authentication, and request forwarding to appropriate backend services.
- **Tech Stack:** Spring Cloud Gateway
- **Features:**
  - Routing to microservices
  - Basic request filtering and authentication
  - Centralized error handling

### 2. **QuestionService**
- **Purpose:** Manages quiz questions, including creation, retrieval, updating, and deletion.
- **Tech Stack:** Spring Boot, JPA/Hibernate, Database (e.g., H2, MySQL)
- **Features:**
  - CRUD operations for questions
  - RESTful API endpoints
  - Question categorization and tagging

### 3. **QuizService**
- **Purpose:** Handles quiz management, including quiz creation, participation, scoring, and results.
- **Tech Stack:** Spring Boot, JPA/Hibernate, Database
- **Features:**
  - CRUD operations for quizzes
  - Quiz-taking workflow
  - Score calculation and result storage

### 4. **ServiceRegistry**
- **Purpose:** Service discovery and registration, enabling dynamic lookup and load balancing between microservices.
- **Tech Stack:** Spring Cloud Netflix Eureka
- **Features:**
  - Registers all microservices
  - Enables inter-service communication
  - Provides health checks and monitoring

---

## Architecture Diagram

```
+--------------+        +------------------+        +------------------+
|   Client     | <----> |   API Gateway    | <----> | Service Registry |
+--------------+        +------------------+        +------------------+
                                    |       |                 ^
                                    v       v                 |
                              +---------+ +--------+          |
                              | QuizSvc | | QuesSvc| <--------+
                              +---------+ +--------+
```

---

## Running the System

1. **Start ServiceRegistry**  
   Launch the Eureka server to enable service discovery.

2. **Start Each Microservice**  
   - Start `QuestionService`
   - Start `QuizService`
   - Each will register itself in `ServiceRegistry`.

3. **Start ApiGateway**  
   Launch the gateway to route requests to the relevant services.

4. **Access the Platform**  
   Use the API Gateway endpoint to interact with the quiz platform.

---

## Technologies Used

- **Java 17+**
- **Spring Boot**
- **Spring Cloud Gateway**
- **Spring Cloud Netflix Eureka**
- **JPA/Hibernate**
- **REST APIs**

---

## Repository Structure

```
QuizMicroservice/
├── ApiGateway/
├── QuestionService/
├── QuizService/
├── ServiceRegistry/
└── README.md
```

---

## Contributing

Pull requests and issues are welcome! Please follow coding standards and provide tests for any new features.
