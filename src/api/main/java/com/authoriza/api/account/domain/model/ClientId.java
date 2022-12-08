package com.authoriza.api.account.domain.model;

import com.authoriza.shared.domain.NanoId;

public final class ClientId extends NanoId {
    private ClientId() {
        super(32);
    }

    public static ClientId create() {
        return new ClientId();
    }
}
