Spring Data JDBC Showcase
=========================

This example shows basic usage concepts of [Spring Data JDBC](http://projects.spring.io/spring-data-jdbc).

The commit order from initial to latest guides you through:

1. STEP 1: Initial setup and configuration.
2. STEP 2: Custom `Repository` implementation
3. STEP 3: Create object and generate key
4. STEP 4: Multiple ways of inserting records
5. STEP 5: basic CRUD operation
6. STEP 6: Exception handling and Transactional operations

In order to run this example you need:
1- Maven 3+
2- IDE
3- Basic understanding of Spring Framework

The application is meant to work with postgresql database but you can change it in jdbc-config.xml.
You can find the database structure inside: src/main/resources/database-postgresql/ride_tracker.sql
Use pg_dump to import it, below is an exemple:

CREATE DATABASE ride_tracker WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'C';
psql -h localhost -U postgres
\i 'C:/Users/EAS/Desktop/ride_tracker.sql'