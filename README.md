﻿# Basic-Product-Management-Restful-API-Following-DDD-Principle
This project implements a basic **Product Management** RESTful API for an eCommerce platform using **Spring Boot** and **Domain-Driven Design (DDD)** principles. The application manages products while keeping business logic separate from infrastructure concerns, following a well-structured layered architecture.

## Table of Contents
- [Architecture Overview](#architecture-overview)
- [How to Run the Application](#how-to-run-the-application)
- [Endpoints](#endpoints)
- [Domain Model](#domain-model)
- [Application Layer](#application-layer)
- [Infrastructure Layer](#infrastructure-layer)
- [Technologies Used](#technologies-used)
- [Using the Product Management API with Postman](#using-the-product-management-api-with-postman)

## Architecture Overview

The project is structured into distinct layers, adhering to **Domain-Driven Design (DDD)** principles. Here's an overview of the architecture:

1. **API Layer** (`api`):
   - Handles HTTP requests and responses.
   - Exposes RESTful endpoints for product management.
   - Does not contain business logic but delegates work to the application layer.

2. **Application Layer** (`managing.application`):
   - Contains the business logic and coordinates between the domain and infrastructure layers.
   - Handles product creation, updating, and deletion use cases.
   - Serves as the bridge between the API layer and the domain model.

3. **Domain Layer** (`managing.domain`):
   - Contains the core business logic and rules.
   - Encapsulates the **Product** entity, ensuring business invariants such as positive prices and non-negative stock.

4. **Infrastructure Layer** (`managing.infrastructure`):
   - Manages persistence and interaction with the database.
   - Implements the repository pattern using **Spring Data JPA** for the **Product** entity.

### Directory Structure
```bash
src/
├── api/
│   ├── ProductController.java
│   ├── ProductDTO.java
├── managing/
│   ├── application/
│   │   ├── ProductApplicationService.java
│   ├── domain/
│   │   ├── Product.java
│   ├── infrastructure/
│       ├── ProductRepository.java
└── ProductManagementRestfulApiApplication.java
```


## How to Run the Application

### Prerequisites
- **Java 17** or higher installed.
- **Maven** or **Gradle** to manage dependencies.
- IDE (like IntelliJ IDEA, Eclipse, or VS Code) or command-line interface for running the Spring Boot application.

### Steps to Run the Application

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd <repository-directory>
   ```
  
2. **Build the application**:
   Use **Maven** to build the application:
   ```bash
   mvn clean install
   ```

3. **Run the application**:
   Run the Spring Boot application via Maven:
   ```bash
   mvn spring-boot:run
   ```
   Alternatively, you can run the application directly from your IDE.
   
4. **Access the API**:
   Once the application is running, access the API via the following URL:
   ```bash
   http://localhost:8080/products
   ```
   You can use tools like **Postman** or **curl** to interact with the API.




## Endpoints

### Product Endpoints
- `GET /products`: Retrieve all products.
- `GET /products/{id}`: Retrieve a single product by its ID.
- `POST /products`: Create a new product.
- `PUT /products/{id}`: Update an existing product by its ID.
- `DELETE /products/{id}`: Delete a product by its ID.
- `PATCH /products/{id}/update-stock`: Update the stock quantity of a product.




## Domain Model

The **domain model** defines the core business rules for the `Product` entity. The `Product` class contains the following fields:
- `id`: Unique identifier (auto-generated).
- `name`: Product name (must be unique).
- `description`: A description of the product (optional).
- `price`: Product price (must be positive).
- `stockQuantity`: Available stock quantity (must be non-negative).
- `category`: Category of the product.
- `createdAt`: Timestamp when the product was created (auto-generated).
- `updatedAt`: Timestamp when the product was last updated (auto-updated).

### Business Rules
- **Price must be positive**: The price is validated to ensure it’s greater than zero.
- **Stock must be non-negative**: Stock quantity cannot be negative.
- **Unique Product Name**: Product names must be unique, enforced by database constraints.




## Application Layer

The **Application Layer** contains the business workflows related to managing products. These services encapsulate use cases such as creating, updating, and managing stock for products.

### `ProductApplicationService`
The `ProductApplicationService` acts as a bridge between the API and the domain layer. It provides methods for creating, updating, and deleting products, ensuring all business rules are respected.




## Infrastructure Layer

The **Infrastructure Layer** handles persistence and data access, leveraging **Spring Data JPA**. The repository layer abstracts away database interactions and allows the application layer to focus on business logic.

### `ProductRepository`
The `ProductRepository` extends `JpaRepository<Product, Long>`, providing CRUD operations for managing products:

```java
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Custom queries (if any) can be added here
}
```
This repository provides the basic CRUD methods like save(), findById(), deleteById(), etc., out of the box.



## Technologies Used

- **Spring Boot**: For building the RESTful API.
- **Spring Data JPA**: For database access and persistence.
- **H2 In-memory Database**: A lightweight, in-memory database for development and testing.
- **Maven**: For dependency management and building the project.
- **Java 17**: As the programming language.


## Using the Product Management API with Postman

Below are examples of how you can use the Product Management RESTful API via **Postman**.

### 1. Creating a Product
- **Method**: `POST`
- **URL**: `http://localhost:8080/products`
- **Body**: (JSON)
  ```json
  {
    "name": "Laptop",
    "description": "A high-end gaming laptop",
    "price": 1500.00,
    "stockQuantity": 10,
    "category": "Electronics"
  }
- **Headers**:
  - Content-Type: `application/json`

### 2. Retrieving All Products
- **Method**: `GET`
- **URL**: `http://localhost:8080/products`

### 3. Retrieving a Product by ID
- **Method**: `GET`
- **URL**: `http://localhost:8080/products/{id}`
  - Replace `{id}` with the product ID you want to retrieve (e.g., `1`).

### 4. Updating a Product
- **Method**: `PUT`
- **URL**: `http://localhost:8080/products/{id}`
  - Replace `{id}` with the product ID you want to update.
- **Body**: (JSON)
  ```json
  {
    "name": "Gaming Laptop",
    "description": "A high-end gaming laptop with new features",
    "price": 1600.00,
    "stockQuantity": 8,
    "category": "Electronics"
  }
- **Headers**:
  - Content-Type: `application/json`

### 5. Deleting a Product
- **Method**: `DELETE`
- **URL**: `http://localhost:8080/products/{id}`
  - Replace `{id}` with the product ID you want to delete.

### 6. Updating Product Stock
- **Method**: `PATCH`
- **URL**: `http://localhost:8080/products/{id}/update-stock`
  - Replace `{id}` with the product ID for which you want to update the stock.
- **Body**: (JSON)
  ```json
  25
- **Headers**:
  - Content-Type: `application/json`
 
### Steps to Use Postman
1. **Open Postman**: Launch the Postman application.
2. **Create a New Request**: Click on "New" -> "Request".
3. **Select Method and Enter URL**: Choose the appropriate HTTP method (`GET`, `POST`, `PUT`, `PATCH`, `DELETE`) and paste the URL.
4. **Add Headers**: For `POST`, `PUT`, and `PATCH` methods, make sure to add `Content-Type: application/json` in the Headers tab.
5. **Add Body**: For requests like `POST`, `PUT`, and `PATCH`, add the JSON body under the Body tab, selecting "raw" and choosing JSON format.
6. **Send Request**: Click the "Send" button to make the request.

You can use the above examples to interact with the API and manage products using **Postman**. Each example represents a typical use case for managing product data in the eCommerce platform.


##
That’s it! The project is now structured according to Domain-Driven Design principles, and you can run it locally and interact with the API to manage products.
