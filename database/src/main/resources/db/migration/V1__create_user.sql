CREATE TABLE users
(
    id              BIGINT PRIMARY KEY AUTO_INCREMENT,
    surname         VARCHAR(40) NOT NULL,
    name            VARCHAR(20) NOT NULL,
    patronymic_name VARCHAR(40) NULL,
    role            VARCHAR(30) NOT NULL,
    email           VARCHAR(50) UNIQUE
);
