package com.authoriza.shared.domain;

import org.apache.commons.validator.routines.UrlValidator;

public class Url extends StringValueObject {
    private static final String[] SCHEMES = {"http", "https"};

    public Url(String value) {
        super(value);
    }

    @Override
    protected boolean isValid(String value) {
        UrlValidator urlValidator = new UrlValidator(SCHEMES);

        return urlValidator.isValid(value);
    }
}
