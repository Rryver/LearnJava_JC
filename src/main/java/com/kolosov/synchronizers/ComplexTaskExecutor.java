package com.kolosov.synchronizers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ComplexTaskExecutor {
    private final CyclicBarrier cyclicBarrier;

    private final int tasksCount;

    public ComplexTaskExecutor(int tasksCount) {
        this.tasksCount = tasksCount;
        cyclicBarrier = new CyclicBarrier(tasksCount + 1);
    }

    public void executeTasks(int numberOfTasks) {
        ExecutorService executorService = Executors.newFixedThreadPool(tasksCount);

        List<Future<Integer>> futuresTasks = new ArrayList<>();
        for (int i = 0; i < numberOfTasks; i++) {
            ComplexTask complexTask = new ComplexTask(cyclicBarrier);
            Future<Integer> futureTasks = executorService.submit(complexTask);
            futuresTasks.add(futureTasks);
        }

        try {
            cyclicBarrier.await();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        int result = 0;
        for (Future<Integer> futuresTask : futuresTasks) {
            try {
                result += futuresTask.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        executorService.shutdown();
        System.out.println("RESULT: " + result);
    }
}
