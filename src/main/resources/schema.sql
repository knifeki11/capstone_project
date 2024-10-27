CREATE TABLE IF NOT EXISTS "app_user" (
                                          id BIGSERIAL PRIMARY KEY,
                                          username VARCHAR(255) UNIQUE NOT NULL,
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone VARCHAR(50),
    tenant_id BIGINT,
    active BOOLEAN DEFAULT TRUE,
    created_date TIMESTAMP,
    last_modified_date TIMESTAMP
    );
