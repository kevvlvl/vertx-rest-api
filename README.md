# Vert.x Rest API

Here is a REST api built using vert.x concept of Routers and sub routers

## Endpoints

- curl http://localhost:8081/api/v1/health
- curl http://localhost:8081/api/v1/fin?symbolName=ABC.Y

## Build and Run

Build the artifact
- ```./mvnw clean package```

Run the Vertx application
- ``` java -jar target/Vertx-Rest-API-1.0-SNAPSHOT.jar```