CREATE TABLE user (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(100),
                      email VARCHAR(100)
);

INSERT INTO user (name, email) VALUES
                                   ('Alice', 'alice@example.com'),
                                   ('Bob', 'bob@example.com'),
                                   ('Charlie', 'charlie@example.com'),
                                   ('David', 'david@example.com'),
                                   ('Eve', 'eve@example.com');