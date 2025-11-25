package com.kolosov;

import com.kolosov.stringBuilder.StringBuilder;

import java.util.Arrays;


/**
 * Для упрощения нет учёта кодировок. Поэтому работает только с латиницей.
 */
public class Main {
    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder("0");
        builder.append("1");
        builder.append("2");
        System.out.println(builder.build());

        builder.undo();
        System.out.println(builder.build());

        builder.append("3");
        System.out.println(builder.build());
    }
}