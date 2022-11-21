package com.authoriza.account.infrastructure.persistence.hibernate;

import com.authoriza.account.domain.model.Account;
import com.authoriza.account.domain.model.AccountId;
import com.authoriza.account.domain.model.AccountRepository;
import com.authoriza.shared.domain.service.Service;
import com.authoriza.shared.infrastructure.hibernate.HibernateRepository;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.object.SqlQuery;
import org.hibernate.SQLQuery;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Optional;

@Service
@Transactional("authoriza-api-transaction_manager")
public class AccountRepositoryImpl extends HibernateRepository<Account> implements AccountRepository {
    public AccountRepositoryImpl(@Qualifier("authoriza-api-session_factory") SessionFactory sessionFactory) {
        super(sessionFactory, Account.class);
    }

    @Override
    public Optional<Account> search(AccountId id) {
        return byId(id);
    }

    @Override
    public void save(Account account) {
        persist(account);
    }

    @Override
    public boolean existUserOfEmail(String email) {
        NativeQuery query = sessionFactory()
                .getCurrentSession()
                .createSQLQuery(String.format("SELECT COUNT(*) FROM users WHERE email = '%s'", email));

        BigInteger result = (BigInteger) query.uniqueResult();

        return result.intValue() > 0;
    }
}
