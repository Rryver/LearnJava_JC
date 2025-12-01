package com.kolosov.blockingQueue;


import java.util.concurrent.*;

/**
 * Практическая задача - Concurrency - блокирующая очередь<br><br>
 *
 * Предположим, у вас есть пул потоков, и вы хотите реализовать блокирующую очередь
 * для передачи задач между потоками. Создайте класс BlockingQueue, который будет
 * обеспечивать безопасное добавление и извлечение элементов между
 * производителями и потребителями в контексте пула потоков.<br><br>
 *
 * Класс BlockingQueue должен содержать методы enqueue() для добавления
 * элемента в очередь и dequeue() для извлечения элемента.
 * Если очередь пуста, dequeue() должен блокировать вызывающий
 * поток до появления нового элемента.<br><br>
 *
 * очередь должна иметь фиксированный размер.<br><br>
 *
 * Используйте механизмы wait() и notify() для координации между
 * производителями и потребителями. Реализуйте метод size(), который
 * возвращает текущий размер очереди.
 */
public class Main {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new BlockingQueue<>(2);

        Producer<String> producerTask = new Producer<>(queue, () -> new String("Message from: " + Thread.currentThread().getName()));

        ScheduledExecutorService producerService = Executors.newScheduledThreadPool(2);
        producerService.scheduleAtFixedRate(producerTask, 0, 3L, TimeUnit.MILLISECONDS);
        producerService.scheduleAtFixedRate(producerTask, 0, 3L, TimeUnit.MILLISECONDS);
        producerService.scheduleAtFixedRate(producerTask, 0, 3L, TimeUnit.MILLISECONDS);


        ExecutorService consumerService = Executors.newFixedThreadPool(1);
        Consumer<String> consumerTask = new Consumer<>(queue, (value) -> {
            System.out.printf("Consumer %s got value: \"%s\"%n%n", Thread.currentThread().getName(), value);
        });

        consumerService.execute(consumerTask);
    }
}