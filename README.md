
# Library

## Getting Started

To install this application, run the following command

> git clone https://github.com/oktadeveloper/okta-spring-boot-2-angular-5-example.git

### Maven

* build: mvn clean package
* run: mvn spring-boot:run

### Gradle

* build: gradle build
* run: gradle bootRun

### Authorization 

Use Basic Authorization

login: user

password: user_password

### Swagger 

> http://localhost:8080/swagger-ui.html

### Book api examples

**Get all books**

GET: /book

**Get book by id**

GET: /book/{book_id}

** Create book ** 

POST: /book

Body
{
    "title": "TestName",
    "isbn": "TestName",
    "genre": "POEM"
}

** Update book **

PUT: /book

Body
{
    "title": "TestName",
    "isbn": "TestName",
    "genre": "POEM"
}

### Author api examples

**Get all authors**

GET: /author

**Get author short info**

GET: /author/info/short//{author_id}

**Get author by id**

GET: /author/{author_id}

** Create author ** 

POST: /author

Body
{
    "first_name": "TestName",
    "last_name": "TestName",
    "sex": "MALE",
    "birth_date":"12.09.1900"
}

** Update author **

PUT: /book

Body
{
    "first_name": "TestName",
    "last_name": "TestName",
    "sex": "MALE",
    "birth_date":"12.09.1900"
}