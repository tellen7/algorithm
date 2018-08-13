package com.laowang.forkJoinPool.action;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author wangyonghao
 * @date 2018/8/10
 */
public class PrintDemo {
    public static void main(String[] args) throws InterruptedException {
        PrintAction printAction = new PrintAction(1,70);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(printAction);
        forkJoinPool.awaitTermination(2, TimeUnit.SECONDS);
        forkJoinPool.shutdown();
    }


    static class PrintAction extends RecursiveAction{
        /**每一个PrintAction只打印5个数*/
        private static final int SEGMENT = 5;
        private int start;
        private int end;

        public PrintAction(int start, int end) {
            super();
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (end - start < SEGMENT){
                for (int i = start; i < end ; i++) {
                    System.out.println(Thread.currentThread().getName()+"  "+i);
                }
            }else{
                int middle = (start + end) / 2;
                PrintAction left = new PrintAction(start, middle);
                PrintAction right = new PrintAction(middle, end);
                left.fork();
                right.fork();
            }
        }
    }
}
