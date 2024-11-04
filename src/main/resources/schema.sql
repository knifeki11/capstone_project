
DROP TABLE IF EXISTS review CASCADE;
DROP TABLE IF EXISTS suggestion CASCADE;
DROP TABLE IF EXISTS document CASCADE;
DROP TABLE IF EXISTS process CASCADE;
DROP TABLE IF EXISTS authority CASCADE;
DROP TABLE IF EXISTS "user" CASCADE;
DROP TABLE IF EXISTS tenant CASCADE;

CREATE TABLE tenant (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        description VARCHAR(255),
                        admin BOOLEAN NOT NULL,
                        active BOOLEAN NOT NULL,
                        created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        last_modified_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE "user" (
                          id SERIAL PRIMARY KEY,
                          username VARCHAR(50) NOT NULL UNIQUE,
                          firstname VARCHAR(50) NOT NULL,
                          lastname VARCHAR(50) NOT NULL,
                          password VARCHAR(255) NOT NULL,
                          email VARCHAR(100) NOT NULL UNIQUE,
                          phone VARCHAR(20),
                          tenant_id INT REFERENCES tenant(id),
                          active BOOLEAN NOT NULL,
                          created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          last_modified_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE authority (
                           id SERIAL PRIMARY KEY,
                           username VARCHAR(50) NOT NULL REFERENCES "user"(username),
                           authority VARCHAR(50) NOT NULL,
                           active BOOLEAN NOT NULL,
                           created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           last_modified_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE process (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         description VARCHAR(255),
                         tenant_id INT REFERENCES tenant(id),
                         active BOOLEAN NOT NULL,
                         created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         last_modified_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE document (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          type VARCHAR(10) NOT NULL,
                          description VARCHAR(255),
                          tenant_id INT REFERENCES tenant(id),
                          active BOOLEAN NOT NULL,
                          created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          last_modified_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE suggestion (
                            id SERIAL PRIMARY KEY,
                            title VARCHAR(100) NOT NULL,
                            content TEXT NOT NULL,
                            user_id INT REFERENCES "user"(id),
                            process_id INT REFERENCES process(id),
                            status VARCHAR(50) NOT NULL,
                            active BOOLEAN NOT NULL,
                            created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            last_modified_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE review (
                        id SERIAL PRIMARY KEY,
                        suggestion_id INT REFERENCES suggestion(id),
                        user_id INT REFERENCES "user"(id),
                        comment TEXT,
                        rating DECIMAL(2, 1),
                        is_approved BOOLEAN NOT NULL,
                        active BOOLEAN NOT NULL,
                        created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        last_modified_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
