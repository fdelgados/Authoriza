DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS accounts;

CREATE TABLE accounts
(
    id UUID PRIMARY KEY,
    client_id CHAR(32) NOT NULL,
    client_secret CHAR(64) NOT NULL,
    company_name VARCHAR(100),
    created_at TIMESTAMP NOT NULL,
    UNIQUE (client_id),
    UNIQUE (client_secret)
);

CREATE TABLE users
(
    id UUID PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    password CHAR(64) NOT NULL,
    is_admin BOOLEAN NOT NULL DEFAULT false,
    account_id UUID NOT NULL,
    first_name VARCHAR(60),
    last_name VARCHAR(120),
    created_at TIMESTAMP NOT NULL,
    UNIQUE (email),
    CONSTRAINT accounts_fk FOREIGN KEY (account_id)
        REFERENCES accounts (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
