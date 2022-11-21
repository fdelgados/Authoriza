package com.authoriza.account.domain.model;

import com.authoriza.shared.domain.DomainError;

public class UserAlreadyExistsException extends DomainError {
    public UserAlreadyExistsException() {
        super(
                "authoriza.account.user_already_exists",
                "User already exists"
        );
    }
}
