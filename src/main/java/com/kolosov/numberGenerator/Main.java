package com.kolosov.numberGenerator;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Предположим, у нас есть список заказов, и каждый заказ представляет собой продукт и его стоимость.
 * Задача состоит в использовании Stream API и коллекторов для решения следующих задач:<br>
 *
 * 1. Создайте список заказов с разными продуктами и их стоимостями. <br>
 * 2. Группируйте заказы по продуктам.<br>
 * 3. Для каждого продукта найдите общую стоимость всех заказов.<br>
 * 4. Отсортируйте продукты по убыванию общей стоимости.<br>
 * 5. Выберите три самых дорогих продукта.<br>
 * 6. Выведите результат: список трех самых дорогих продуктов и их общая стоимость.<br>
 */
public class Main {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0),
                new Order("HeadSet", 400.0),
                new Order("Microphone", 1600.0)
        );

        Map<String, Double> collect = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getProduct,
                        Collectors.summingDouble(Order::getCost))
                );

        Map<String, Double> result = collect.entrySet().stream()
                .sorted((o1, o2) -> Double.compare(o2.getValue(), o1.getValue()))
                .limit(3)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (s, a) -> a,
                        LinkedHashMap::new
                ));

        System.out.println(result);
    }
}