package com.authoriza.events.domain.model;

import java.io.Serializable;
import java.util.HashMap;

public final class DomainEventData {
    private final HashMap<String, Serializable> value;

    public DomainEventData(HashMap<String, Serializable> value) {
        this.value = value;
    }

    public HashMap<String, Serializable> getValue() {
        return value;
    }
}
