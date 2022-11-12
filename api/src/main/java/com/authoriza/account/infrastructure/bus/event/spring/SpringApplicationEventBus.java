package com.authoriza.account.infrastructure.bus.event.spring;

import com.authoriza.shared.domain.bus.event.DomainEvent;
import com.authoriza.shared.domain.bus.event.EventBus;
import com.authoriza.shared.domain.service.Service;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;

@Service
public class SpringApplicationEventBus implements EventBus {
    private final ApplicationEventPublisher publisher;

    public SpringApplicationEventBus(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void publish(List<DomainEvent> events) {
        events.forEach(this::publish);
    }

    private void publish(final DomainEvent event) {
        this.publisher.publishEvent(event);
    }
}
