# Baraka Banking Application using Java8, Spring Boot, Spring Security and H2 DB

RESTful API to simulate simple banking operations.

## Requirements

* CRUD operations for customers and accounts.
* Support new account creation, delete account , find All Account List, deposits, withdrawals, transfer, International
  transfers.

## Getting Started

1. Checkout the project from GitHub

```
https://github.com/mohdtausifraza/baraka-bank.git

```

2. Enable Lombok support on your IDE

Refer to the following link for instructions:

```
https://projectlombok.org/setup/eclipse

```

3. Open IDE of your choice and Import as existing maven project in your workspace

```
- Import existing maven project
- Run mvn clean install
- If using STS, Run As Spring Boot App

```

4. Default port for the api is 8080

### Prerequisites

* Java 8
* Spring Tool Suite 4 or similar IDE
* [Maven](https://maven.apache.org/) - Dependency Management

### Maven Dependencies

```
spring-boot-starter-actuator
spring-boot-starter-data-jpa
spring-boot-starter-security
spring-boot-starter-web
spring-boot-devtools
h2 - Inmemory database
lombok - to reduce boilerplate code
springfox-swagger2
springfox-swagger-ui
spring-boot-starter-test
spring-security-test

```

## Swagger

Please find the Rest API documentation in the below url

```
http://localhost:8080/baraka-bank/swagger-ui.html

```

## H2 In-Memory Database

Make sure to use jdbc:h2:mem:testdb as your jdbc url. If you intend to you use custom database name, please define
datasource properties in application.yml

```
http://localhost:8080/baraka-bank/h2-console/

```

## Testing the Bank APP Rest Api

1. Please use the Swagger url to perform CRUD operations.

## Authors

* **Mohd Tausif Raza**

