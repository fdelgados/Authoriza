package com.authoriza.api.unit.account.domain.model;

import com.authoriza.api.account.domain.model.AccountId;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountIdTest {
    @Test
    public void it_should_foo_and_bar() {
        AccountId accountId = AccountId.create();

        Pattern pattern = Pattern.compile("^[0-9a-f]{8}-[0-9a-f]{4}-4[0-9a-f]{3}-[89ab]{1}[0-9a-f]{3}-[0-9a-f]{12}$");
        Matcher matcher = pattern.matcher(accountId.getValue());

        assertTrue(matcher.matches(), "It should be an account id with a valid format");
    }

}
