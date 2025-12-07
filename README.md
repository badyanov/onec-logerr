# OneC LogErr - Система сбора ошибок 1С

Приложение Spring Boot для сбора и хранения ошибок из программы 1С:Предприятие.

## Описание

**OneC LogErr** - это веб-приложение, которое принимает отчеты об ошибках от клиентов 1С в формате JSON и сохраняет их в
базе данных PostgreSQL. Приложение предоставляет возможность анализа и мониторинга ошибок.

## Технологии

- **Spring Boot 3.5.8**
- **Spring Data JPA**
- **PostgreSQL**
- **Flyway** (миграции БД)
- **Spring Security**
- **Thymeleaf**
- **Java 25**

## Настройка

### 1. База данных

Создайте базу данных PostgreSQL:

```sql
CREATE USER onec_user WITH PASSWORD 'your_password';
CREATE DATABASE onec_logerr;
GRANT ALL PRIVILEGES ON DATABASE onec_logerr TO onec_user;
```

Затем, необходимо подключиться к созданной базе данных и выполнить следующий скрипт:

```sql
GRANT ALL ON SCHEMA public TO onec_user;
```

### 2. Конфигурация

Отредактируйте `src/main/resources/application.yaml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/onec_logerr
    username: onec_user
    password: your_password

# Настройка порта сервера
server:
  port: 8080
```

Образец файла `application.yaml` смотрите в конце этой страницы

#### Настройка порта

Приложение по умолчанию запускается на порту **8080**. Для изменения порта:

**Вариант 1:** Измените в `application.yaml`:

```yaml
server:
  port: 9090  # или любой другой порт
```

**Вариант 2:** Передайте через командную строку:

```bash
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=9090
```

**Вариант 3:** Установите переменную окружения:

```bash
export SERVER_PORT=9090
mvn spring-boot:run
```

### 3. Запуск

```bash
# Сборка проекта
mvn clean install

# Запуск приложения
mvn spring-boot:run
```

Приложение будет доступно по адресу: http://localhost:8080

> **Примечание:** Если вы изменили порт в конфигурации, используйте соответствующий адрес
> (например, http://localhost:9090)

## Структура JSON

Приложение принимает JSON в следующем формате (согласно документации 1С):

```json
{
  "time": "2024-01-01T12:00:00Z",
  "id": "unique-error-id",
  "clientInfo": {
    "platformType": "Windows",
    "appVersion": "8.3.20.1234",
    "appName": "1C:Предприятие"
  },
  "systemInfo": {
    "osVersion": "Windows 10",
    "fullRAM": 16777216,
    "freeRAM": 8388608,
    "processor": "Intel Core i7",
    "useragent": "Mozilla/5.0..."
  },
  "errorInfo": {
    "userDescription": "Описание ошибки пользователем",
    "systemErrorInfo": {
      "clientStack": "Стек клиента",
      "serverStack": "Стек сервера",
      "systemCrash": false
    }
  }
}
```

## UML диаграмма

UML диаграмма классов доступна в файле [doc/uml/OneCLogErrUML.puml](doc/uml/OneCLogErrUML.puml)](doc/uml/design.puml).

Описание структуры данных приложения доступно в документации к
платформе [https://its.1c.eu/db/v8327doc#bookmark:dev:TI000002262](Глава 31.3.6 - Отчет об ошибке (8.3.27))

## API Endpoints

todo...

## Образец файла `application.yaml`

```yaml
# Server Configuration
server:
  port: 80

spring:
  application:
    name: OneC LogErr

  # Database Configuration
  datasource:
    url: jdbc:postgresql://localhost:5432/onec_logerr
    username: "onec_user"
    password: "********"
    driver-class-name: org.postgresql.Driver

  # JPA Configuration
  jpa:
    hibernate:
      ddl-auto: validate
    show-sql: true
    properties:
      hibernate:
        format_sql: true
        dialect: org.hibernate.dialect.PostgreSQLDialect

  # Flyway Configuration
  flyway:
    enabled: true
    locations: classpath:db/migration
    baseline-on-migrate: true

# Logging Configuration
logging:
  level:
    root: INFO
    org.hibernate.SQL: DEBUG
    org.hibernate.SQL.type.descriptor.sql.BasicBinder: TRACE
```