package com.authoriza.events.application.store;

import com.authoriza.events.domain.model.DomainEventAggregateId;
import com.authoriza.events.domain.model.DomainEventData;
import com.authoriza.events.domain.model.DomainEventId;
import com.authoriza.events.domain.model.DomainEventType;
import com.authoriza.shared.domain.bus.event.DomainEvent;
import com.authoriza.shared.domain.bus.event.DomainEventSubscriber;
import org.springframework.context.event.EventListener;

import java.time.LocalDateTime;

@DomainEventSubscriber({DomainEvent.class})
public final class StoreDomainEventOnOccurred {
    private DomainEventStorer storer;

    public StoreDomainEventOnOccurred(DomainEventStorer storer) {
        this.storer = storer;
    }

    @EventListener
    public void on(DomainEvent event) {
        DomainEventId domainEventId = new DomainEventId(event.eventId());
        DomainEventAggregateId domainEventAggregateId = new DomainEventAggregateId(event.getAggregateId());
        DomainEventType domainEventType = new DomainEventType(event.eventName());
        DomainEventData domainEventData = new DomainEventData(event.toPrimitives());

        storer.store(
                domainEventId,
                domainEventAggregateId,
                domainEventType,
                domainEventData,
                event.eventVersion(),
                event.occurredOn()
        );
    }
}
