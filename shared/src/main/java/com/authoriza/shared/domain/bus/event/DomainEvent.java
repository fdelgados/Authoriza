package com.authoriza.shared.domain.bus.event;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class DomainEvent {
    private final String eventId;
    private final int eventVersion;
    private final LocalDateTime occurredOn;

    public DomainEvent() {
        eventId = UUID.randomUUID().toString();
        eventVersion = 1;
        occurredOn = LocalDateTime.now();
    }

    public DomainEvent(String eventId, int eventVersion) {
        this.eventId = eventId;
        this.eventVersion = eventVersion;
        occurredOn = LocalDateTime.now();
    }

    public String eventId() {
        return eventId;
    }

    public int eventVersion() {
        return eventVersion;
    }

    public LocalDateTime occurredOn() {
        return occurredOn;
    }
}
