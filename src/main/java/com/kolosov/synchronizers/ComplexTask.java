package com.kolosov.synchronizers;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;

public class ComplexTask implements Callable<Integer> {
    private final CyclicBarrier barrier;

    public ComplexTask(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public Integer call() throws Exception {
        return execute();
    }

    public Integer execute () throws InterruptedException, BrokenBarrierException {
//        Thread.sleep(2000);
        int result = ThreadLocalRandom.current().nextInt(1, 10);
        barrier.await();
        return result;
    }
}
