package com.authoriza.api.unit.account.domain.model;

import com.authoriza.api.account.domain.model.AccountId;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountIdTest {

    public static final String ID_PATTERN = "^[0-9a-f]{8}-[0-9a-f]{4}-4[0-9a-f]{3}-[89ab]{1}[0-9a-f]{3}-[0-9a-f]{12}$";

    @Test
    public void it_should_be_created_with_a_valid_format() {
        AccountId accountId = AccountId.create();

        Pattern pattern = Pattern.compile(ID_PATTERN);
        Matcher matcher = pattern.matcher(accountId.getValue());

        assertTrue(matcher.matches(), "It should be an account id with a valid format");
    }

}
