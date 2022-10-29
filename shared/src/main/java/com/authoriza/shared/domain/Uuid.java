package com.authoriza.shared.domain;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public abstract class Uuid implements Serializable {
    protected String value;

    public Uuid(String value) {
        ensureIsValid(value);

        this.value = value;
    }

    protected Uuid() {
        value = UUID.randomUUID().toString();
    }

    private void ensureIsValid(String value) throws IllegalArgumentException {
        UUID.fromString(value);
    }

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
        Uuid that = (Uuid) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
