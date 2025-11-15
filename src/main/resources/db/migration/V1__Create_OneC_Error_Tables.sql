-- V1: Создание схемы базы данных

-- Таблица для хранения бинарных данных (скриншоты, доп. файлы, дампы)
CREATE TABLE binary_files (
    id BIGSERIAL PRIMARY KEY,
    storage_path VARCHAR(255) NOT NULL UNIQUE, -- Путь к файлу в хранилище
    original_file_name VARCHAR(255),          -- Оригинальное имя файла
    file_size BIGINT,
    mime_type VARCHAR(100),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Таблица для дампов
CREATE TABLE dumps (
    id BIGSERIAL PRIMARY KEY,
    type VARCHAR(50),                          -- тип дампа (mini, full)
    reason_for_no_dump VARCHAR(100),           -- причина, по которой дамп не сформирован
    binary_file_id BIGINT REFERENCES binary_files(id), -- Ссылка на файл дампа
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Основная таблица ошибок, включающая все вложенные данные
CREATE TABLE onec_errors (
    id BIGSERIAL PRIMARY KEY,
    error_id UUID NOT NULL UNIQUE,
    time TIMESTAMP NOT NULL,

    -- Поля из ClientInfo и SystemInfo
    client_platform_type VARCHAR(100), -- Свойство 'СистемнаяИнформация.ТипПлатформы' клиента
    client_app_version VARCHAR(100),   -- Свойство 'СистемнаяИнформация.ВерсияПриложения' клиента
    client_app_name VARCHAR(255),      -- Метод ИмяПриложения()
    os_version VARCHAR(255),           -- Свойство 'СистемнаяИнформация.ВерсияОС'
    full_ram BIGINT,                   -- Всего оперативной памяти
    free_ram BIGINT,                   -- Свободно оперативной памяти
    processor TEXT,                    -- Свойство 'СистемнаяИнформация.Процессор'
    useragent TEXT,                    -- user-agent веб-браузера
    client_id VARCHAR(255),            -- Свойство 'СистемнаяИнформация.ИдентификаторКлиента'

    -- Поля из SessionInfo
    user_name VARCHAR(255),
    data_separation VARCHAR(255),      -- Значения разделителей текущего сеанса
    platform_interface_language_code VARCHAR(10),
    configuration_interface_language_code VARCHAR(10),
    session_locale_code VARCHAR(10),   -- Код локализации сеанса

    -- Поля из InfoBaseInfo
    infobase_locale_code VARCHAR(10),  -- Код локализации информационной базы

    -- Поля из ServerInfo
    server_platform_type VARCHAR(100), -- Тип платформы сервера
    server_app_version VARCHAR(100),   -- Версия кластера серверов
    dbms VARCHAR(50),                  -- Тип СУБД

    -- Поля из ConfigInfo
    config_name VARCHAR(255),
    config_description TEXT,
    config_version VARCHAR(100),
    compatibility_mode VARCHAR(100),   -- Режим совместимости конфигурации
    config_hash VARCHAR(32),           -- Хеш конфигурации (MD5)
    change_enabled BOOLEAN,            -- Возможность изменения конфигурации

    -- Поля из ErrorInfo, SystemErrorInfo, ApplicationErrorInfo
    user_description TEXT,             -- Описание, написанное пользователем
    client_stack TEXT,                 -- Текст стека клиентского приложения
    client_stack_hash VARCHAR(32),     -- Хеш текста стека клиентского приложения (MD5)
    server_stack TEXT,                 -- Текст стека кластера серверов
    server_stack_hash VARCHAR(32),     -- Хеш текста стека кластера серверов (MD5)
    system_crash BOOLEAN,              -- Признак аварийного завершения
    stack_hash VARCHAR(32),            -- Хеш стека прикладной ошибки

    -- Основные связи и доп. данные
    screenshot_file_id BIGINT REFERENCES binary_files(id), -- Ссылка на скриншот
    additional_data TEXT,              -- Дополнительные данные в формате JSON
    dump_id BIGINT REFERENCES dumps(id), -- Ссылка на дамп
    
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Таблица для расширений конфигурации
CREATE TABLE config_extensions (
    onec_error_id BIGINT NOT NULL REFERENCES onec_errors(id) ON DELETE CASCADE,
    extension_name VARCHAR(255),
    extension_hash VARCHAR(32)
);

-- Таблица для отключенных расширений конфигурации
CREATE TABLE config_disabled_extensions (
    onec_error_id BIGINT NOT NULL REFERENCES onec_errors(id) ON DELETE CASCADE,
    extension_name VARCHAR(255),
    extension_hash VARCHAR(32)
);

-- Таблица для описаний ошибок приложения
CREATE TABLE application_error_descriptions (
    onec_error_id BIGINT NOT NULL REFERENCES onec_errors(id) ON DELETE CASCADE,
    description TEXT,
    category VARCHAR(255)
);

-- Таблица для стека вызовов приложения
CREATE TABLE application_stack_descriptions (
    onec_error_id BIGINT NOT NULL REFERENCES onec_errors(id) ON DELETE CASCADE,
    module_name VARCHAR(255),          -- Имя модуля
    line_number INTEGER,               -- Номер строки
    line_content TEXT                  -- Содержимое строки кода
);

-- Таблица связи для дополнительных файлов
CREATE TABLE onec_error_additional_files (
    onec_error_id BIGINT NOT NULL REFERENCES onec_errors(id) ON DELETE CASCADE,
    binary_file_id BIGINT NOT NULL REFERENCES binary_files(id) ON DELETE CASCADE,
    PRIMARY KEY (onec_error_id, binary_file_id)
);

-- Индекс для фильтрации по времени
CREATE INDEX idx_onec_errors_time ON onec_errors(time);

-- Индексы по хешам для быстрой группировки и поиска дубликатов
CREATE INDEX idx_onec_errors_client_stack_hash ON onec_errors(client_stack_hash);
CREATE INDEX idx_onec_errors_server_stack_hash ON onec_errors(server_stack_hash);
CREATE INDEX idx_onec_errors_stack_hash ON onec_errors(stack_hash);

-- Индексы по внешним ключам для ускорения JOIN и операций ON DELETE CASCADE
CREATE INDEX idx_config_extensions_error_id ON config_extensions(onec_error_id);
CREATE INDEX idx_config_disabled_extensions_error_id ON config_disabled_extensions(onec_error_id);
CREATE INDEX idx_application_error_descriptions_error_id ON application_error_descriptions(onec_error_id);
CREATE INDEX idx_application_stack_descriptions_error_id ON application_stack_descriptions(onec_error_id);

-- Полнотекстовый поиск по описанию пользователя
CREATE INDEX idx_onec_errors_user_description_fts ON onec_errors USING gin(to_tsvector('russian', user_description));
