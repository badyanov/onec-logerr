-- V2: Пользователи и роли

-- Перечисление (ENUM) для ролей доступа
CREATE TYPE user_roles AS ENUM ('ROLE_ADMINISTRATOR', 'ROLE_USER', 'ROLE_READONLY');

-- Таблица для пользователей
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role user_roles NOT NULL,
    email VARCHAR(100),
    telegram VARCHAR(100),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
