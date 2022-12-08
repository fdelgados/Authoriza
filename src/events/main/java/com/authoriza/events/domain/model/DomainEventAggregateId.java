package com.authoriza.events.domain.model;

import com.authoriza.shared.domain.Uuid;

public final class DomainEventAggregateId extends Uuid {
    public DomainEventAggregateId(String value) {
        super(value);
    }

    public DomainEventAggregateId() {
        super();
    }
}
