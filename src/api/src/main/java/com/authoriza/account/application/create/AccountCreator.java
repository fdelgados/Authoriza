package com.authoriza.account.application.create;

import com.authoriza.account.domain.model.Account;
import com.authoriza.account.domain.model.AccountId;
import com.authoriza.account.domain.model.UserEmail;
import com.authoriza.account.domain.service.Hasher;
import com.authoriza.account.domain.model.AccountRepository;
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
        Account account = new Account(id, adminEmail, this.hasher.hash(adminPassword));

        accountRepository.save(account);
        eventBus.publish(account.pullDomainEvents());
    }
}
