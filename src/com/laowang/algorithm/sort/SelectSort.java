package com.laowang.algorithm.sort;

import java.util.Random;

/**
 *
 * @author wangyonghao
 * @date 2018/8/27
 */
public class SelectSort {

    /**
     * 选择排序
     * 1.找出待排序序列的最小元素下标
     * 2.交换最小元素和已排序序列后面紧跟的元素
     * 3.重复上述操作
     * */
    public static void selectionSort(int[] A, int n) {
        for (int i = 0; i < n - 1; i++) {
            int index = i;
            int j;
            // 找出最小值得元素下标
            for (j = i + 1; j < n; j++) {
                if (A[j] < A[index]) {
                    index = j;
                }
            }
            int tmp = A[index];
            A[index] = A[i];
            A[i] = tmp;
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

        selectionSort(array,array.length);

        for (int i = 0; i < 100; i++) {
            System.out.print(" "+array[i]);
        }
    }
}
