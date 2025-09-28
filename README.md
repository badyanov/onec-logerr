# OneC LogErr - Система сбора ошибок 1С

Приложение Spring Boot для сбора и хранения ошибок из программы 1С:Предприятие.

## Описание

**OneC LogErr** - это веб-приложение, которое принимает отчеты об ошибках от клиентов 1С в формате JSON и сохраняет их в
базе данных PostgreSQL. Приложение предоставляет возможность анализа и мониторинга ошибок.

## Технологии

- **Spring Boot 3.6.0**
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
CREATE DATABASE onec_logerr;
CREATE USER onec_user WITH PASSWORD 'your_password';
GRANT ALL PRIVILEGES ON DATABASE onec_logerr TO onec_user;
```

### 2. Конфигурация

Отредактируйте `src/main/resources/application.yml`:

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

#### Настройка порта

Приложение по умолчанию запускается на порту **8080**. Для изменения порта:

**Вариант 1:** Измените в `application.yml`:

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

UML диаграмма классов доступна в файле `doc/uml/design.puml`.

## API Endpoints

todo...