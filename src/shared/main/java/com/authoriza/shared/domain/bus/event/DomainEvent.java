package com.authoriza.shared.domain.bus.event;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
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

    public DomainEvent(String aggregateId) {
        this.eventId = UUID.randomUUID().toString();
        this.aggregateId = aggregateId;
        this.eventVersion = 1;
        this.occurredOn = LocalDateTime.now();
    }

    public DomainEvent(String aggregateId, String eventId, LocalDateTime occurredOn) {
        this.eventId = eventId;
        this.aggregateId = aggregateId;
        this.eventVersion = 1;
        this.occurredOn = occurredOn;
    }

    public String eventId() {
        return eventId;
    }

    public abstract String eventName();

    public abstract HashMap<String, Serializable> toPrimitives();

    public abstract DomainEvent fromPrimitives(
            String aggregateId,
            HashMap<String, Serializable> body,
            String eventId,
            String occurredOn
    );

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
