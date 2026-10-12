CREATE SEQUENCE users_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE users
(
    id            INT          NOT NULL,
    created_at    TIMESTAMP    NOT NULL,
    username      VARCHAR(32)  NOT NULL,
    password_hash VARCHAR(60)  NOT NULL,
    role          VARCHAR(255) NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE users
    ADD CONSTRAINT uc_users_username UNIQUE (username);