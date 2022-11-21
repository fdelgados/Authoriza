package com.authoriza.shared.domain.criteria;

import com.authoriza.shared.domain.StringValueObject;

public final class FilterValue extends StringValueObject {
    public FilterValue(String value) {
        super(value);
    }

    @Override
    protected boolean isValid(String value) {
        return true;
    }
}
