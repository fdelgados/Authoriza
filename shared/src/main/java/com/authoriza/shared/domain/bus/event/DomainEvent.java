package com.authoriza.shared.domain.bus.event;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class DomainEvent {
    private final String eventId;
    private final String aggregateId;
    private final int eventVersion;
    private final LocalDateTime occurredOn;

    public DomainEvent() {
        eventId = UUID.randomUUID().toString();
        aggregateId = null;
        eventVersion = 1;
        occurredOn = LocalDateTime.now();
    }

    public DomainEvent(String eventId, int eventVersion) {
        this.eventId = eventId;
        this.aggregateId = null;
        this.eventVersion = eventVersion;
        this.occurredOn = LocalDateTime.now();
    }

    public DomainEvent(String aggregateId, String eventId, int eventVersion) {
        this.eventId = eventId;
        this.aggregateId = aggregateId;
        this.eventVersion = eventVersion;
        this.occurredOn = LocalDateTime.now();
    }

    public DomainEvent(String aggregateId) {
        this.eventId = null;
        this.aggregateId = aggregateId;
        this.eventVersion = 1;
        this.occurredOn = LocalDateTime.now();
    }

    public String eventId() {
        return eventId;
    }

    abstract public String name();

    public int eventVersion() {
        return eventVersion;
    }

    public LocalDateTime occurredOn() {
        return occurredOn;
    }

    public String getAggregateId() {
        return aggregateId;
    }
}
