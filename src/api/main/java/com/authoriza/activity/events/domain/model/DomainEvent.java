package com.authoriza.activity.events.domain.model;

import com.authoriza.shared.domain.Utils;

import java.time.LocalDateTime;

public final class DomainEvent {
    private final DomainEventId id;
    private final DomainEventAggregateId aggregateId;
    private final DomainEventType type;
    private final DomainEventData data;
    private final Integer version;
    private final LocalDateTime occurredOn;

    public DomainEvent(
            DomainEventId id,
            DomainEventAggregateId aggregateId,
            DomainEventType type,
            DomainEventData data,
            Integer version,
            LocalDateTime occurredOn
    ) {
        this.id = id;
        this.aggregateId = aggregateId;
        this.type = type;
        this.data = data;
        this.version = version;
        this.occurredOn = occurredOn;
    }

    public DomainEventId getId() {
        return id;
    }

    public DomainEventAggregateId getAggregateId() {
        return aggregateId;
    }

    public DomainEventType getType() {
        return type;
    }

    public DomainEventData getData() {
        return data;
    }

    public Integer getVersion() {
        return version;
    }

    public LocalDateTime getOccurredOn() {
        return occurredOn;
    }
}
