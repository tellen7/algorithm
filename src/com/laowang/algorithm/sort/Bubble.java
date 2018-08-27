package com.laowang.algorithm.sort;

import java.util.Random;

/**
 *
 * @author wangyonghao
 * @date 2018/8/27
 */
public class Bubble {

    /**
     * 冒泡排序
     * 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
     * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这样最大的元素会升到最后就像冒泡一样。
     * 针对所有的元素重复以上的步骤，除了最后一个（已排序序列）。
     * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
     */
    public static void bubbleSort(int[] numbers)
    {
        int temp = 0;
        int size = numbers.length;
        for(int i = 0 ; i < size-1; i ++) {
            for(int j = 0 ;j < size-1-i ; j++) {
                // 如果满足条件，交换两数位置
                if(numbers[j] > numbers[j+1]) {
                    temp = numbers[j];
                    numbers[j] = numbers[j+1];
                    numbers[j+1] = temp;
                }
            }
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

        bubbleSort(array);

        for (int i = 0; i < 100; i++) {
            System.out.print(" "+array[i]);
        }
    }
}
