package com.laowang.forkJoinPool.task;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 *
 * @author wangyonghao
 * @date 2018/8/10
 */
public class SumDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[] array = new int[100];
        int sum = 0;
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(10);
            sum += array[i];
        }
        System.out.println("sum="+sum);

        SumTask sumTask = new SumTask(array,0,100);
        ForkJoinPool forkJoinPool = new ForkJoinPool(3);
        Future<Integer> future = forkJoinPool.submit(sumTask);

        System.out.println("执行结果: "+future.get());
        forkJoinPool.shutdown();
    }

    static class SumTask extends RecursiveTask<Integer>{
        private static final int RANGE = 10;
        private int start;
        private int end;
        private int[] array;

        public SumTask(int[] array, int start, int end) {
            super();
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            int sum = 0;
            if (end - start < RANGE){
                for (int i = start; i < end; i++) {
                    sum += array[i];
                }
                System.out.println(Thread.currentThread().getName()+" calculate from="+start+", end="+end+", sum="+sum);
                return sum;
            }else{
                int middle = (start + end) / 2;
                SumTask left = new SumTask(array,start,middle);
                SumTask right = new SumTask(array,middle,end);
                left.fork();
                right.fork();
                return left.join() + right.join();
            }
        }
    }
}
