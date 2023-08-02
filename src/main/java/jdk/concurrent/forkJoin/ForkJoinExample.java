package jdk.concurrent.forkJoin;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinExample {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        SumTask sumTask = new SumTask(array, 0, array.length);

        int result = forkJoinPool.invoke(sumTask);
        System.out.println("Sum: " + result);
    }
}
