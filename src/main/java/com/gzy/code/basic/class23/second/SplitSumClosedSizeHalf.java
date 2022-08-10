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
      int ans3 = dp1(arr);
      if (ans1 != ans2 || ans2 != ans3){
        int s = dp2(arr);
        System.out.println("oops!");
      }
    }
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


  /**
   * 上面的动态规划，是从后往前填的，因为正常的思路就是，每一步的最优值肯定依赖他后面走的步的所有值的，也就是前依赖后
   * 但是我们也可以反过来思考，也就是到到每一步都当成最后一步来看， 这样的话，就是每一步就都依赖前面一步
   *
   * 后 依赖  前
   *
   * @param arr
   * @return
   */
  private static int dp1(int[] arr){
    if (arr == null || arr.length < 2){
      return 0;
    }
    int N = arr.length;
    int sum = 0;
    for (int i = 0; i < N; i++){
      sum += arr[i];
    }
    int picks = (N + 1) >> 1;
    sum = sum >> 1;
    int[][][] dp = new int[N][picks + 1][sum + 1];
    for (int n = 0; n < N; n++){
      for (int p = 0; p <= picks; p++){
        for (int r = 0; r <= sum; r++){
          dp[n][p][r] = -1;
        }
      }
    }
    // 当没有选择时，也就是 pick == 0，都是0
    for (int n = 0; n < N; n++){
      for (int s = 0; s <= sum; s++){
        dp[n][0][s] = 0;
      }
    }
    // 当 pick = 1 时，n = 0,填值
    for(int s = 0; s <= sum; s++){
      if (arr[0] <= s){
        dp[0][1][s] = arr[0];
      }
    }

    // n = 0时， 虽然只填了， pick = 0, 1的情况，因为 pick >1 时，也没有意义，因为，只有一个值可以选
    // 而且，我们这边是按照从前往后填写的
    // 所以，我们下面直接从 n
    for (int n = 1; n < N; n++){
      for (int p = 1; p <= Math.min(n + 1, picks); p++){
        for (int s = 0; s <= sum; s++){
          dp[n][p][s] = dp[n - 1][p][s];
          if (s >= arr[n]){
            dp[n][p][s] = Math.max(dp[n][p][s], dp[n - 1][p - 1][s - arr[n]] + arr[n]);
          }
        }
      }
    }

    return Math.max(dp[N - 1][picks][sum], dp[N - 1][N - picks][sum]);
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

//    return ans;
    return Math.max(dp[0][N >> 1][sum], dp[0][N - (N >> 1)][sum]);

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
