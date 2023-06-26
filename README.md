# User GitHub

## Service that integrates with GitHub API

### Functionalities:

- Search for a user profile


### Technologies, frameworks and libraries used
- [Kotlin 1.7.22](https://kotlinlang.org/docs/home.html)
- [Gradle 7.0+](https://docs.gradle.org/7.0/userguide/userguide.html)
- [Spring Boot (2.7.5)](https://spring.io/projects/spring-boot)
- [JUnit5](https://junit.org/junit5/docs/current/user-guide/), [Mockito](https://site.mockito.org/), [Datafaker](https://www.datafaker.net/)
- [Swagger (openapi v1.6.14)](https://springdoc.org/)
- [Docker](https://www.docker.com/), [Docker Compose](https://docs.docker.com/compose/)

### Service response example (API)
```
{
    "login": "detowhey",
    "profileName": "Henrique Almeida",
    "avatarUrl": "https://avatars.githubusercontent.com/u/56054329?v=4",
    "urlProfile": "https://github.com/detowhey",
    "createDate": "02/10/2019 00:34",
    "numberPublicRepos": 16
}
```
Fetch user data from GitHub according to your login.


### Running the application

#### Services
- API

#### Application up
```
gradle bootRun
```

### URL

http://localhost:8080/api/v1

### Documentation with Swagger

http://localhost:8080/swagger-ui/index.html

#### Important

To use the Kotlin programming language, you need to have the JDK (Java Development Kit) installed. For this application, [JDK 17](https://www.oracle.com/br/java/technologies/downloads/#java17) is used.
