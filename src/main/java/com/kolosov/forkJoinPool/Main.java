package com.kolosov.forkJoinPool;

import java.util.concurrent.ForkJoinPool;

/**
 * Рассмотрим задачу вычисления факториала числа с использованием ForkJoinPool.
 * Факториал числа n обозначается как n! и вычисляется как произведение всех
 * положительных целых чисел от 1 до n. <br><br>
 *
 * 1. Реализуйте класс FactorialTask, который расширяет RecursiveTask.
 * Этот класс будет выполнять рекурсивное вычисление факториала числа.<br>
 *
 * 2. В конструкторе FactorialTask передайте число n, факториал которого
 * нужно вычислить.<br>
 *
 * 3. В методе compute() разбейте задачу на подзадачи и используйте fork()
 * для их асинхронного выполнения.<br>
 *
 * 4. Используйте join() для получения результатов подзадач и комбинирования их
 * для получения общего результата.<br>
 *
 * 5. В основном методе создайте экземпляр FactorialTask с числом, для которого
 * нужно вычислить факториал, и запустите его в ForkJoinPool.<br>
 *
 * 6. Выведите результат вычисления факториала.
 */
public class Main {
    public static void main(String[] args) {
        int n = 10; // Вычисление факториала для числа 10

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FactorialTask factorialTask = new FactorialTask(n);

        long result = forkJoinPool.invoke(factorialTask);

        System.out.println("Факториал " + n + "! = " + result);
    }
}
