package com.authoriza.events.application.store;

import com.authoriza.events.domain.model.*;

import java.time.LocalDateTime;

public final class DomainEventStorer {
    private final DomainEventRepository domainEventRepository;

    public DomainEventStorer(DomainEventRepository domainEventRepository) {
        this.domainEventRepository = domainEventRepository;
    }

    public void store(
            DomainEventId domainEventId,
            DomainEventAggregateId domainEventAggregateId,
            DomainEventType domainEventName,
            DomainEventData domainEventData,
            Integer version,
            LocalDateTime occurredOn
    ) {
        DomainEvent domainEvent = new DomainEvent(
                domainEventId,
                domainEventAggregateId,
                domainEventName,
                domainEventData,
                version,
                occurredOn
        );

        domainEventRepository.save(domainEvent);
    }
}
