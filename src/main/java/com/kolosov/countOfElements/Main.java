package com.kolosov.countOfElements;

import com.kolosov.filtering.Filter;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {
    public static void main(String[] args) {
        Integer[] r = new Integer[] {1, 2,2, 3, 4,4,4};

        System.out.println(countOfElements(r));
    }

    public static <T> Map<T, Long> countOfElements(T[] array) {
        if (array == null || array.length == 0) {
            return new HashMap<>();
        }

        return Arrays.stream(array)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}