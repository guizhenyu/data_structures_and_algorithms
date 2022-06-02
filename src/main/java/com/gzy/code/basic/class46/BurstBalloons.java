package com.gzy.code.basic.class46;

import java.util.Arrays;
import sun.nio.cs.ext.MacCentralEurope;

/**
 * description: BurstBalloons date: 2022/5/31 20:13
 *
 * @author: guizhenyu
 */
public class BurstBalloons {

  private static int ways1(int[] arr) {
    if (arr == null || arr.length == 0){
      return 0;
    }

    int len = arr.length;
    int[] newArr = new int[len + 2];
    newArr[0] = 1;
    newArr[len + 1] = 1;
    for (int i = 1; i <= len; i++){
      newArr[i] = arr[i - 1];
    }
    return natureWisdom(newArr, 1, len);
  }

  /**
   * 删除L ~ R 范围内的数，并且 L - 1 和 R + 1位置的数还没有删除
   * @param arr
   * @param L
   * @param R
   * @return
   */
  public static int natureWisdom(int[] arr, int L, int R){
    if (L > R){
      return 0;
    }
    if (L == R){
      return arr[L] * arr[L - 1] * arr[R + 1];
    }
    // 先求两种位置的得分
    // 第一种可能是，L位置是最后删除
    // 第二种可能是，R位置是最后删除
    int ans = Math.max(arr[L] * arr[L - 1] * arr[R + 1] + natureWisdom(arr, L + 1, R),
        natureWisdom(arr, L, R - 1) + arr[L - 1] * arr[R] * arr[R + 1]);


    for (int i = L + 1; i < R; i++){
      int left = natureWisdom(arr, L, i - 1);
      int right = natureWisdom(arr, i + 1, R);
      ans = Math.max(ans, left + right + arr[i] * arr[L - 1] * arr[R + 1]);
    }
    return ans;
  }

  public static int ways2(int[] arr){
    if (arr == null || arr.length == 0){
      return 0;
    }

    int N = arr.length;

    int[] help = new int[N + 2];
    help[0] = 1;
    help[N + 1] = 1;
    for (int i = 0; i < N; i++ ){
      help[i + 1] = arr[i];
    }

    int[][] dp = new int[N + 2][N + 2];

    for (int i = 1; i <= N; i++){
      dp[i][i] = help[i] * help[i - 1] * help[i + 1];
    }

    for (int L = N - 1; L >= 1; L--){
      for (int R = L + 1; R <= N; R++){
        int ans = Math.max(dp[L + 1][ R] + help[L] * help[L - 1] * help[R + 1],
              dp[L][R - 1] + help[L - 1] * help[R] * help[R + 1]);
        for (int j = L + 1; j < R; j++){
          ans = Math.max(ans, dp[L][j - 1] + dp[j + 1][R] + help[j] * help[L - 1] * help[R + 1]);
        }
        dp[L][R] = ans;
      }
    }
    return dp[1][N];
  }


}
