package com.authoriza.events.domain.model;

public interface DomainEventRepository {
    void save(DomainEvent domainEvent);
}
