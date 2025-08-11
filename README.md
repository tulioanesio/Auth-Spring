# Spring Boot JWT Authentication with RBAC

This project implements authentication using JWT (JSON Web Token) with Spring Boot and Spring Security, featuring Role-Based Access Control (RBAC) for access permission management.

## Technologies Used

- Java 17+
- Spring Boot 3+
- Spring Boot DevTools
- Spring Security
- JWT (io.jsonwebtoken)
- Spring Data JPA
- Flyway Migration
- Hibernate
- Bean Validation with Hibernate validator
- PostgreSQL (can be adapted for other relational databases)
- Maven

## Features

- User registration with password encryption (BCrypt)
- Login with JWT token generation
- Stateless authentication (no server-side sessions)
- RBAC to define access permissions by role (e.g., ADMIN, USER)
- Route protection according to user role
- Automatic request data validation

## Endpoints

### Register User
```
POST /register

{
"name": "John Doe",
"email": "john@example.com",
"password": "StrongPassword123",
"role": "USER"
}
```

### Login

```
POST /login
Content-Type: application/json

{
"email": "john@example.com",
"password": "StrongPassword123"
}

{
"token": "generated_jwt_here"
}
```

### Product Endpoints

#### List all products (accessible to any authenticated user)

```
GET /product
Authorization: Bearer generated_jwt_here

```

#### Create a new product (ADMIN only)

```
POST /product
Authorization: Bearer generated_jwt_here
Content-Type: application/json

{
"name": "Product Name",
"price": 99.99
}

```

#### Update a product by ID (ADMIN only)

```
PUT /product/{id}
Authorization: Bearer generated_jwt_here
Content-Type: application/json

{
"name": "Updated Product Name",
"price": 129.99
}

```

#### Delete a product by ID (ADMIN only)

```
DELETE /product/{id}
Authorization: Bearer generated_jwt_here
```
## Database

The application uses PostgreSQL by default. Configure in application.properties:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/spring_auth
spring.datasource.username=your_user
spring.datasource.password=your_password
api.security.token.secret=${JWT_SECRET:your_jwt_secret}
```

## Running the Application

Clone the repository:
```
git clone https://github.com/tulioanesio/Auth-Spring.git
```

Navigate to the project folder:

```
cd Auth-Spring
```

Build and run, the application will be available at:
```
http://localhost:8080
```
