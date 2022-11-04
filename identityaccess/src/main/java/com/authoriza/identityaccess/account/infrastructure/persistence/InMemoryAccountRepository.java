package com.authoriza.identityaccess.account.infrastructure.persistence;

import com.authoriza.identityaccess.account.domain.model.Account;
import com.authoriza.identityaccess.account.domain.model.AccountId;
import com.authoriza.identityaccess.account.domain.model.AccountRepository;
import com.authoriza.shared.domain.service.Service;

import java.util.Optional;

@Service
public final class InMemoryAccountRepository implements AccountRepository {
    @Override
    public Optional<Account> search(AccountId id) {
        return Optional.empty();
    }

    @Override
    public void save(Account account) {

    }
}
