CREATE TABLE user (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(100),
                      email VARCHAR(100),
                      userId VARCHAR(100),
                      password VARCHAR(100)
);

INSERT INTO user (name, email, userId, password) VALUES ('Alice', 'alice@example.com', 'alice123', 'password1');
INSERT INTO user (name, email, userId, password) VALUES ('Bob', 'bob@example.com', 'bob456', 'password2');
INSERT INTO user (name, email, userId, password) VALUES ('Charlie', 'charlie@example.com', 'charlie789', 'password3');
INSERT INTO user (name, email, userId, password) VALUES ('David', 'david@example.com', 'david321', 'password4');
INSERT INTO user (name, email, userId, password) VALUES ('Eve', 'eve@example.com', 'eve654', 'password5');