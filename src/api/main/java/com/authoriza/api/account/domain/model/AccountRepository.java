package com.authoriza.api.account.domain.model;

import java.util.Optional;

public interface AccountRepository {
    Optional<Account> search(AccountId id);

    void save(Account account);

    boolean existUserOfEmail(String email);
}
