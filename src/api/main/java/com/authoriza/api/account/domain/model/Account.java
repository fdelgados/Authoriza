package com.authoriza.api.account.domain.model;

import com.authoriza.shared.domain.AggregateRoot;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public final class Account extends AggregateRoot {
    private final AccountId id;
    private final ClientId clientId;
    private User admin;
    private Set<User> users;
    private ClientSecret clientSecret;
    private String companyName;
    private LocalDateTime createdAt;

    public Account() {
        id = null;
        clientId = null;
    }

    public Account(AccountId id, UserEmail adminEmail, String adminPassword) {
        this.id = id;
        users = new HashSet<>(0);
        clientId = ClientId.create();
        clientSecret = ClientSecret.create();
        createAdminUser(adminEmail, adminPassword);

        AccountCreated event = new AccountCreated(
                this.id.getValue(),
                this.clientId.toString(),
                adminEmail.getValue()
        );

        createdAt = event.occurredOn();

        record(event);
    }

    private void createAdminUser(UserEmail adminEmail, String adminPassword) {
        admin = User.createAsAdmin(adminEmail, adminPassword, this);
        users.add(admin);
    }

    public AccountId id() {
        return id;
    }

    public ClientId clientId() {
        return clientId;
    }

    public ClientSecret clientSecret() {
        return clientSecret;
    }

    public void refreshClientSecret() {
        clientSecret = ClientSecret.create();
    }

    public void changeAdminPassword(String password) {
        admin.changePassword(password);
    }

    public void changeUserPassword(UserId userId, String password) {
        User user = getUser(userId);

        user.changePassword(password);
    }

    private User getUser(UserId userId) {
        var user = users.stream()
                .filter(wantedUser -> wantedUser.id().equals(userId))
                .findFirst()
                .orElse(null);

        if (user == null) {
            throw new UserNotFoundException();
        }

        return user;
    }
}
