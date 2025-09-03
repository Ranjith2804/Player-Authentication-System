<h1 align="center">Spring Boot JWT Authentication Service</h1>

<p align="center">
  A secure, stateless, and scalable backend service for user authentication and authorization.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-blue.svg" alt="Java 17">
  <img src="https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg" alt="Spring Boot 3">
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
-   ✅ **Clean Architecture:** Follows the classic Controller-Service-Repository pattern.
-   ✅ **Ready to Run:** Minimal configuration needed to get started.

## API Endpoints

| Method | Endpoint             | Protection | Description                                        |
| :----- | :------------------- | :--------- | :------------------------------------------------- |
| `POST` | `/api/auth/register` | **Public** | Registers a new user.                              |
| `POST` | `/api/auth/login`    | **Public** | Authenticates a user and provides a JWT.           |
| `GET`  | `/api/data/me`       | **Secured**  | Accesses a protected resource for the current user. |

## Quick Start

### Prerequisites

-   JDK 17
-   Apache Maven
-   PostgreSQL

### Installation & Setup

1.  **Clone the repo:**
    ```sh
    git clone https://github.com/your-username/your-repo.git
    cd your-repo
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
