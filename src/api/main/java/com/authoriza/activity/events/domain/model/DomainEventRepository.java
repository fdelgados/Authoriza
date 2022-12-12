package com.authoriza.activity.events.domain.model;

public interface DomainEventRepository {
    void save(DomainEvent domainEvent);
}
