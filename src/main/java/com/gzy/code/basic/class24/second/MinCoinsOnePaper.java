package com.gzy.code.basic.class24.second;



import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 返回组成aim的最少货币数
 * 注意：因为是求最少货币数，所以每一张货币认为是相同或者不同就不重要了
 *
 */
public class MinCoinsOnePaper {

    /**
     *  给定一个数组 arr[1,5,10,1,20,5,10,100,50,15] 代表不同面值的货币，有重复的
     *  给定一个数： aim (例如 87)
     *  求 从数组中选取N张货币的值正好等于 aim, N的最小值
     */

    public static void main(String[] args) {
        int testTime = 10000;

        // 货币数
        int maxArrLen = 20;
        // 货币的最大面值
        int maxValue = 30;
        // aim的最大值
        int aimMax = 30;


        for (int i = 0; i < testTime; i++){

            int[] arr = generate(maxArrLen, maxValue);
            int aim = randomByValue(aimMax);
            int ans = natureWisdom(arr, aim);
            int ans1 = dp1(arr, aim);
            int ans2 = dp2(arr, aim);
            int ans3 = dp3(arr, aim);
            if (ans != ans1
                    || ans1 != ans2
                    || ans2 != ans3
            ){
                System.out.println("oops");
//                break;
            }
        }
    }

    private static int dp3(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim <= 0){
            return Integer.MAX_VALUE;
        }

        // 时间复杂度 O(N)
        Info info = generateInfo(arr);
        int[] coins = info.getValues();
        int[] counts = info.getCount();

        int N = coins.length;
        int[][] dp = new int[N + 1][aim + 1];
        for (int i = 1; i <= aim; i++){
            dp[N][i] = Integer.MAX_VALUE;
        }
        dp[N][0] = 0;
        // dp2第三层for循环中，有一些重复计算，rest, rest + coins[index], rest + 2 * coins[index] ... rest + counts[index] * coins[index]
        // ,每次算都会从 0开始计算，其实他们是可以依赖的，这边我们可以通过双端队列，然后只要 求出 mod = aim % coins[index]
        // 然后 第二层循环里只要 便利 0 ～ mod, 然后第三层for循环中，然后 只要遍历 mod + coins[index] .... mod + coins[index] * n
        for (int n = N - 1; n >= 0; n--){
            for (int mod = 0; mod < Math.min(aim + 1, coins[n]); mod++){
                dp[n][mod] = dp[n + 1][mod];
                // 双端队列
                LinkedList<Integer> minRest = new LinkedList<>();
                minRest.addLast(mod);
                for (int rest = mod + coins[n]; rest <= aim; rest += coins[n]){
                    while (!minRest.isEmpty() && (dp[n + 1][minRest.peekLast()] == Integer.MAX_VALUE
                    || dp[n + 1][minRest.peekLast()] + compensate(minRest.peekLast(), rest, coins[n]) >= dp[n + 1][rest])){
                        // minRest中有两种情况要弹出， 第一种是dp[n + 1][minRest.peekLast()] 是最大值也就是不存在，
                        // 还有一种是存在的时候，但是大于 dp[n + 1][rest]

                        minRest.pollLast();
                    }
                    minRest.addLast(rest);
                    // 计算下，当前的rest到不了的情况
                    int overdue = rest - (counts[n] + 1) * coins[n];
                    if (minRest.peekFirst() == overdue){
                        minRest.pollFirst();
                    }
                    dp[n][rest] = dp[n + 1][minRest.peekFirst()] + compensate(minRest.peekFirst(), rest, coins[n]);
                }
            }
        }



        return dp[0][aim];
    }

    private static int compensate(Integer peekLast, int rest, int coin) {

        return (rest - peekLast) / coin;
    }

    // 时间复杂度：  O(N) +  aim * 货币的种类 * 货币的平均数量
    private static int dp2(int[] arr, int aim){
        if (arr == null || arr.length == 0 || aim <= 0){
            return Integer.MAX_VALUE;
        }

        // 时间复杂度 O(N)
        Info info = generateInfo(arr);
        int[] coins = info.getValues();
        int[] counts = info.getCount();
        
        int N = coins.length;
        int[][] dp = new int[N + 1][aim + 1];
        for (int i = 1; i <= aim; i++){
            dp[N][i] = Integer.MAX_VALUE;
        }
        dp[N][0] = 0;

        //时间复杂度： aim * 货币的种类 * 货币的平均数量
        for (int n = N - 1; n >= 0; n--){
            for (int rest = 0; rest <= aim; rest++){
                int ans  = dp[n + 1][rest];
                for (int c = 1; c <= counts[n] && coins[n] * c <= rest; c++){
                    int next = dp[n + 1][rest - c * coins[n]];
                    if (next != Integer.MAX_VALUE){
                        ans = Math.min(ans, next + c);
                    }
                }

                dp[n][rest] = ans;
            }
        }

        return dp[0][aim];
    }


    private static Info generateInfo(int[] arr) {
        Map<Integer, Integer> valueAndCountMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++){
            if (valueAndCountMap.containsKey(arr[i])){
                valueAndCountMap.put(arr[i], valueAndCountMap.get(arr[i]) + 1);
            }else {
                valueAndCountMap.put(arr[i], 1);
            }
        }
        int size = valueAndCountMap.size();
        int[] values = new int[size];
        int[] counts = new int[size];
        int index = 0;
        for (Integer value : valueAndCountMap.keySet()){
            values[index] = value;
            counts[index++] = valueAndCountMap.get(value);
        }

        return new Info(values, counts);
    }

    // 时间复杂度： N * aim
    private static int dp1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim <= 0){
            return Integer.MAX_VALUE;
        }

        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        for (int j = 0; j <= aim; j++){
            dp[N][j] = Integer.MAX_VALUE;
        }
        dp[N][0] = 0;

        for (int n = N - 1; n >= 0; n--){
            for (int rest = 0; rest <= aim; rest++){
                int ans = dp[n + 1][rest];
                if (arr[n] <= rest && dp[n + 1][rest - arr[n]] != Integer.MAX_VALUE){
                    ans = Math.min(ans, dp[n + 1][rest - arr[n]] + 1);
                }
                dp[n][rest] = ans;
            }
        }
        return dp[0][aim];

    }

    /**
     *  给定一个数组 arr[1,5,10,1,20,5,10,100,50,15] 代表不同面值的货币，有重复的
     *  给定一个数： aim (例如 87)
     *  求 从数组中选取N张货币的值正好等于 aim, N的最小值
     */
    private static int natureWisdom(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim <= 0){
            return Integer.MAX_VALUE;
        }

        return process(arr, 0, aim);
    }

    private static int process(int[] arr,int index,  int rest) {
        if (index == arr.length){
            return rest == 0? 0 : Integer.MAX_VALUE;
        }

        //来到index，有两种情况，要当前的货币，不要当前货币

        int p1 = process(arr, index + 1, rest);
        int p2 = Integer.MAX_VALUE;
        if (rest >= arr[index]){
            int next = process(arr, index + 1, rest - arr[index]);
            if (next != Integer.MAX_VALUE){
                p2 = next + 1;
            }
        }

        return Math.min(p1, p2);
    }


    private static int[] generate(int maxArrLen, int maxValue) {
        int len = randomByValue(maxArrLen);

        int[] arr = new int[len];

        for (int i = 0; i < len; i++){
            arr[i] = randomByValue(maxValue);
        }

        return arr;
    }

    private static int randomByValue(int v){
        return (int)(Math.random() * v) + 1;
    }

}

class Info{
    private int[] values;
    private int[] count;

    public Info(int[] values, int[] count) {
        this.values = values;
        this.count = count;
    }

    public int[] getValues() {
        return values;
    }

    public void setValues(int[] values) {
        this.values = values;
    }

    public int[] getCount() {
        return count;
    }

    public void setCount(int[] count) {
        this.count = count;
    }
}