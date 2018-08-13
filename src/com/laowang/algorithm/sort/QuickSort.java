package com.laowang.algorithm.sort;

import java.util.Random;

/**
 * 快排：基本思想：采用分治法，每一轮的排序将待排序序列用基准数（一般为了方便选择待排序的第一个数字）分割成独立的两部分，
 * 其中左边部分序列均比基准数小；右边部分序列均比基准数大。之后递归上述步骤。
 *
 * @author wangyonghao
 * @date 2018/8/10
 */
public class QuickSort {
    public static void main(String[] args) {
        Random random = new Random();
        int[] array = new int[100];
        for (int i = 0; i < 100; i++) {
            array[i] = random.nextInt(100);
            System.out.print(array[i]+" ");
        }
        System.out.println();

        //方法一
        //quickSort(0,99,array);
        //方法二
        quickSort(array);

        for (int i = 0; i < 100; i++) {
            System.out.print(array[i]+" ");
        }
    }

    /**
     * 方法一
     * */
    public static void quickSort(int left, int right, int[] a) {
        int i, j, t, temp;

        if (left > right) {
            return;
        }

        //temp中存的就是基准数
        temp = a[left];
        i = left;
        j = right;
        while (i != j) {
            //顺序很重要，要先从右边开始找
            while (a[j] >= temp && i < j) {
                j--;
            }
            //再找右边的
            while (a[i] <= temp && i < j) {
                i++;
            }
            //交换两个数在数组中的位置
            if (i < j) {
                t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
        //最终将基准数归位
        a[left]=a[i];
        a[i]=temp;

        //继续处理左边的，这里是一个递归的过程
        quickSort(left,i-1,a);
        //继续处理右边的 ，这里是一个递归的过程
        quickSort(i+1,right,a);
    }


    /**
     * 方法二
     */
    public static void sort(int a[], int low, int hight) {
        int i, j, index;
        if (low > hight) {
            return;
        }
        i = low;
        j = hight;
        // 用第一个记录做基准
        index = a[i];
        // 从表的两端交替向中间扫描
        while (i < j) {
            //先从后面找到比index小的数
            while (i < j && a[j] >= index) {
                j--;
            }
            if (i < j) {
                //用比基准小的记录替换低位记录
                a[i++] = a[j];
            }
            //再从前面找比index大的数
            while (i < j && a[i] < index) {
                i++;
            }
            if (i < j) {
                // 用比基准大的记录替换高位记录
                a[j--] = a[i];
            }
        }
        // 将基准数值替换回 a[i]
        a[i] = index;
        // 对左边序列进行递归排序
        sort(a, low, i - 1);
        // 对右边序列进行递归排序
        sort(a, i + 1, hight);

    }

    public static void quickSort(int a[]) {
        sort(a, 0, a.length - 1);
    }

}
