package com.authoriza.identityaccess.application.domain.model;

import com.authoriza.identityaccess.account.domain.model.ClientId;
import com.authoriza.shared.domain.AggregateRoot;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;

public final class Application extends AggregateRoot {
    private ApplicationId id;
    private ClientId clientId;
    private String name;
    private Domain domain;

    public Application(ApplicationId id, ClientId clientId, String name, Domain domain) {
        this.id = id;
        this.clientId = clientId;
        this.name = name;
        this.domain = domain;
    }

    public ApplicationId getId() {
        return id;
    }

    public ClientId getClientId() {
        return clientId;
    }
}
