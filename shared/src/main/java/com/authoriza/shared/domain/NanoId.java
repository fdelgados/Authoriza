package com.authoriza.shared.domain;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;

import java.util.Objects;
import java.util.Random;

abstract class NanoId {
    private static final int DEFAULT_ID_SIZE = 21;
    private static final String ALLOWED_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private final int size;
    private final boolean excludeSpecial;
    protected String value;

    public NanoId(int size, boolean excludeSpecial)
    {
        this.size = size;
        this.excludeSpecial = excludeSpecial;
        this.value = generateId();
    }

    public NanoId(boolean excludeSpecial) {
        this.size = DEFAULT_ID_SIZE;
        this.excludeSpecial = excludeSpecial;
        this.value = generateId();
    }

    public NanoId(int size) {
        this.size = size;
        this.excludeSpecial = true;
        this.value = generateId();
    }

    private String generateId() {
        var allowedChars = ALLOWED_CHARS;
        Random random = new Random();

        if (!excludeSpecial) {
            allowedChars += "-_";
        }

        return NanoIdUtils.randomNanoId(random, allowedChars.toCharArray(), size);
    }

    public String getValue() {
        return value;
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
        NanoId that = (NanoId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
