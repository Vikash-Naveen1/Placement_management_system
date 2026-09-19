# College Placement Management System

A Spring Boot REST API for managing college placement activities including
students, companies, job postings, applications, and interviews.

##  Tech Stack

- Java 21
- Spring Boot 4.1.1
- Spring Web MVC
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Bean Validation
- Lombok
- Swagger / OpenAPI

##  Architecture

The application follows a layered architecture:

Controller
↓
Service
↓
Repository
↓
Spring Data JPA
↓
Hibernate
↓
MySQL

### Layers

- Controller – Handles HTTP requests and responses
- Service – Contains business logic and validation rules
- Repository – Handles database operations
- Entity – Represents database tables
- DTO – Controls API request and response data
- Exception – Handles application and business exceptions

##  Main Features

### Student Management

- Create student
- Get student by ID
- Get all students
- Update student
- Delete student
- Input validation

### Company Management

- Create company
- Get company by ID
- Get all companies
- Update company
- Delete company

### Job Management

- Create job
- View jobs
- Update job
- Delete job
- Job approval and rejection
- Job status management
- Pagination
- Sorting

### Application Management

Students can apply for available jobs.

Business rules include:

- Only OPEN jobs accept applications
- Applications cannot be submitted after the deadline
- Duplicate applications are prevented
- Application status transitions are controlled

Application lifecycle:

APPLIED
↓
SHORTLISTED
↓
INTERVIEW
↓
SELECTED / REJECTED

### Interview Management

- Schedule interviews for shortlisted candidates
- Automatically move application status to INTERVIEW
- Store interview round and scheduled time

##  Job Lifecycle

PENDING_APPROVAL
↓
OPEN
↓
CLOSED

A job can also be:

PENDING_APPROVAL → REJECTED

##  Validation & Exception Handling

The application uses Jakarta Bean Validation for request validation.

Examples:

- @NotBlank
- @NotNull
- @Email
- @Min
- @Max
- @DecimalMin
- @DecimalMax

Global exception handling is implemented using:

`@RestControllerAdvice`

HTTP responses include:

- 400 BAD REQUEST – Validation errors
- 404 NOT FOUND – Resource does not exist
- 409 CONFLICT – Business rule violation

## Pagination & Sorting

Job listings support pagination and sorting using Spring Data JPA.

Example:

GET /job/page?page=0&size=5

Jobs can also be sorted using Spring's `Sort` and `Pageable`.

## Logging

Application logging is implemented using SLF4J with Lombok's `@Slf4j`.

Important business events and warnings are logged without exposing
sensitive information.

## API Documentation

Swagger / OpenAPI is used for interactive API documentation.

After starting the application:

http://localhost:8080/swagger-ui/index.html

##  Database

MySQL database:

placement_management

Main entities:

- Student
- Company
- Job
- Application
- Interview

Relationships:

Company 1 → N Job

Student 1 → N Application

Job 1 → N Application

Application 1 → N Interview

## Running the Application

### 1. Configure MySQL

Create the database:

CREATE DATABASE placement_management;

Configure the database credentials in:

src/main/resources/application.properties

### 2. Set database password

The application uses:

DB_PASSWORD

as an environment variable.

### 3. Build the project

```bash
mvnw.cmd clean package