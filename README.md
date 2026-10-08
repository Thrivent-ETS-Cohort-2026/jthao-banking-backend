# JTHAO-BANKING-BACKEND


## Technologies
Java
Spring Boot
Spring Data JPA
Hibernate
PostgreSQL
Maven
Docker
Docker Compose
Swagger / OpenAPI

## Prerequisites

Before installing the application, make sure you have the following installed:

Java

This application requires Java.

Check your Java installation:

java -version

If Java is not installed, install a compatible JDK version (JDK 17 +).

## Git

Check your Git installation:

git --version

## Docker

Docker is used to run the PostgreSQL database.

Check your Docker installation:

docker --version
docker compose version

You will need Docker Desktop running before starting the database.


## Installation
1. Clone the repository

Clone the repository and navigate into the project:

git clone <https://github.com/Thrivent-ETS-Cohort-2026/jthao-banking-backend.git>

cd <YOUR_PROJECT_DIRECTORY>

## Starting Postgresql
The PostgreSQL database is provided through Docker Compose.

Navigate to the directory containing the PostgreSQL Docker Compose file:

`cd src/db`

Start the database:
```bash
docker compose up -d
```
The -d option runs the container in detached mode, allowing the terminal to be used for other commands.

Verify that the container is running:
```bash
docker ps
```
You should see the PostgreSQL container listed.

Next open a new terminal at the root folder.

Run `docker compose up --build`.
The `--build` option tells Docker to rebuild the application image before starting the containers.

After the application starts, it should be available at:

http://localhost:8080

## API Documentation

The application provides API documentation through Swagger/OpenAPI.

Once the application is running, open:

http://localhost:8080/swagger-ui/index.html

Swagger allows you to view the available endpoints and send requests to the API directly from your browser.





