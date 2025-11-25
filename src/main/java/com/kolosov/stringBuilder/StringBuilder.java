package com.kolosov.stringBuilder;

import java.io.*;
import java.util.Arrays;

public class StringBuilder implements Serializable {
    private byte[] value;

    private byte coder = 2;

    private int count;

    History history = new History();

    private static final byte[] EMPTY_VALUE = new byte[0];
    private static final int RESERVED_CAPACITY = 16;

    public StringBuilder() {
        value = EMPTY_VALUE;
    }

    public StringBuilder(String str) {
        int length = str.length();
        int capacity = length + RESERVED_CAPACITY;
        value = new byte[capacity];
        append(str);
    }

    public StringBuilder append(String str) {
        history.push(createSnapshot());

        if (str == null) {
            return this;
        }

        int len = str.length();
        ensureCapacity(count + len);

        putStringAt(count, str);

        count += len;
        return this;
    }

    public StringBuilder undo() {
        history.undo();
        return this;
    }

    public String build() {
        return new String(Arrays.copyOfRange(value, 0, count));
    }

    private void ensureCapacity(int minimumCapacity) {
        int oldCapacity = value.length;
        if (oldCapacity - minimumCapacity < 0) {
            value = Arrays.copyOf(value, minimumCapacity);
        }
    }

    private void putStringAt(int index, String str) {
        System.arraycopy(str.getBytes(), 0, value, index, str.length());
    }

    StringBuilderSnapshot createSnapshot() {
        return new StringBuilderSnapshot(value, count, this);
    }

    void restore(StringBuilderSnapshot snapshot) {
        this.value = Arrays.copyOf(snapshot.getValue(), snapshot.getValue().length);
        this.count = snapshot.getCount();
    }
}
