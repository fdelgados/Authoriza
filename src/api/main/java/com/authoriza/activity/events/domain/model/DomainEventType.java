package com.authoriza.activity.events.domain.model;

import com.authoriza.shared.domain.StringValueObject;

public final class DomainEventType extends StringValueObject {
    public DomainEventType(String value) {
        super(value);
    }

    public DomainEventType() {
        super();
    }

    @Override
    protected boolean isValid(String value) {
        return value != null;
    }
}
