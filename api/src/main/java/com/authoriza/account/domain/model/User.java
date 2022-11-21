package com.authoriza.account.domain.model;

import com.authoriza.shared.domain.PersonName;

final class User {
    private final UserId id;
    private final PersonName name;
    private final UserEmail email;
    private String password;
    private final boolean isAdmin;
    private final Account account;

    public User() {
        id = null;
        name = null;
        email = null;
        isAdmin = false;
        account = null;
    }

    private User(UserEmail email, String password, boolean isAdmin, PersonName name, Account account) {
        this.id = UserId.create();
        this.email = email;
        this.password = password;
        this.name = name;
        this.isAdmin = isAdmin;
        this.account = account;
    }

    private User(UserEmail email, String password, boolean isAdmin, Account account) {
        this.id = UserId.create();
        this.email = email;
        this.password = password;
        this.name = null;
        this.isAdmin = isAdmin;
        this.account = account;
    }

    public static User create(UserEmail email, String password, PersonName name, Account account) {
        return new User(email, password, false, name, account);
    }

    public static User createAsAdmin(UserEmail email, String password, Account account) {
        return new User(email, password, true, account);
    }

    public UserId id() {
        return id;
    }

    public String fullName() {
        return name.fullName();
    }

    public UserEmail email() {
        return email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void changePassword(String password) {
        this.password = password;
    }
}
