package com.authoriza.activity.events.domain.model;

import com.authoriza.shared.domain.Uuid;

public final class DomainEventId extends Uuid {
    public DomainEventId(String value) {
        super(value);
    }

    private DomainEventId() {
        super();
    }
}
