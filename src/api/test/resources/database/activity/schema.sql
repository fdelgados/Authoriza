DROP TABLE IF EXISTS domain_events;

CREATE TABLE domain_events
(
    id UUID PRIMARY KEY,
    aggregate_id UUID NOT NULL,
    type VARCHAR(255) NOT NULL,
    version INT NOT NULL,
    data JSONB NOT NULL,
    occurred_on TIMESTAMP NOT NULL
);