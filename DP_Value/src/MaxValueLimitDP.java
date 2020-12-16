/**
 * 给定N个物品，在背包容量有限的情况下，求最大能装入背包的物品的价值
 * 如：背包容量为8，给定4个物品
 * 物品编号 1 2 3 4
 * 物品体积 2 3 4 5
 * 物品价值 3 4 5 6
 * 求背包最大价值
 *
 * 方案是画表（搜先考虑检查体积，看能不能装下当前编号为i的物品，不能则和前 i-1个物品背包最大价值一样
 *           如果能装下当前i号物品，则考虑1：不装当前i号物品背包最大价值【此时和前 i-1个物品背包最大价值一样】
 *                                考虑2：装了当前i号物品，则背包最大价值和当前物品价值+前i-1个物品里剩余背包空间的最大价值之和）
 * 物品编号/背包容量
 *          0   1   2   3   4   5   6   7   8
 *     0    0   0   0   0   0   0   0   0   0   // 前0个物品，背包装不到物品，背包价值为0
 *     1    0   0   3   3   3   3   3   3   3   // 前1个物品，背包在不同容量情况下所能装的物品价值情况（不装1号物品则背包价值和前0号物品背包价值一样）
 *     2    0   0   3   4   4   7   7   7   7
 *     3    0   0   3   4   5   7   8   9   9
 *     4    0   0   3   4   5   7   8   9   10
 */
public class MaxValueLimitDP {
    public static void main(String[] args) {
        int[][] arr = {{0,0},{2,3},{3,4},{4,5},{5,6}}; // 体积、价值
        System.out.println(dp_opt(arr, 8));
        System.out.println(dp_opt(arr, 7));
        System.out.println(dp_opt(arr, 6));
        System.out.println(dp_opt(arr, 5));
        System.out.println(dp_opt(arr, 4));
        System.out.println(dp_opt(arr, 3));
        System.out.println(dp_opt(arr, 2));
        System.out.println(dp_opt(arr, 1));
        System.out.println(dp_opt(arr, 0));
        System.out.println(dp_opt(arr, 13));
        System.out.println(dp_opt(arr, 14));
        System.out.println(dp_opt(arr, 15));
    }

    static int dp_opt(int[][] arr, int cap) {
        int[][] result = new int[arr.length][cap+1];

        for (int i = 0; i < cap + 1; i++) {
            result[0][i] = 0; // 前0个物品背包价值
        }

        for (int i = 0; i < arr.length; i++) {
            result[i][0] = 0;
        }

        for (int i = 1; i < arr.length; i++) { // i为物品编号
            for (int j = 1; j < cap + 1; j++) {    // j为背包容量
                if (arr[i][0] > j) { // 当前背包容量放不下当前物品时
                    result[i][j] = result[i-1][j];
                } else {
                    // 选择装当前第i号物品时，背包价值
                    int y = arr[i][1] + result[i-1][j-arr[i][0]];
                    // 不选当前i号物品时，背包价值
                    int n = result[i-1][j];
                    result[i][j] = y > n ? y : n;
                }
            }
        }

        return result[arr.length - 1][cap];
    }
}
