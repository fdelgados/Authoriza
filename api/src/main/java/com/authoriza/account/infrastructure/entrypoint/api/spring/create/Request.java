package com.authoriza.account.infrastructure.entrypoint.api.spring.create;

import com.authoriza.shared.infrastructure.spring.validators.Required;

public class Request {
    @Required(message = "Parameter <id> is required")
    private String id;

    @Required(message = "Parameter <email> is required")
    private String email;

    @Required(message = "Parameter <password> is required")
    private String password;

    public String id() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String email() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String password() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
