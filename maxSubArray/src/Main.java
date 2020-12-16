/**
 * 求给定随机数组中不相邻元素的最大和
 */
public class Main {
    public static void main(String[] args) {
        int val[] = {1,2,4,1,7,8,3};

        System.out.println(rec_opt(val, 6));
        System.out.println(dp_opt(val));
    }

    static int rec_opt(int[] arr, int i) {
        if (i == 0) {
            return arr[0];
        } else if (i == 1) {
            return Math.max(arr[0], arr[1]);
        } else {
            int n = rec_opt(arr, i - 1);
            int y = rec_opt(arr, i - 2) + arr[i];
            return Math.max(n , y);
        }
    }

    static int dp_opt(int[] arr) {
        int dp[] = new int[arr.length];
        dp[0] = arr[0];
        dp[1] = Math.max(arr[0], arr[1]);

        for (int i = 2; i < arr.length; i++) {
            int y = dp[i - 2] + arr[i];
            int n = dp[i - 1];
            dp[i] = Math.max(y, n);
        }

        return dp[arr.length - 1];
    }
}
