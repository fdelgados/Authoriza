CREATE USER authoriza WITH PASSWORD 'w135HkeGXg';

CREATE DATABASE authoriza
    WITH
    ENCODING = 'UTF8'
    OWNER = authoriza;

CREATE TABLE IF NOT EXISTS authoriza.accounts
(
    id UUID NOT NULL CONSTRAINT accounts_pk PRIMARY KEY,
    client_id CHAR(32) UNIQUE NOT NULL,
    client_secret CHAR(64) UNIQUE NOT NULL,
    company_name VARCHAR(100),
    created_at TIMESTAMP NOT NULL
);

ALTER TABLE authoriza.accounts
    OWNER TO authoriza;

CREATE TABLE IF NOT EXISTS authoriza.users
(
    id UUID NOT NULL CONSTRAINT users_pk PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password CHAR(64) NOT NULL,
    is_admin BOOLEAN NOT NULL DEFAULT false,
    account_id UUID NOT NULL,
    first_name VARCHAR(60),
    last_name VARCHAR(120),
    created_at TIMESTAMP NOT NULL,
    CONSTRAINT accounts_fk FOREIGN KEY (account_id)
    REFERENCES accounts (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

ALTER TABLE authoriza.users
    OWNER TO authoriza;