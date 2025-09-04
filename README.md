<h1 align="center">Spring Boot JWT Authentication Service</h1>

<p align="center">
  A secure, stateless, and scalable backend service for user authentication and authorization.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-blue.svg" alt="Java 21">
  <img src="https://img.shields.io/badge/Spring%20Boot-3.5.5-brightgreen.svg" alt="Spring Boot 3.5.5">
  <img src="https://img.shields.io/badge/Security-JWT-critical" alt="JWT Security">
  <img src="https://img.shields.io/badge/Database-PostgreSQL-blue.svg" alt="PostgreSQL">
</p>

## Overview

This project provides a complete, production-ready template for a secure REST API using **Java**, **Spring Boot**, and **JSON Web Tokens (JWT)**. It handles the entire authentication flow, from user registration with hashed passwords to validating tokens for protected resource access.

Built with a clean, layered architecture, this service is designed to be both powerful and easy to understand.

## Features

-   ✅ **Secure Registration:** `POST /api/auth/register` endpoint with BCrypt password hashing.
-   ✅ **Stateless Authentication:** `POST /api/auth/login` endpoint issues signed, expiring JWTs.
-   ✅ **Robust Authorization:** A custom `JwtAuthFilter` secures endpoints by validating the `Authorization: Bearer` token.
-   ✅ **Architecture:** Follows the classic Controller-Service-Repository pattern.
-   ✅ **Ready to Run:** Minimal configuration needed to get started.

### Technology Stack

-   **Backend:** Java 21, Spring Boot 3.5.5
-   **Security:** Spring Security 6, JSON Web Tokens (JWT)
-   **Database:** Spring Data JPA, Hibernate, PostgreSQL
-   **Build & Dependencies:** Apache Maven

## API Endpoints

| Method | Endpoint             | Protection | Description                                        |
| :----- | :------------------- | :--------- | :------------------------------------------------- |
| `POST` | `/api/auth/register` | **Public** | Registers a new user.                              |
| `POST` | `/api/auth/login`    | **Public** | Authenticates a user and provides a JWT.           |
| `GET`  | `/api/data/me`       | **Secured**  | Accesses a protected resource for the current user. |

### Security Flow

Security is managed by a custom-configured Spring Security filter chain:

1.  **Public Endpoints:** Requests to `/api/auth/**` (login/register) are permitted for all users.
2.  **JWT Authentication:** On successful login, a signed, stateless JWT is generated and returned to the client.
3.  **Protected Endpoints:** For all other requests, a custom `JwtAuthFilter` intercepts the request.
    -   It validates the `Authorization: Bearer <token>` header.
    -   It verifies the token's signature and expiration.
    -   If valid, it populates the `SecurityContext`, authenticating the user for the duration of the request.


## Quick Start

### Prerequisites

-   JDK 21
-   Apache Maven
-   PostgreSQL

### Installation & Setup

1.  **Clone the repo:**
    ```sh
    git clone https://github.com/Ranjith2804/Player-Authentication-System.git
    cd Player-Authentication-System
    ```
2.  **Create a PostgreSQL database:**
    ```sql
    CREATE DATABASE playerdb;
    ```
3.  **Configure `application.properties`:**
    Update `spring.datasource.username` and `spring.datasource.password` with your credentials.

4.  **Run the application:**
    ```sh
    mvn spring-boot:run
    ```

The API will be available at `http://localhost:8080`.
