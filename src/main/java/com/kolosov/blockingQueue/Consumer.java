package com.kolosov.blockingQueue;

import java.util.concurrent.ExecutorService;

public class Consumer<T> implements Runnable {

    private final BlockingQueue<T> queue;

    private final java.util.function.Consumer<T> consumer;


    public Consumer(BlockingQueue<T> queue, java.util.function.Consumer<T> consumer) {
        this.queue = queue;
        this.consumer = consumer;
    }


    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                T value = queue.dequeue();
                consumer.accept(value);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
