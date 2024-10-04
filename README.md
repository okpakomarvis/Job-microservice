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
#### Step 1: Clone the Repository
```bash
git clone https://github.com/okpakomarvis/job-microservice.git
cd job-microservice

```
#### Step 2: Build the Microservices
Build the microservices using Maven:

```bash
cd job-microservice
mvn clean install
cd ../company-microservice
mvn clean install
cd ../review-microservice
mvn clean install
```
#### Step 3: Set Up PostgreSQL Databases
Create PostgreSQL databases for each microservice:
* Job Microservice: job_db
* Company Microservice: company_db
* Review Microservice: review_db
* Update the database configurations in each microservice's application.yml file.

#### Step 4: Run the Microservices with Docker
Docker Compose is used to start all the services. Run the following command to start the microservices, Eureka server, and Zipkin:
```bash
docker-compose up --build

```
#### Step 5: Access the Services
* Job Microservice: http://localhost:8081
* Company Microservice: http://localhost:8082
* Review Microservice: http://localhost:8083
* Eureka Dashboard: http://localhost:8761
* Zipkin UI: http://localhost:9411

### Usage
Once the microservices are running, you can use Postman or any REST client to interact with the following endpoints:

#### Job Microservice Endpoints
* POST /jobs: Create a new job.
* GET /jobs: List all jobs.
* PUT /jobs/{id}: Update a job.
* DELETE /jobs/{id}: Delete a job.
#### Company Microservice Endpoints
* POST /companies: Create a new company.
* GET /companies: List all companies.
* PUT /companies/{id}: Update a company.
* DELETE /companies/{id}: Delete a company.
#### Review Microservice Endpoints
* POST /reviews: Submit a review.
* GET /reviews: List all reviews.
* PUT /reviews/{id}: Update a review.
* DELETE /reviews/{id}: Delete a review.
#### Zipkin Tracing
To view traces and monitor the performance of the service interactions, visit the Zipkin UI:
<br />
URL: http://localhost:9411
<br />
Zipkin will display the trace history of each request, helping in identifying latency issues and bottlenecks in the system.

#### Technologies Used
* Java 11
* Spring Boot
* Spring Cloud Eureka
* PostgreSQL
* Docker
* Zipkin (for distributed tracing)
* Maven (for project build)
* Docker Compose (for container orchestration)

#### System Design
##### Use Case Diagram
The system is designed to handle job postings, manage company profiles, and allow users to submit reviews. Each microservice operates independently but communicates through Eureka.

#### Architecture Diagram
The architecture follows a microservices pattern with a central Eureka service for service discovery, Docker for deployment, and Zipkin for tracing requests.

#### Future Improvements
* Security Enhancements: Add OAuth2 or JWT-based authentication and authorization.
* Load Balancing: Implement load balancing to handle increased traffic.
* ElasticSearch Integration: Add ElasticSearch for more efficient search capabilities across job listings and reviews.

