# Lab 17 starter: Spring Security (users in DB) and JWT

This project is a working, **unsecured** product CRUD API. Follow the Lab 17 manual to secure it step by step.

Provided:

| File | Role |
| --- | --- |
| `model/Product.java` | Product record |
| `repository/ProductRepository.java` | Spring Data JDBC repository |
| `service/ProductService.java` | Product business logic (the only class that uses the repository) |
| `controller/ProductController.java` | `GET/POST/PUT/DELETE /api/v1/products`, delegates to `ProductService` |
| `resources/schema.sql`, `data.sql` | Create the `product` table and insert 6 products |

Before you start:

1. Put your MySQL URL, username and password in `src/main/resources/application.properties`.
2. Run the app (`./mvnw spring-boot:run`, or `mvnw.cmd spring-boot:run` on Windows).
3. Check that `GET http://localhost:8080/api/v1/products` returns 6 products **without any credentials**: that is what this lab fixes.

`mvn test` runs against an in-memory H2 database, so it does not need MySQL.

Look for the `TODO` comments in `pom.xml`, `schema.sql` and `data.sql`.
