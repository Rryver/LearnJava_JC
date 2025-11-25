package com.kolosov.stringBuilder;

import com.kolosov.stringBuilder.StringBuilderSnapshot;

import java.util.ArrayList;
import java.util.List;

public class History {
    private final List<StringBuilderSnapshot> history = new ArrayList<>();

    public void push(StringBuilderSnapshot snapshot) {
        history.add(snapshot);
    }

    public void undo() {
        if (history.isEmpty()) {
            return;
        }

        StringBuilderSnapshot last = history.getLast();
        last.restore();
    }
}
