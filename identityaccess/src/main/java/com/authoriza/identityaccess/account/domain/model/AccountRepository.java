package com.authoriza.identityaccess.account.domain.model;

import java.util.Optional;

public interface AccountRepository {
    Optional<Account> search(AccountId id);

    void save(Account account);
}
