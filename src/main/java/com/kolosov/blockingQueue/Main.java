package com.kolosov.blockingQueue;


import java.util.concurrent.*;

/**
 * Для упрощения нет учёта кодировок. Поэтому работает только с латиницей.
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