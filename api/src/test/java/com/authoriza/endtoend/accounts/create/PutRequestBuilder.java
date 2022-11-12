package com.authoriza.endtoend.accounts.create;

import com.github.javafaker.Faker;

import java.util.HashMap;

class PutRequestBuilder {
    private final Faker faker;
    private String id;
    private String email;
    private String password;

    PutRequestBuilder() {
        faker = new Faker();
        id = null;
        email = null;
        password = null;
    }

    static PutRequestBuilder random() {
        PutRequestBuilder builder = new PutRequestBuilder();

        return builder.withId().withEmail().withPassword();
    }

    public String getId() {
        return id;
    }

    PutRequestBuilder withId(String id) {
        this.id = id;

        return this;
    }

    PutRequestBuilder withId() {
        this.id = faker.internet().uuid();

        return this;
    }

    PutRequestBuilder withEmptyId() {
        this.id = "";

        return this;
    }

    PutRequestBuilder withEmail(String email) {
        this.email = email;

        return this;
    }

    PutRequestBuilder withEmail() {
        this.email = faker.internet().emailAddress();

        return this;
    }

    PutRequestBuilder withEmptyEmail() {
        this.email = "";

        return this;
    }

    PutRequestBuilder withPassword(String password) {
        this.password = password;

        return this;
    }

    PutRequestBuilder withPassword() {
        this.password = faker.internet().password(8, 16, true, true, true);

        return this;
    }

    PutRequestBuilder withEmptyPassword() {
        this.password = "";

        return this;
    }

    HashMap<String, Object> build() {
        HashMap<String, Object> parameters = new HashMap<>();

        Faker faker = new Faker();

        if (id != null) {
            parameters.put("id", id);
        }

        if (email != null) {
            parameters.put("email", email);
        }

        if (password != null) {
            parameters.put("password", password);
        }

        return parameters;
    }
}
