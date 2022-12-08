package com.authoriza.shared.domain;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;

import java.util.Objects;
import java.util.Random;

public abstract class NanoId extends Identifier {
    private static final int DEFAULT_ID_SIZE = 21;
    private static final String ALLOWED_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private final int size;
    private final boolean excludeSpecial;

    public NanoId() {
        this.size = DEFAULT_ID_SIZE;
        this.excludeSpecial = true;
    }

    public NanoId(int size, boolean excludeSpecial)
    {
        this.size = size;
        this.excludeSpecial = excludeSpecial;
        setValue();
    }

    public NanoId(boolean excludeSpecial) {
        this.size = DEFAULT_ID_SIZE;
        this.excludeSpecial = excludeSpecial;
        setValue();
    }

    public NanoId(int size) {
        this.size = size;
        this.excludeSpecial = true;
        setValue();
    }

    private void setValue() {
        var allowedChars = ALLOWED_CHARS;
        Random random = new Random();

        if (!excludeSpecial) {
            allowedChars += "-_";
        }

        this.value = NanoIdUtils.randomNanoId(random, allowedChars.toCharArray(), size);
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
}
