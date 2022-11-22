# Bookweb 

Application for book lovers, where users can add new books, mark reading books, mark read books, or books to read.
Each book can be rated and added to user private bookcase.

Users can add publishers and authors information and upload covers for books.

Access to application is secured and available only to registered users.



Technologies used: **Java, Spring Boot, Spring Security, Spring MVC,
Hibernate, MySQL, JSP, JSTL, CSS and Bootstrap framework**.


## local development 

Start MySQL database docker instance
```shell
docker-compose up -d
```

And then start application with maven

```shell
mvn clean spring-boot:run
```