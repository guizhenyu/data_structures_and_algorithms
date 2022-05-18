package com.gzy.code.basic.class23;

/**
 * description: SplitSumClosed date: 2022/4/25 17:57
 *
 * @author: guizhenyu
 */
public class SplitSumClosed {

  public static int[] randomArray(int len, int value) {
    int[] arr = new int[len];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = (int) (Math.random() * value);
    }
    return arr;
  }

  public static void printArray(int[] arr) {
    for (int num : arr) {
      System.out.print(num + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    int maxLen = 20;
    int maxValue = 50;
    int testTime = 10000;
    System.out.println("测试开始");
    for (int i = 0; i < testTime; i++) {
      int len = (int) (Math.random() * maxLen);
      int[] arr = randomArray(len, maxValue);
      int ans1 = right(arr);
      int ans2 = dp(arr);
      if (ans1 != ans2) {
        printArray(arr);
        System.out.println(ans1);
        System.out.println(ans2);
        System.out.println("Oops!");
        break;
      }
    }
    System.out.println("测试结束");
  }

  private static int right(int[] arr) {

    if (null == arr || arr.length < 2){
      return 0;
    }

    int sum = 0;
    for (int num : arr){
      sum += num;
    }

    return process(arr, 0, sum / 2);

  }

  /**
   *
   *
   * @param arr 数组
   * @param index 现在遍历的索引
   * @param rest 剩余值
   * @return
   */
  private static int process(int[] arr, int index, int rest) {
    // base case
    // 遍历
    if (index == arr.length ){
      return 0;
    }


    // index 位置的数不要
    int p1 = process(arr, index + 1, rest);
    int p2 = 0;
    if (rest >= arr[index]){
      // index 位置的数要
      p2 = arr[index] + process(arr, index + 1, rest - arr[index]);
    }

    return Math.max(p1, p2);


  }

  private static int dp(int[] arr) {

    if (null == arr || arr.length < 2){
      return 0;
    }

    int sum = 0;
    for (int num : arr){
      sum += num;
    }
    int len = arr.length;
    sum = sum / 2;

    int[][] dp = new int[len + 1][sum + 1];

    for (int n = len - 1; n >= 0; n--){
      for (int rest = 0; rest <= sum; rest++){
        int p1 = dp[n + 1][rest];
        int p2 = 0;
        if (rest >= arr[n]){
          // index 位置的数要
          p2 = arr[n] + dp[n + 1][rest - arr[n]];
        }
        dp[n][rest] = Math.max(p1, p2);
      }
    }

    return dp[0][sum];
  }

}
