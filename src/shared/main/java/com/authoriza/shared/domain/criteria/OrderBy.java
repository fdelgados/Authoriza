package com.authoriza.shared.domain.criteria;

import com.authoriza.shared.domain.StringValueObject;

public final class OrderBy extends StringValueObject {
    public OrderBy(String value) {
        super(value);
    }

    @Override
    protected boolean isValid(String value) {
        return value != null;
    }
}
