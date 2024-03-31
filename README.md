
PRODUCT-CRM-APP

Table of Contents
-----------------
* [Database-model-diagram](#database-model-diagram)
* [Management-tool](#management-tool)
* [Installing](#installing)
* [Running using Docker](#running-using-docker)
* [Change database variable](#change-databse-variable)
* [Initial values](#initial-values)

## Database-model-diagram

You can see by the link:
https://drive.google.com/file/d/1OUQlhyw9xrH4TTLWbrugR3qfdGW-pxsG/view?usp=sharing

## Management-tool

I use Trello application and divide mini-task. You can see by the link:
https://trello.com/invite/b/M1avVpt8/ATTI159110ec9656b64d4c590aab09bb42e3270DD531/product-crm

## Installing

### Prerequisites

* Maven
* Java 17 (JDK)
* Docker for running service inside container (You can also run it without docker)


## Running using Docker

```shell
docker-compose build
docker-compose up
```

All required ports are mapped to localhost.

REST API is accessible via port 9001.

http://localhost:9002/swagger/index.html - serves an API documentation.


## Change database variable
   Step 1:

path: src/main/resources/application-dev.yml

```shell
spring:
  datasource:
      url: ${SPRING_DATASOURCE_URL:jdbc:postgresql://localhost:5432/task}
      username: ${DB_USERNAME:postgres}
      password: ${DB_PASSWORD:root123}
      driverClassName: org.postgresql.Driver
```

Step 2:

path: compose.yaml

Change variables: SPRING_DATASOURCE_URL, DB_USERNAME, DB_PASSWORD, POSTGRES_USER, POSTGRES_PASSWORD, POSTGRES_DB

## Initial values:

**User:** 

username:worker, password:111
username:manager, password:111

