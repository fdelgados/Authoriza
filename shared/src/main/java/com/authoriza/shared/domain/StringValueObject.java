package com.authoriza.shared.domain;

import java.util.Objects;

public abstract class StringValueObject {
    protected String value;

    public StringValueObject() {
        value = null;
    }

    public StringValueObject(String value) {
        assertValueIsValid(value);

        this.value = value;
    }

    private void assertValueIsValid(String value) {
        if (!this.isValid(value)) {
            throw InvalidValueException.fromValue(value, this);
        }
    }

    abstract protected boolean isValid(String value);

    public final String getValue() {
        return value;
    }

    public final boolean isBlank() {
        return value == null || value.isBlank();
    }

    public final boolean isEmpty() {
        return value == null || value.isEmpty();
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StringValueObject that = (StringValueObject) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
