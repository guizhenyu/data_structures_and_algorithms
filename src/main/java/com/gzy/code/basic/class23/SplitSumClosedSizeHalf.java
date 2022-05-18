package com.gzy.code.basic.class23;

/**
 * description: SplitSumClosedSizeHalf date: 2022/4/25 19:15
 *
 * @author: guizhenyu
 */
public class SplitSumClosedSizeHalf {

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
      int ans3 = dp2(arr);
      if (ans1 != ans2 || ans2!= ans3) {
        printArray(arr);
        System.out.println(ans1);
        System.out.println(ans2);
        System.out.println(ans3);


        System.out.println("Oops!");
        break;
      }
    }
    System.out.println("测试结束");
  }
  public static int dp2(int[] arr) {
    if (arr == null || arr.length < 2) {
      return 0;
    }
    int sum = 0;
    for (int num : arr) {
      sum += num;
    }
    sum >>= 1;
    int N = arr.length;
    int M = (arr.length + 1) >> 1;
    int[][][] dp = new int[N][M + 1][sum + 1];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j <= M; j++) {
        for (int k = 0; k <= sum; k++) {
          dp[i][j][k] = Integer.MIN_VALUE;
        }
      }
    }
    for (int i = 0; i < N; i++) {
      for (int k = 0; k <= sum; k++) {
        dp[i][0][k] = 0;
      }
    }
    for (int k = 0; k <= sum; k++) {
      dp[0][1][k] = arr[0] <= k ? arr[0] : Integer.MIN_VALUE;
    }
    for (int i = 1; i < N; i++) {
      for (int j = 1; j <= Math.min(i + 1, M); j++) {
        for (int k = 0; k <= sum; k++) {
          dp[i][j][k] = dp[i - 1][j][k];
          if (k - arr[i] >= 0) {
            dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - 1][k - arr[i]] + arr[i]);
          }
        }
      }
    }
    return Math.max(dp[N - 1][M][sum], dp[N - 1][N - M][sum]);
  }


  private static int right(int[] arr) {

    if (null == arr || arr.length < 2){
      return 0;
    }

    int sum = 0;
    for (int num : arr){
      sum += num;
    }
    sum /= 2;

    int len = arr.length;

    if (len % 2 == 0){
      // 偶数
      return process(arr, 0, len / 2, sum);
    }else {
      // 奇数
      return Math.max(process(arr, 0, len / 2, sum), process(arr, 0, (len / 2) + 1, sum));
    }

  }

  private static int process(int[] arr, int i, int k, int rest) {
    if (i == arr.length ){
      return k == 0 ? 0 : -1;
    }

    int p1 = process(arr, i + 1, k, rest);

    int p2 = -1;

    int next = -1;

    if (arr[i] <= rest){
        next = process(arr, i + 1, k - 1, rest - arr[i]);
    }

    if (next != -1){
      p2 = arr[i] + next;
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
    sum /= 2;


    int len = arr.length;
    int picks = (len + 1) / 2;

    int[][][] dp = new int[len + 1][picks + 1][sum + 1];
    for (int i = 0; i <= len; i++){
      for (int k = 0; k <= picks; k++){
        for (int j = 0; j <= sum; j++){
          dp[i][k][j] = -1;
        }
      }
    }
    for (int j = 0; j <= sum; j++){
      dp[len][0][j] = 0;
    }

    for (int i = len - 1; i >= 0; i--){
      for (int k = 0; k <= picks; k++){
        for (int j = 0; j <= sum; j++){
          int p1 = dp[i + 1][k][j];

          int p2 = -1;

          int next = -1;

          if (arr[i] <= j && k > 0){
            next = dp[i + 1][k - 1][j - arr[i]];
          }

          if (next != -1){
            p2 = arr[i] + next;
          }

          dp[i][k][j] =  Math.max(p1, p2);
        }
      }
    }


    if (len % 2 == 0){
      // 偶数
      return dp[0] [len / 2] [sum];
    }else {
      // 奇数
      return Math.max(dp[0][len / 2][sum], dp[0][(len / 2) + 1][ sum]);
    }
  }
}
