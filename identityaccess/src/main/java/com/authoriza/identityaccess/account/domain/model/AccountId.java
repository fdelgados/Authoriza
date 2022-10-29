package com.authoriza.identityaccess.account.domain.model;

import com.authoriza.shared.domain.Uuid;

public class AccountId extends Uuid {
    public AccountId(String value) {
        super(value);
    }

    private AccountId() {
        super();
    }

    public static AccountId random() {
        return new AccountId();
    }
}
