/**
 * 给定随机数组arr，求数组中是子序列的和等于sum
 * 如: arr = {3,34,4,12,5,2}; sum = 9;[
 */
public class Main {
    public static void main(String[] args) {
        int[] arr = {3,34,4,12,5,2};
        int sum = 9;

        System.out.println(rec_opt(arr, arr.length -1, 9));
        System.out.println(rec_opt(arr, arr.length -1, 10));
        System.out.println(rec_opt(arr, arr.length -1, 11));
        System.out.println(rec_opt(arr, arr.length -1, 12));
        System.out.println(rec_opt(arr, arr.length -1, 13));
        System.out.println(dp_opt(arr, 9));
        System.out.println(dp_opt(arr, 10));
        System.out.println(dp_opt(arr, 11));
        System.out.println(dp_opt(arr, 12));
        System.out.println(dp_opt(arr, 13));
    }

    static boolean rec_opt(int[] arr, int i, int sum) {
        // 1. 如果sum = 0，则返回true
        if (sum == 0) {
            return true;
        } else
        // 2. 如果index = 0时，arr[0] == sum,则刚好返回true
        if (i == 0) {
            return arr[0] == sum;
        } else
        // 3. 如果当前arr[i]的值大于sum，那就不能选当前值，因为sum-arr[i]会得到负数，那结果是在不选当前值的组合里
        if (arr[i] > sum) {
            return rec_opt(arr, i - 1, sum);
        } else {
            boolean y = rec_opt(arr, i - 1, sum - arr[i]);
            boolean n = rec_opt(arr, i - 1, sum);
            return y || n;
        }
    }

    static boolean dp_opt(int[] arr, int sum) {
        // set[indexOfArr][currentSum]
        boolean set[][] = new boolean[arr.length][sum + 1];
        for (int i = 0; i < arr.length; i++) {
            set[i][0] = true; // when sum == 0, result is true
        }
        for (int s = 0; s < sum +1; s++) {
            if (arr[0] == s) {
                // only when arr[i] == sum, result is true, or result is false
                set[0][s] = true;
                continue;
            }
            set[0][s] = false;
        }

        for (int index = 1; index < arr.length; index++) {
            for (int s = 1; s < sum + 1; s++) {
                if (arr[index] > s) {
                    // when arr[i] larger then current sum, result will be arr[i-1][s]
                    set[index][s] = set[index - 1][s];
                } else {
                    boolean y = set[index - 1][s - arr[index]]; // 选了当前的值，则看arr[i-1]能否得到sum = sum-arr[i]
                    boolean n = set[index - 1][s]; // 没有选当前的值，则看arr[i-1]能否得到sum
                    set[index][s] = y || n; // 当前arr[i]能不能得到sum，要看选当前arr[i]和不选当前arr[i]能不能得到sum
                }
            }
        }
        return set[arr.length - 1][sum];
    }
}
