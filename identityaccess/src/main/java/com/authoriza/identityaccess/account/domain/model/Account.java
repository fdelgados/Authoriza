package com.authoriza.identityaccess.account.domain.model;

import com.authoriza.shared.domain.AggregateRoot;

public class Account extends AggregateRoot {
    private AccountId id;

    public Account() {
        id = AccountId.random();
    }

    public AccountId getId() {
        return id;
    }
}
