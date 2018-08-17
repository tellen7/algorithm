package com.laowang.source;

import java.awt.*;
import java.util.Scanner;

/**
 *
 * @author wangyonghao
 * @date 2018/8/15
 * 说明：这里是hashMap的含参(初始化容量)构造函数,其底层会用大于给定参数并且是2 的指数倍的数来初始化容量
 */
public class tableSizeFor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int initSize = scanner.nextInt();
        int result = tablesizefor(initSize + (initSize >>> 1) + 1);
        System.out.println(result);

        //计算hash 值
        System.out.println(spreed(15));
    }

    public static int tablesizefor(int c){
        int maxValue = 1 << 30;
        int n = c - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;

        return (n < 0) ? 1 : (n >= maxValue) ? maxValue : n + 1;
    }

    public static int spreed(int h){
        //最高位前值为零。原因如下
        //sets of hashes that vary only in bits above the current mask will always collide
        //仅在当前掩码上方的位上变化的散列集合总是会发生碰撞。
        //简单理解为 为了减少碰撞
        int hash_bits = 0x7fffffff;

        return (h ^ (h >>> 16)) & hash_bits;
    }
}
