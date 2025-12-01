package com.kolosov.blockingQueue;

import java.util.function.Supplier;

public class Producer<T> implements Runnable {

    private final BlockingQueue<T> queue;
    private final Supplier<T> supplier;

    public Producer(BlockingQueue<T> queue, Supplier<T> supplier) {
        this.queue = queue;
        this.supplier = supplier;
    }

    @Override
    public void run() {
        try {
            System.out.println("Thread into producer: " + Thread.currentThread().getName());
            T elem = supplier.get();
            queue.enqueue(elem);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
