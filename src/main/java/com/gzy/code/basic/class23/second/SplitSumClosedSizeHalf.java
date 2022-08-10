package com.gzy.code.basic.class23.second;


/**
 * description: SplitSumClosedSizeHalf date: 2022/8/8 09:37
 * 给定一个正数数组arr，请把arr中所有的数分成两个集合
 * 如果arr长度为偶数，两个集合包含数的个数要一样多
 * 如果arr长度为奇数，两个集合包含数的个数必须只差一个
 * 请尽量让两个集合的累加和接近
 * 返回：
 * 最接近的情况下，较小集合的累加和
 * @author: guizhenyu
 */
public class SplitSumClosedSizeHalf {


  public static void main(String[] args) {
    int maxValue = 50;
    int maxLen = 20;
    int testTime = 10000;
    for (int i = 0; i <= testTime; i++){
      int[] arr = generateArr(maxLen, maxValue);
      int ans1 = violenceRecursive(arr);
      int ans2 = dp(arr);
      int ans3 = dp2(arr);
      if (ans1 != ans2){
        System.out.println("oops!");
      }
    }
  }

  private static int dp2(int[] arr) {
    if (arr == null || arr.length == 0){
      return 0;
    }
    int N = arr.length;
    int sum = 0;
    for (int i = 0; i < N; i++){
      sum += arr[i];
    }
    boolean isEven = (N & 1) == 0;
    int picks = (N + 1) >> 1;
    sum = sum >> 1;
    int[][][] dp = new int[N + 1][picks + 1][sum + 1];

    return 0;
  }


  private static int dp(int[] arr) {
    if (arr == null || arr.length == 0){
      return 0;
    }
    int N = arr.length;
    int sum = 0;
    for (int i = 0; i < N; i++){
      sum += arr[i];
    }
    boolean isEven = (N & 1) == 0;
    int picks = (N + 1) >> 1;
    sum = sum >> 1;
    int[][][] dp = new int[N + 1][picks + 1][sum + 1];
    for (int n = 0; n <= N; n++){
      for (int p = 0; p <= picks; p++){
        for (int r = 0; r <= sum; r++){
          dp[n][p][r] = -1;
        }
      }
    }

    for (int r = 0; r <= sum; r++){
      dp[N][0][r] = 0;
    }

    for (int n = N - 1; n >= 0; n--){
      for (int p = 0; p <= picks; p++){
        for (int rest = 0; rest <= sum; rest++){
          int p1 = dp[n + 1][p][rest];
          int p2 = -1;
          if (rest >= arr[n] && p >= 1){
            int next = dp[n + 1][p - 1][rest - arr[n]];
            if (next != -1){
              p2 = next + arr[n];
            }
          }

          dp[n][p][rest] = Math.max(p1, p2);
        }
      }
    }

    int ans = dp[0][N >> 1][sum];
    if (!isEven){
      ans = Math.max(ans, dp[0][(N + 1) >> 1][sum]);
    }

    return ans;

  }

  private static int violenceRecursive(int[] arr) {

    if (arr == null || arr.length < 2){
      return 0;
    }
    int N = arr.length;
    int sum = 0;
    for (int i = 0; i < N; i++){
      sum += arr[i];
    }

    boolean isEven = (N & 1) == 0;

    int result = process(arr, 0, N / 2, sum / 2);
    if (!isEven){
      return Math.max(process(arr, 0, N / 2 + 1, sum / 2), result);
    }

    return result;


  }

  private static int process(int[] arr, int index, int picks, int rest) {

    if (index == arr.length){
      return picks == 0? 0 : -1;
    }

//    if (picks == 0){
//      return  0;
//    }

    // 不要当前数据
    int p1 = process(arr, index + 1, picks, rest);
    int p2 = -1;
    if (rest >= arr[index]){
      int next = process(arr, index + 1, picks - 1, rest - arr[index]);
      p2 = next == - 1? -1 : next + arr[index];
    }

    return Math.max(p1, p2);

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
