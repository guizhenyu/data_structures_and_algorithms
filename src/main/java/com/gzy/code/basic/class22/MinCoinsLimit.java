package com.gzy.code.basic.class22;

/**
 * description: MinCoinsLimit date: 2022/8/5 21:59
 *
 * arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
 * 每个值都认为是一种面值，且认为张数是无限的。
 * 返回组成aim的最少货币数
 *
 * @author: guizhenyu
 */
public class MinCoinsLimit {


  public static void main(String[] args) {
    int maxAim = 100;
    int maxLen = 10;
    int maxArrVal = 20;

    int testTime = 10000;
    System.out.println("test start!");
    for (int i = 0; i < testTime; i++){
      int aim = (int)(Math.random() * maxAim);
      int[] arr = generateArr(maxLen, maxArrVal);

      int ans1 = violenceRecursive(arr, aim);
      int ans2 = dp(arr, aim);
      int ans3 = dp1(arr, aim);
      if (ans1 != ans2 || ans3 != ans2){
        System.out.println("Oops!");
        return;
      }

    }

    System.out.println("test end!");

  }

  private static int dp1(int[] arr, int aim) {


    if (arr == null || arr.length == 0){
      return Integer.MAX_VALUE;
    }
    int N = arr.length;
    int[][] dp = new int[N + 1][aim + 1];
    for (int i = 0; i <= N; i++){
      for (int j = 0; j <= aim; j++){
        dp[i][j] = Integer.MAX_VALUE;
      }
    }

    dp[N][0] = 0;

    for (int index = N - 1; index >= 0; index--){
      for (int rest = 0; rest <= aim; rest++){
        dp[index][rest] = dp[index + 1][rest];

        if (rest >= arr[index] && dp[index][rest - arr[index]] != Integer.MAX_VALUE){
          dp[index][rest] = Math.min(dp[index][rest], dp[index][rest - arr[index]] + 1);
        }
      }
    }

    return dp[0][aim];
  }

  private static int dp(int[] arr, int aim) {

    if (arr == null || arr.length == 0){
      return Integer.MAX_VALUE;
    }
    int N = arr.length;
    int[][] dp = new int[N + 1][aim + 1];
    for (int i = 0; i <= N; i++){
      for (int j = 0; j <= aim; j++){
        dp[i][j] = Integer.MAX_VALUE;
      }
    }
    dp[N][0] = 0;
    for (int index = N - 1; index >= 0; index--){
      int coin = arr[index];
      for (int rest = 0; rest <= aim; rest++){
        int ans  = dp[index + 1][rest] ;
        for (int count = 1; count * coin <= rest; count++){
          int next = dp[index + 1][rest - count * coin];
          if (next != Integer.MAX_VALUE){
            ans = Math.min(ans, next + count);
          }
        }
        dp[index][rest] = ans;
      }
    }

    return dp[0][aim];
  }

  private static int violenceRecursive(int[] arr, int aim) {

    if (arr == null || arr.length == 0){
      return Integer.MAX_VALUE;
    }
//    return process(arr, 0, 0, aim);
    return process1(arr,0, aim);

  }

  private static int process1(int[] arr, int index, int rest){
    if (index == arr.length){
      return rest == 0 ? 0 : Integer.MAX_VALUE;
    }

    int ans = Integer.MAX_VALUE;
    for (int count = 0; count * arr[index] <= rest; count++){
      int next = process1(arr, index + 1, rest - count * arr[index]);
      if (next != Integer.MAX_VALUE){
        ans = Math.min(ans, next + count);
      }
    }


    return ans;
  }

  private static int process(int[] arr, int index, int count, int rest) {
    if (rest == 0){
      return count;
    }

    if (index == arr.length || rest < 0){
      return Integer.MAX_VALUE;
    }

    int ans = Integer.MAX_VALUE;
    int coin = arr[index];
    for (int i = 0; i * coin <= rest; i++){
      ans = Math.min(ans, process(arr, index + 1, count + i, rest - i * coin));
    }

    return ans;
  }

  private static int[] generateArr(int maxLen, int maxArrVal) {
    int N = (int)(maxLen * Math.random());
    if (N == 0){
      return null;
    }

    int[] ans = new int[N];
    boolean[] booleans = new boolean[maxArrVal + 1];
    for (int i = 0; i < N; i++){
      do {
        ans[i] = (int)(maxArrVal * Math.random()) + 1;
      }while (booleans[ans[i]]);
      booleans[ans[i]] = true;
    }

    return ans;

  }

}
