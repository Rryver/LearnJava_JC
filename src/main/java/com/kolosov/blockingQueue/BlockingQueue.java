package com.kolosov.blockingQueue;

import java.util.LinkedList;

public class BlockingQueue<T> {

    private final LinkedList<T> items = new LinkedList<>();

    private volatile int size = 0;
    private final int maxSize;

    public BlockingQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    public void enqueue(T elem) throws InterruptedException {
        synchronized (items) {
            while (size == maxSize) {
                System.out.println(Thread.currentThread().getName() + " waiting because of MAX SIZE");
                items.notify();
                items.wait();
            }

            final int oldSize = size;
            items.addLast(elem);
            size++;
            System.out.println("Added task. Current count: " + size);

            if (oldSize == 0) {
                items.notify();
            }
        }
    }

    public T dequeue() throws InterruptedException {
        synchronized (items) {
            while (size == 0) {
                System.out.println(Thread.currentThread().getName() + " waiting because IS EMPTY");
                items.wait();
            }

            final int oldSize = size;
            T first = items.pollFirst();
            size--;
            System.out.println("Removed task. Current count: " + size);

            if (oldSize == maxSize) {
                items.notify();
            }
            return first;
        }
    }

    public int size() {
        return size;
    }


}
