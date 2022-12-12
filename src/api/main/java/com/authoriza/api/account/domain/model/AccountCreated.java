package com.authoriza.api.account.domain.model;

import com.authoriza.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Objects;

public final class AccountCreated extends DomainEvent {
    private final String clientId;
    private final String adminEmail;

    public AccountCreated() {
        super(null);

        clientId = null;
        adminEmail = null;
    }

    public AccountCreated(
            String aggregateId,
            String clientId,
            String adminEmail
    ) {
        super(aggregateId);

        this.clientId = clientId;
        this.adminEmail = adminEmail;
    }

    public AccountCreated(
            String aggregateId,
            String eventId,
            LocalDateTime occurredOn,
            String clientId,
            String adminEmail
    ) {
        super(aggregateId, eventId, occurredOn);

        this.clientId = clientId;
        this.adminEmail = adminEmail;
    }

    public String getClientId() {
        return clientId;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    @Override
    public String eventName() {
        return String.format("authoriza.api.account.%d.account_created", this.eventVersion());
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<String, Serializable>() {{
            put("client_id", clientId);
            put("admin_email", adminEmail);
        }};
    }

    @Override
    public DomainEvent fromPrimitives(
            String aggregateId,
            HashMap<String, Serializable> body,
            String eventId,
            String occurredOn
    ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(occurredOn, formatter);

        return new AccountCreated(
                aggregateId,
                eventId,
                dateTime,
                (String) body.get("client_id"),
                (String) body.get("admin_email")
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountCreated that = (AccountCreated) o;
        return Objects.equals(clientId, that.clientId) && Objects.equals(adminEmail, that.adminEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, adminEmail);
    }
}
