## Job Microservice Application with Zipkin Tracing
### Overview
This project implements a Job Microservice Architecture with three key microservices: Job Microservice, Company Microservice, and Review Microservice. These services interact through Spring Cloud Eureka for service discovery and are deployed using Docker containers. The system utilizes Zipkin for distributed tracing, providing enhanced observability and performance monitoring across microservices.

### Features
* Job Microservice: Manages job postings (CRUD operations).
* Company Microservice: Handles company profiles and associations with job listings.
* Review Microservice: Allows users to submit reviews for jobs and companies.
* Service Discovery: Implemented using Eureka for dynamic registration and service discovery.
* API Gateway: Centralized API gateway to route requests to respective services.
* Distributed Tracing: Zipkin integrated for tracing request flows across the microservices.
* Dockerized: All microservices are containerized using Docker for easier deployment.

### Architecture
This microservice-based architecture includes the following key components:

1. Job Microservice: Manages job-related operations such as creating and viewing job posts.
2. Company Microservice: Responsible for managing company information.
3. Review Microservice: Handles the submission and retrieval of reviews.
4. Service Registry (Eureka): Allows for service discovery among microservices.
5. Zipkin: Provides observability by tracing requests across services.
6. Docker: All microservices are packaged as Docker containers for portability and easy deployment.

### Prerequisites
* Java 11 or later
* Maven for building the project
* Docker for containerization
* PostgreSQL database
* Spring Boot and Spring Cloud Eureka
* Zipkin Server for distributed tracing

### Setup and Installation
Step 1: Clone the Repository
```bash
git clone https://github.com/okpakomarvis/job-microservice.git
cd job-microservice

```
