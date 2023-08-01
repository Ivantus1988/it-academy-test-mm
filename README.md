# Description

Многомодульный maven проект (3-level three-module development architecture).
Endpoint для добавления пользователя и endpoint для получения всех пользователей.

### Technology stack:
- Java 17
- Spring 6
- Hibernate 6
- MySQL 8
- Log4j2
- Flyway Migration
- Docker


To start application with MySQL DB in Docker container:
docker-compose.local up

##### API Endpoints:
- GET /users find all users (with default filter parameters)
- POST /user/new save new user
