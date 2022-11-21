package com.authoriza.account.domain.model;

import com.authoriza.shared.domain.DomainError;

public class UserNotFoundException extends DomainError {
    public UserNotFoundException() {
        super("authoriza.account.user_not_found", "User not found");
    }
}
