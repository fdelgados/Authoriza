package com.authoriza.application.domain.model;

import com.authoriza.shared.domain.Uuid;

public final class ApplicationId extends Uuid {
    private ApplicationId() {
        super();
    }

    public static ApplicationId create() {
        return new ApplicationId();
    }
}
