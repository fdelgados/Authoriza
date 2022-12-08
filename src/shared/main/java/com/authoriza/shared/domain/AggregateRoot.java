package com.authoriza.shared.domain;

import com.authoriza.shared.domain.bus.event.DomainEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AggregateRoot {
    private List<DomainEvent> domainEvents = new ArrayList<>();

    public final List<DomainEvent> pullDomainEvents() {
        List<DomainEvent> events = domainEvents;

        domainEvents = Collections.emptyList();

        return events;
    }

    protected final void record(DomainEvent event) {
        domainEvents.add(event);
    }
}
