package com.authoriza.activity.events.application.store;

import com.authoriza.activity.events.domain.model.*;
import com.authoriza.shared.domain.service.Service;

import java.time.LocalDateTime;

@Service
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
