# College Placement Management System

A backend REST API for managing the college placement process by connecting
students, companies, placement officers, jobs, applications, interviews,
and placements.

##  Project Status

 Currently under active development.

## 🛠️ Tech Stack

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- MySQL
- Maven

##  Architecture

The project follows a layered architecture:

Client
  ↓
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

##  Currently Implemented

- Spring Boot project setup
- MySQL database integration
- JPA and Hibernate configuration
- Student entity
- Student repository
- Student service
- Student creation API
- Bean validation
- Global exception handling
- Structured validation error response

##  API

### Create Student

```http
POST /placement/student
```

### Example Request

{
  "name": "Rahul",
  "email": "rahul@gmail.com",
  "dateOfBirth": "2004-05-12",
  "joiningYear": 2022,
  "graduationYear": 2026,
  "cgpa": 8.5,
  "tenthPercentage": 92.5,
  "twelfthPercentage": 89.0,
  "backlogs": 0,
  "currentSemester": 8,
  "resumeUrl": "https://example.com/resume"
}
