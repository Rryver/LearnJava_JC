package com.kolosov.filtering;

import java.lang.reflect.Array;
import java.util.Arrays;


/**
 * Напишите метод filter, который принимает на вход массив любого типа, вторым арументом метод должен принимать класс, реализующий интерфейс Filter, в котором один метод - T apply(T o) (параметризованный).
 * <br>
 * Метод должен быть реализован так чтобы возвращать новый массив, к каждому элементу которого была применена функция apply
 */
public class Main {
    public static void main(String[] args) {
        Integer[] arr = {1, 2};
        System.out.println(Arrays.toString(arr));

        Integer[] filter = filter(arr, item -> item + 2);
        System.out.println(Arrays.toString(filter));

        System.out.println(Arrays.toString(arr));
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] filter(T[] array, Filter<T> filter) {
        if (array == null || array.length == 0) {
            return array;
        }

        return Arrays.stream(array)
                .map(filter::apply)
                .toArray(size -> (T[]) Array.newInstance(array.getClass().getComponentType(), size));
    }
}