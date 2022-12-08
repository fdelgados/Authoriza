package com.authoriza.events.infrastructure.persistence.hibernate;

import com.authoriza.events.domain.model.DomainEvent;
import com.authoriza.events.domain.model.DomainEventRepository;
import com.authoriza.shared.domain.service.Service;
import com.authoriza.shared.infrastructure.hibernate.HibernateRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional("authoriza-events-transaction_manager")
public class DomainEventRepositoryImpl extends HibernateRepository<DomainEvent> implements DomainEventRepository {
    public DomainEventRepositoryImpl(@Qualifier("authoriza-events-session-factory") SessionFactory sessionFactory) {
        super(sessionFactory, DomainEvent.class);
    }

    @Override
    public void save(DomainEvent domainEvent) {
        persist(domainEvent);
    }
}
