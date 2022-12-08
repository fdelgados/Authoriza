package com.authoriza.api.account.application.create;

import com.authoriza.shared.domain.bus.command.Command;

public final class CreateAccountCommand implements Command {
    private final String id;
    private final String email;
    private final String password;

    public CreateAccountCommand(String id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public String id() {
        return id;
    }

    public String email() {
        return email;
    }

    public String password() {
        return password;
    }
}
