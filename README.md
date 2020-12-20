# spring-boot-webapp-example
A simple Spring Boot web application example.

This example presents a web application for the sending and viewing of jokes, along with a like-dislike system for ranking jokes.

The application illustrates the use of:
  1. Spring Boot for the development of a simple web application
  2. PostgreSQL as a datbase for data storage
  3. Flyway for the definition of the database structure
  4. Spring Data JPA for connecting and interacting with the database 
  5. Spring Security for simple authentication purposes

By default, the application connects to a PostgreSQL database on the 5432 port with the username *postgres* and password *postgres*. This can be changed in the application.properties and flyway.properties configuration files if necessary. 

The application will attempt to fill the database with initial test data on application startup. Due to defined database constraints, subsequent application startups after the first one will result in a violation of those constraints and an exception. If repeated application startups are required, simply comment-out or update the *spring.datasource.initialization-mode=always* property in  the application.properties file.
