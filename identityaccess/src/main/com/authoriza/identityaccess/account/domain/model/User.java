package com.authoriza.identityaccess.account.domain.model;

import com.authoriza.shared.domain.PersonName;

final class User {
    private final UserId id;
    private final PersonName name;
    private final UserEmail email;
    private String password;
    private final boolean isAdmin;

    private User(UserEmail email, String password, boolean isAdmin, PersonName name) {
        this.id = UserId.create();
        this.email = email;
        this.password = password;
        this.name = name;
        this.isAdmin = isAdmin;
    }

    private User(UserEmail email, String password, boolean isAdmin) {
        this.id = UserId.create();
        this.email = email;
        this.password = password;
        this.name = null;
        this.isAdmin = isAdmin;
    }

    public static User create(UserEmail email, String password, PersonName name) {
        return new User(email, password, false, name);
    }

    public static User createAsAdmin(UserEmail email, String password) {
        return new User(email, password, true);
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
