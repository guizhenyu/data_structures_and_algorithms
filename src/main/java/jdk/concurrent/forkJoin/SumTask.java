package jdk.concurrent.forkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class SumTask extends RecursiveTask<Integer> {
    private static final int THRESHOLD = 10; // 阈值，小于等于该值时直接计算结果
    private int[] array;
    private int start;
    private int end;

    public SumTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (end - start <= THRESHOLD) {
            // 当任务足够小，直接计算结果
            int sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        } else {
            // 将任务拆分成两个子任务
            int mid = (start + end) / 2;
            SumTask leftTask = new SumTask(array, start, mid);
            SumTask rightTask = new SumTask(array, mid, end);

            // 提交子任务给 ForkJoinPool 并等待完成
            leftTask.fork();
            int rightResult = rightTask.compute();

            // 等待左子任务完成并获取结果
            int leftResult = leftTask.join();

            // 合并子任务的结果
            return leftResult + rightResult;
        }
    }
}



