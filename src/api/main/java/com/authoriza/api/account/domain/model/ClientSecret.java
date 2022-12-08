package com.authoriza.api.account.domain.model;

import com.authoriza.shared.domain.NanoId;

final class ClientSecret extends NanoId {
    private ClientSecret() {
        super(64, false);
    }

    public static ClientSecret create() {
        return new ClientSecret();
    }
}
