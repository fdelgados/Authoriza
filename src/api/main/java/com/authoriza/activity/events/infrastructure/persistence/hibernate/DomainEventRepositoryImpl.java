package com.authoriza.activity.events.infrastructure.persistence.hibernate;

import com.authoriza.activity.events.domain.model.DomainEvent;
import com.authoriza.activity.events.domain.model.DomainEventRepository;
import com.authoriza.shared.domain.service.Service;
import com.authoriza.shared.infrastructure.hibernate.HibernateRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional("authoriza-activity-transaction_manager")
public class DomainEventRepositoryImpl extends HibernateRepository<DomainEvent> implements DomainEventRepository {
    public DomainEventRepositoryImpl(@Qualifier("authoriza-activity-session_factory") SessionFactory sessionFactory) {
        super(sessionFactory, DomainEvent.class);
    }

    @Override
    public void save(DomainEvent domainEvent) {
        persist(domainEvent);
    }
}
