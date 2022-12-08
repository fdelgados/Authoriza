package com.authoriza.api.account.application.create;

import com.authoriza.api.account.domain.model.*;
import com.authoriza.api.account.domain.service.Hasher;
import com.authoriza.shared.domain.bus.event.EventBus;
import com.authoriza.shared.domain.service.Service;

@Service
public final class AccountCreator {
    private final EventBus eventBus;
    private final AccountRepository accountRepository;
    private final Hasher hasher;

    public AccountCreator(EventBus eventBus, AccountRepository accountRepository, Hasher hasher) {
        this.eventBus = eventBus;
        this.accountRepository = accountRepository;
        this.hasher = hasher;
    }

    public void create(AccountId id, UserEmail adminEmail, String adminPassword) {
        ensureAccountDoesNotExist(id);
        ensureUserDoesNotExist(adminEmail);
        Account account = new Account(id, adminEmail, this.hasher.hash(adminPassword));

        accountRepository.save(account);
        eventBus.publish(account.pullDomainEvents());
    }

    private void ensureAccountDoesNotExist(AccountId id) {
        if (accountRepository.search(id).isPresent()) {
            throw new AccountAlreadyExistsException();
        }
    }

    private void ensureUserDoesNotExist(UserEmail adminEmail) {
        if (accountRepository.existUserOfEmail(adminEmail.getValue())) {
            throw new UserAlreadyExistsException();
        }
    }
}
