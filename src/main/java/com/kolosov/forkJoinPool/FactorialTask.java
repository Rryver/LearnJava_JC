package com.kolosov.forkJoinPool;

import java.util.concurrent.RecursiveTask;

class FactorialTask extends RecursiveTask<Integer> {

    private final Integer n;

    public FactorialTask(Integer n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n == 1) {
            return 1;
        }
        FactorialTask subTask = new FactorialTask(n - 1);
        subTask.fork();

        return n * subTask.join();
    }
}
