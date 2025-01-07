CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       login VARCHAR(30) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       firstname VARCHAR(60) NOT NULL,
                       lastname VARCHAR(60) NOT NULL,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       langue VARCHAR(20),
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       role_id BIGINT NOT NULL,
                       CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);
