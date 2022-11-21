package com.authoriza.shared.domain;

import java.io.Serializable;
import java.util.Objects;

public abstract class Identifier implements Serializable {
    protected String value;

    public String value() {
        return value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }

    abstract public boolean equals(Object o);
}
