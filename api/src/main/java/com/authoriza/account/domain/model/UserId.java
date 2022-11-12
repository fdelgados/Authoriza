package com.authoriza.account.domain.model;

import com.authoriza.shared.domain.Uuid;

public final class UserId extends Uuid {
    private UserId() {
        super();
    }

    public UserId(String value) {
        super(value);
    }

    public static UserId create() {
        return new UserId();
    }
}
