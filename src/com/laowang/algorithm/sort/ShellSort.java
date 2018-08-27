package com.laowang.algorithm.sort;

import java.util.Random;

/**
 *
 * @author wangyonghao
 * @date 2018/8/27
 */
public class ShellSort {
    /**
     *  希尔排序（插入排序的一种）
     *  1.根据步长分组，对每组使用直接插入排序算法
     *  2.缩短步长，再次执行步骤一，直到步长为1停止
     * */
    public static void sort(int[] a) {
        // 设置步长，默认为数组长度的一半
        int step = a.length / 2;
        while (step >= 1) {
            for (int i = step; i < a.length; i += step) {
                int tmp = a[i];
                int j;
                for (j = i; j > 0 && a[j - step] > tmp; j -= step) {
                    a[j] = a[j - step];//元素后移
                }
                //插入的位置，注意此时j在for循环中已经进行了一次--
                a[j] = tmp;
            }
            step /= 2;
        }
    }
    public static void main(String[] args) {
        Random random = new Random();
        int[] array = new int[100];
        for (int i = 0; i < 100; i++) {
            array[i] = random.nextInt(100);
            System.out.print(" "+array[i]);
        }
        System.out.println();

        sort(array);

        for (int i = 0; i < 100; i++) {
            System.out.print(" "+array[i]);
        }
    }
}
