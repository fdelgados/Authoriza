package com.authoriza.shared.domain;

import java.util.Objects;
import java.util.UUID;

public abstract class Uuid extends Identifier {
    private UUID id;

    public Uuid(String value) {
        ensureIsValid(value);

        this.id = UUID.fromString(value);
        this.value = value;
    }

    private void ensureIsValid(String value) throws IllegalArgumentException {
        UUID.fromString(value);
    }

    protected Uuid() {
        id = UUID.randomUUID();
        value = id.toString();
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
}
