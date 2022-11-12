package com.authoriza.account.domain.model;

import com.authoriza.shared.domain.AggregateRoot;

import java.util.ArrayList;
import java.util.List;

public final class Account extends AggregateRoot {
    private final AccountId id;
    private final ClientId clientId;
    private final User admin;
    private List<User> users;
    private ClientSecret clientSecret;

    public Account(AccountId id, UserEmail adminEmail, String adminPassword) {
        this.id = id;
        admin = User.createAsAdmin(adminEmail, adminPassword);
        users = new ArrayList<>();
        clientId = ClientId.create();
        clientSecret = ClientSecret.create();
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
