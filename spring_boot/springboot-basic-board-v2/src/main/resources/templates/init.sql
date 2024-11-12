CREATE DATABASE java_basic
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

CREATE TABLE member (
                        id BIGINT NOT NULL AUTO_INCREMENT,
                        user_id VARCHAR(30) NOT NULL UNIQUE,
                        password VARCHAR(200),
                        user_name VARCHAR(10) NOT NULL,
                        role VARCHAR(20) NOT NULL DEFAULT 'ROLE_USER',
                        PRIMARY KEY (id)
);

CREATE TABLE article (
                         id BIGINT NOT NULL AUTO_INCREMENT,
                         user_id VARCHAR(30) NOT NULL,
                         title VARCHAR(100) NOT NULL,
                         content TEXT NOT NULL,
                         created DATETIME DEFAULT CURRENT_TIMESTAMP,
                         updated DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         file_path VARCHAR(500),
                         PRIMARY KEY (id)
);