package com.authoriza.identityaccess.account.domain.model;

import junit.framework.*;

public class AccountIdTest extends TestCase {
    public void test_it_should_foo_and_bar() {
        AccountId accountId = AccountId.create();

        assertNotNull("It should be true", accountId.getValue());
    }
}
