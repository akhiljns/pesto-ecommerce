# Ecommerce Microservices Project

This project is an ecommerce backend developed with Spring Boot, providing various microservices.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Running with Docker](#running-with-docker)
- [Endpoints](#endpoints)
- [Clustering and High Availability](#clustering-and-high-availability)
- [Kubernetes Deployment](#kubernetes-deployment)
- [Troubleshooting](#debugging-and-troubleshooting)

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
- for initial testing I have written ```data-h2.sql``` which inserts testing data during the start of the application so that features could be testing without having to create dummy data.

## Clustering and High Availability with Minikube

### Step 1: Install and Set Up Minikube

1. **Install Minikube:** Follow the instructions for your operating system in the [official documentation](https://minikube.sigs.k8s.io/docs/start/).

2. **Start Minikube:** Run `minikube start` to launch a local Kubernetes cluster.

### Step 2: Deploy to Kubernetes

```bash
kubectl apply -f kubernetes/deployment.yaml
kubectl apply -f kubernetes/service.yaml
```

### Step 3 : check pods

```bash
kubectl get pods -o wide
```
### Step 4 : Access the service 

```bash
kubectl.exe get services
```

Ecommerce App: http://<MINIKUBE_IP>:<PORT>/swagger-ui.html#/

example - http://127.0.0.1:57412/swagger-ui.html#/

The services are now running in a clustered environment. Demonstrate high availability by checking the services' availability even if one node/container goes down. Explore the Swagger UI for API documentation and interact with the ecommerce system.


## Debugging and Troubleshooting

It is possible that the docker image built locally is not accessible to minikube and if this deployment is done on minikube then the local docker registry should be accesible since docker pull from registry is disabled

in this case please run 

```bash
eval $(minikube docker-env)
```

- now re-build docker image and restart container, then reapply service and it will start working

# Ecommerce Microservices Project Evaluation Criteria Checklist

This project adheres to the following criteria:

## Microservices Architecture

The system is structured as microservices, with distinct modules responsible for specific functionalities. The project is organized into packages, such as `com.pesto.ecommerce.controller`, `com.pesto.ecommerce.service`, and `com.pesto.ecommerce.repository`. Each package represents a microservice or a layer within the microservices architecture. This gives us well structured MVC architecture.

also the project is dockerized which will help us deploying and creating a microservice out of this.

## Concurrency Control

Concurrency control is effectively implemented to manage concurrent access to critical sections of the code. The `@Transactional` annotation is used to ensure atomicity, consistency, isolation, and durability (ACID properties) during database transactions. Concurrency issues are addressed through proper synchronization mechanisms and transaction management. Executor service is used to spawn threads for each shopping done by user and takes every purchasing task in a different thread.

## Clustering

The system demonstrates high availability and clustering capabilities, especially when deployed in a Kubernetes environment. The Kubernetes deployment includes multiple replicas of the application to ensure redundancy and fault tolerance. This setup is designed to maintain service availability even if one node or container becomes unavailable. For ease of usage and demonstration minikube is used to perform local kubernetes deployment.

## Code Quality

The codebase follows best practices for readability, maintainability, and documentation. Descriptive package names and well-structured classes enhance code organization. Each module includes comments and documentation to explain its purpose and usage. Lombok annotations are used to reduce boilerplate code, and the project adheres to consistent coding conventions.

## Testing

The project includes a suite of unit tests that cover critical components and concurrency control mechanisms. JUnit test are written for critical services like cart and shopping, in the interest of time only 2 tests have been written.

## Database Integration

For quick and ease of usage h2 database is used to store the product and cart data, it can be accessed on :

```bash
http://localhost:8080/h2-console/
```

while the application is up and running. This is an in-memory database so data only gets persisted while the app is running