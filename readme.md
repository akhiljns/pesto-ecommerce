# Ecommerce Microservices Project

This project is an ecommerce backend developed with Spring Boot, providing various microservices.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Running with Docker](#running-with-docker)
- [Endpoints](#endpoints)
- [Clustering and High Availability](#clustering-and-high-availability)
- [Kubernetes Deployment](#kubernetes-deployment)

## Prerequisites

Ensure that you have the following installed:

- Docker
- Java 11
- Maven

## Project Structure

- src/main/java/com.pesto.ecommerce/controller: This package contains classes responsible for handling HTTP requests and defining RESTful API endpoints. Controllers communicate with services to perform business logic.

- src/main/java/com.pesto.ecommerce/exception: This package holds custom exception classes. These exceptions can be thrown in the application and handled globally.

- src/main/java/com.pesto.ecommerce/model: Entity classes that represent the data model of the application. These classes often map to database tables.

- src/main/java/com.pesto.ecommerce/repository: JPA repositories for interacting with the database. They extend JpaRepository and provide CRUD operations for entity classes.

- src/main/java/com.pesto.ecommerce/service: Service classes containing business logic. Controllers delegate the processing of HTTP requests to these services.

- src/main/java/com.pesto.ecommerce/Application.java: The main class with the entry point of the Spring Boot application. It contains the main method to start the application.

- src/main/resources/application.properties: Configuration file containing application-wide properties. This includes database settings, server port, and other configurations.

- Dockerfile: Instructions for building a Docker image. Specifies the base image, dependencies, and how to run the application.

- pom.xml: Maven project configuration file. Includes dependencies, plugins, and build settings.

## Running with Docker

To run the project using Docker, follow these steps:

1. Clone the repository:

    ```bash
    git clone https://github.com/your-username/ecommerce.git
    cd ecommerce
    ```

2. Build the Docker image:

    ```bash
    docker build -t pesto-ecommerce-app .
    ```

3. Run the Docker container:

    ```bash
    docker run -p 8080:8080 -d pesto-ecommerce-app
    ```

   Replace `8080:8080` with the desired port mapping.

4. Access the application:

   Open your browser and navigate to [http://localhost:8080](http://localhost:8080).

## Endpoints

The APIs in this project are documented using Swagger. To explore and test the endpoints, follow these steps:

- Open your browser and navigate to [Swagger UI](http://localhost:8080/swagger-ui.html).
- You will be presented with an interactive Swagger documentation that lists all the available endpoints and provides a convenient way to test them.

## Clustering and High Availability

Explain how the system achieves clustering and high availability.

## Kubernetes Deployment

If applicable, provide instructions on how to deploy the system on Kubernetes.

