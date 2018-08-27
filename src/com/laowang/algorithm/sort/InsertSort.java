package com.laowang.algorithm.sort;

import java.util.Random;

/**
 *
 * @author wangyonghao
 * @date 2018/8/27
 */
public class InsertSort {

    /**
     *  插入排序
     * 1.假设无序数组第一个元素是有序的
     * 2.以次把有序序列后面的元素插入到有序队列中，涉及到有序队列元素的后移操作
     * */
    public static int[] insertionSort(int[] array, int n) {
        int i, j, temp;

        for(i = 1; i < n; i++){
            temp = array[i];
            for(j = i; j > 0 && array[j - 1] > temp; j-- ){
                array[j] = array[j - 1];
            }
            array[j] = temp;
        }

        return array;
    }

    public static void main(String[] args) {
        Random random = new Random();
        int[] array = new int[100];
        for (int i = 0; i < 100; i++) {
            array[i] = random.nextInt(100);
            System.out.print(" "+array[i]);
        }
        System.out.println();

        insertionSort(array,array.length);

        for (int i = 0; i < 100; i++) {
            System.out.print(" "+array[i]);
        }
    }
}
