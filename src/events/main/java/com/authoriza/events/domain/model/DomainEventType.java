package com.authoriza.events.domain.model;

import com.authoriza.shared.domain.StringValueObject;

public final class DomainEventType extends StringValueObject {
    public DomainEventType(String value) {
        super(value);
    }

    @Override
    protected boolean isValid(String value) {
        return value != null;
    }
}
