# Pride Admin Portal

A full-stack web application built with **Angular, Spring Boot, and PostgreSQL** to simplify student administration, employee management, bulk email communication, and employee attendance tracking.

## Overview

Pride Admin Portal provides a centralized platform for administrators to manage student and employee records. It combines a responsive Angular frontend with Spring Boot REST APIs and PostgreSQL for persistent data storage.

The application incorporates role-based JWT authentication, BCrypt password hashing, frontend route guards, Java Record-based DTOs, centralized exception handling, and application logging to support secure and maintainable development.

## Key Features

### Student Management
- Add new student details.
- View and retrieve student records.
- Modify existing student information.
- Maintain student data in a centralized system.

### Employee Management
- Add new employee details.
- View and retrieve employee information.
- Modify existing employee records.
- Maintain employee information in PostgreSQL.

### Bulk Email Communication
- Send emails to all students at once.
- Send emails to all employees at once.
- Use mail connectors for application email communication.

### Employee Attendance
- Mark attendance for employees.
- Retrieve employee attendance records.
- Attendance reports with date filters.
- Manage attendance information through the admin portal.

### Authentication and Authorization
- Role-based access control using JWT authentication.
- Authenticate API requests using JSON Web Tokens.
- Hash passwords using Spring Security's `BCryptPasswordEncoder`.
- Use Angular route guards to control access to protected frontend routes.
- Enforce authorization on protected backend endpoints.

### Backend Engineering
- RESTful API development with Spring Boot.
- Java Record-based DTOs for API request and response data exchange.
- Centralized exception handling.
- Application logging for troubleshooting and monitoring.
- PostgreSQL database integration.
- Separation of API data-transfer objects from persistence entities.

### Frontend Engineering
- Angular-based user interface.
- Route guards for protected navigation.
- Mapping backend API responses to frontend models and views.
- Integration with Spring Boot REST APIs.

## Technology Stack

| Technology | Purpose |
|---|---|
| Angular | Frontend application |
| TypeScript | Frontend programming |
| Java | Backend programming |
| Spring Boot | REST API development |
| Spring Security | Authentication and authorization |
| JWT | Token-based authentication |
| Role-Based Access Control (RBAC) | Role-based permissions |
| BCryptPasswordEncoder | Password hashing |
| PostgreSQL | Relational database |
| Java Records | DTOs for API data exchange |
| Mail Connectors | Bulk email communication |
| Exception Handlers | Centralized error handling |
| Application Logging | Debugging and monitoring |

## Application Architecture

```text
                  Administrator
                        |
                        v
                Angular Frontend
                        |
                 HTTP / REST APIs
                        |
                        v
               Spring Boot Backend
                        |
              +---------+----------+
              |         |          |
              v         v          v
          JWT / RBAC  Business   Exception
           Security     Logic    Handling
                        |
                +-------+-------+
                |               |
                v               v
            PostgreSQL     Mail Connectors
                |
                v
       +--------+---------+
       |        |         |
    Students Employees Attendance
```

## Authentication and Security

### Role-Based JWT Authentication

JWT tokens are used to authenticate requests to protected APIs. Role-based authorization determines which operations an authenticated user is permitted to perform.

### Password Security with BCrypt

Passwords are hashed using `BCryptPasswordEncoder` before being stored in the database. Password verification is performed against the stored hash rather than storing or comparing plain-text passwords.

### Angular Route Guards

Angular route guards help control navigation to protected pages based on authentication and role information. Backend authorization remains responsible for protecting the actual API operations and data.

### Exception Handling and Logging

- Centralized exception handlers provide consistent API error responses.
- Application logs help identify errors and investigate application behavior.
- Sensitive information, such as passwords, JWT secrets, and authentication tokens, should not be written to logs.

## DTOs Using Java Records

The backend uses Java Records to define Data Transfer Objects (DTOs) for API request and response communication.

Benefits include:

- Concise and readable data-transfer definitions.
- Immutable record components.
- Clear separation between API contracts and database entities.
- Structured JSON communication between Angular and Spring Boot.

## Application Workflow

1. The user accesses the Angular frontend.
2. The frontend communicates with the Spring Boot backend through REST APIs.
3. JWT authentication and role-based authorization are applied to protected operations.
4. Backend services process requests using business logic and DTOs.
5. PostgreSQL stores and retrieves student, employee, and attendance data.
6. Mail connectors support bulk email communication.
7. API responses are returned to Angular and mapped to the appropriate frontend views.

## Getting Started

### Prerequisites

Install the following tools:

- Java JDK compatible with the Spring Boot project
- Node.js and npm
- Angular CLI compatible with the project
- PostgreSQL
- Git
- Maven

### 1. Clone the Repository

```bash
git clone https://github.com/AjayBodapati1/pride_admin_portal.git
cd pride_admin_portal
```

### 2. Configure PostgreSQL

Create a database for the application:

```sql
CREATE DATABASE pride_admin_portal;
```

Configure the database connection in the backend configuration file or through environment variables.

Example `application.properties` configuration:

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

Set the environment variables according to your PostgreSQL configuration.

### 3. Configure Gmail App Password

If the application uses Gmail to send emails, generate a Google App Password and configure it in the Spring Boot properties file.

#### 1. Enable 2-Step Verification

1. Open [Google Account Security](https://myaccount.google.com/security).
2. Sign in to the Google account used for sending emails.
3. Enable **2-Step Verification** if it is not already enabled.

#### 2. Generate an App Password

1. Open [Google App Passwords](https://myaccount.google.com/apppasswords).
2. Sign in again if prompted.
3. Enter an application name, such as `Pride Admin Portal`.
4. Generate the app password.
5. Copy the generated password and store it securely.

*Note: App Passwords require 2-Step Verification and may be unavailable for some account types or security configurations.*

#### 3. Configure Spring Boot Mail Properties

Add the following configuration to `application.properties`:

```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=${MAIL_USERNAME}
spring.mail.password=${MAIL_APP_PASSWORD}

spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

Set the environment variables `MAIL_USERNAME` to the Gmail address used to send emails and `MAIL_APP_PASSWORD` to the generated Google App Password.

For local development, you can set these environment variables in your terminal or IDE run configuration.

**Security note:** Never commit your actual Gmail address credentials or App Password to a public GitHub repository. Keep secrets in environment variables or an untracked local configuration file.



### 4. Configure Backend Settings

Configure the required application settings:

- Database connection details.
- JWT signing secret and token expiration settings.
- Role-based authorization rules.
- Mail connector or SMTP credentials.
- Backend port and frontend CORS settings, if required.

**Security:** Keep passwords, email credentials, and JWT signing secrets outside source control. Never commit production credentials to GitHub.

### 5. Run the Spring Boot Backend

Navigate to the backend directory containing `pom.xml` and run:

```bash
mvn spring-boot:run
```

Alternatively, package and execute the application:

```bash
mvn clean package
java -jar target/<application-jar>.jar
```

Replace `<application-jar>.jar` with the generated JAR filename.

### 6. Run the Angular Frontend

Navigate to the frontend directory containing `package.json`.

Install dependencies:

```bash
npm install
```

Start the development server:

```bash
ng serve
```

Open the URL displayed in the terminal, commonly `http://localhost:4200`.

Ensure the Angular API configuration points to the running Spring Boot backend.

*Note: Update the directory paths, ports, and configuration property names according to the actual repository structure.*

## Key Learning Outcomes

This project demonstrates practical experience in:

- Full-stack development with Angular and Spring Boot.
- REST API design and integration.
- PostgreSQL database operations.
- Role-based JWT authentication and authorization.
- Secure password hashing with BCrypt.
- Java Record-based DTO design.
- Angular route guards and API response mapping.
- Centralized exception handling and application logging.
- Bulk email integration.
- Student and employee CRUD operations.
- Employee attendance management.

## Future Enhancements
- PDF report generation for student progress.
- Search, sorting, and pagination for student & employee records.
- Email delivery status tracking and retry handling.
- Automated unit and integration tests.

## Repository
**GitHub:** [AjayBodapati1/pride_admin_portal](https://github.com/AjayBodapati1/pride_admin_portal)

## Contact
**Linkedin:** [Ajay BODAPATI](https://www.linkedin.com/in/ajay-bodapati)

**GitHub:** [ajaybodapati01@gmail.com](https://mail.google.com/mail/u/0/?tab=rm&ogbl#inbox?compose=GTvVlcRzBkzFfNWtMPrQLFVcXTHlDnWBKpNGvftpZxJSwsSPjsMrQKctkVCpGpPBqKnRlVrNvHDnC)

---

*Built to make student administration, employee management, and attendance tracking simpler through secure full-stack development.*