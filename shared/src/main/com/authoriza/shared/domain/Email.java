package com.authoriza.shared.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email extends StringValueObject {
    private static final String PATTERN = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    public Email(String value) {
        super(value);
    }

    @Override
    protected boolean isValid(String value) {
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}
