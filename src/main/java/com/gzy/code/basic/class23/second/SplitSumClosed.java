package com.gzy.code.basic.class23.second;

/**
 * description: SplitSumClosed date: 2022/8/7 20:20
 * 给定一个正数数组arr，
 * 请把arr中所有的数分成两个集合，尽量让两个集合的累加和接近
 * 返回：
 * 最接近的情况下，较小集合的累加和
 * @author: guizhenyu
 */
public class SplitSumClosed {


  public static void main(String[] args) {
    int maxValue = 40;
    int maxLen = 20;
    int testTime = 100000;

    for (int i = 0; i < testTime; i++){
      int[] arr = generateArr(maxLen, maxValue);
      
      int ans = violenceRecursive(arr);
      int ans1 = dp(arr);
      if (ans != ans1){
        System.out.println("oops");
      }
    }
  }

  private static int dp(int[] arr) {
    if (arr == null || arr.length == 0){
      return 0;
    }
    int sum = 0;
    for (int i = 0; i < arr.length; i++){
      sum += arr[i];
    }

    sum = sum / 2;
    int N = arr.length;
    int[][] dp = new int[N + 1][sum + 1];

    for (int index = N - 1; index >= 0; index--){
      for (int rest = 0; rest <= sum; rest++){
        dp[index][rest] = dp[index + 1][rest];

        if (rest >= arr[index]){
          dp[index][rest] = Math.max(dp[index][rest], dp[index + 1][rest - arr[index]] + arr[index]);
        }

      }

    }


    return dp[0][sum];
  }

  private static int violenceRecursive(int[] arr) {

    if (arr == null || arr.length == 0){
      return 0;
    }
    int sum = 0;
    for (int i = 0; i < arr.length; i++){
      sum += arr[i];
    }
    return process(arr, 0, sum / 2);
  }

  private static int process(int[] arr, int index, int rest) {
    if (index == arr.length){
      return 0;
    }


    int ans1 = process(arr, index + 1, rest);
    int ans2 = 0;
    if (rest >= arr[index]){
      int p = process(arr, index + 1, rest - arr[index]);

      ans2 = arr[index] + p;
    }


    return Math.max(ans1, ans2);
  }

  private static int[] generateArr(int maxLen, int maxValue) {
    int len = (int)(maxLen * Math.random());
    int[] arr = new int[len];
    for (int i = 0; i < len; i++){
      arr[i] = (int)(maxValue * Math.random());
    }
    
    return arr;
  }
}
