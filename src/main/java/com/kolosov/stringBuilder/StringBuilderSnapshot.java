package com.kolosov.stringBuilder;

import java.util.Arrays;

public class StringBuilderSnapshot {
    private final byte[] value;
    private final int count;

    private final StringBuilder stringBuilder;

    public StringBuilderSnapshot(byte[] value, int count, StringBuilder stringBuilder) {
        this.value = Arrays.copyOf(value, value.length);
        this.count = count;
        this.stringBuilder = stringBuilder;
    }

    byte[] getValue() {
        return value;
    }

    int getCount() {
        return count;
    }

    public void restore() {
        stringBuilder.restore(this);
    }
}
