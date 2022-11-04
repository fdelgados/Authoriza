package com.authoriza.identityaccess.account.domain.model;

import com.authoriza.shared.domain.DomainError;

public class UserNotFoundException extends DomainError {
    public UserNotFoundException() {
        super("identity_access.account.user_not_found", "User not found");
    }
}
