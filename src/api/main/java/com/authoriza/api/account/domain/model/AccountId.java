package com.authoriza.api.account.domain.model;

import com.authoriza.shared.domain.Uuid;

public final class AccountId extends Uuid {
    public AccountId(String value) {
        super(value);
    }

    private AccountId() {
        super();
    }

    public static AccountId create() {
        return new AccountId();
    }
}
