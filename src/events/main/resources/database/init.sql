CREATE USER event_store WITH PASSWORD 'w135HkeGXg';

CREATE DATABASE event_store
    WITH
    ENCODING = 'UTF8'
    OWNER = event_store;

CREATE TABLE IF NOT EXISTS domain_events
(
    id UUID NOT NULL CONSTRAINT domain_events_pk PRIMARY KEY,
    aggregate_id UUID NOT NULL,
    type VARCHAR(255) NOT NULL,
    version INT NOT NULL,
    data JSONB NOT NULL,
    occurred_on TIMESTAMP NOT NULL
);

ALTER TABLE domain_events
    OWNER TO event_store;