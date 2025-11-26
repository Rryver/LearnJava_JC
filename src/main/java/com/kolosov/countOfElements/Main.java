package com.kolosov.countOfElements;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * Напишите метод, который получает на вход массив элементов и возвращает Map ключи в котором - элементы, а значения - сколько раз встретился этот элемент
 */
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