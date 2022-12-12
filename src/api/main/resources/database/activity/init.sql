CREATE USER activity WITH PASSWORD 'w135HkeGXg';

CREATE DATABASE activity
    WITH
    ENCODING = 'UTF8'
    OWNER = activity;

CREATE TABLE IF NOT EXISTS domain_events
(
    id UUID NOT NULL CONSTRAINT domain_events_pk PRIMARY KEY,
    aggregate_id UUID         NOT NULL,
    type         VARCHAR(255) NOT NULL,
    version      INTEGER      NOT NULL,
    data         JSONB        NOT NULL,
    occurred_on  TIMESTAMP    NOT NULL
);

ALTER TABLE domain_events
    OWNER TO activity;

CREATE INDEX domain_events_aggregate_id_index
    ON domain_events (aggregate_id);