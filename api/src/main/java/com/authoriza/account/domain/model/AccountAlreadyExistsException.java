package com.authoriza.account.domain.model;

import com.authoriza.shared.domain.DomainError;

public class AccountAlreadyExistsException extends DomainError {
    public AccountAlreadyExistsException() {
        super("authoriza.account.account_already_exists", "Account already exists");
    }
}
