package com.gzy.code.basic.class18.again;

/**
 * description: RpbotWalk date: 2022/7/19 14:57
 *
 * @author: guizhenyu
 */
public class RobotWalk {

  /**
   * 机器人在固定的步数走到目标位置，总共有多少种way
   *
   * 规则:
   *  1. 有N个位置点， 1 ~ N
   *  2. 机器人每次只能走一步
   *  3. 走到位置1，下一步只能往右走
   *     走到N位置，下一步只能往左走
   *     在中间，两边都可以走
   *
   * @param args
   */


  public static void main(String[] args) {
    int N = 5;
    int K = 6;
    int aim = 4;
    int start = 2;
    
    int ways1 = natureWisdom(N, start, aim, K);
    int ways2 = dp1(N, start, aim, K);
    // todo :这边要注意的是，填二维表的规则是，从左往右，从上往下
    int ways3 = dp2(N, start, aim, K);

    System.out.println();


  }

  private static int dp1(int N, int start, int aim, int k) {
    if (N < 2 || start < 1 ||  start > N || aim < 1 || aim > N || k < 1){
      return -1;
    }

    int[][] dp = new int[N + 1][k + 1];

    for (int i = 0; i <= N; i++){
      for (int j = 0; j <= k; j++){
        dp[i][j] = -1;
      }
    }

    return process2(N, start, aim, k, dp);
  }

  private static int process2(int n, int cur, int aim, int rest, int[][] dp) {
    if (dp[cur][rest] != -1){
      return dp[cur][rest];
    }
    int ans = 0;
    if (rest == 0){
      ans = cur == aim? 1 : 0;
    }else if (cur == 1){
      ans = process2(n, cur + 1, aim, rest - 1, dp);
    }else if (cur == n){
      ans = process2(n, cur - 1, aim, rest - 1, dp);
    }else {
      ans = process2(n, cur - 1, aim, rest - 1, dp) + process2(n, cur + 1, aim, rest - 1, dp);
    }
    dp[cur][rest] = ans;
    return dp[cur][rest];
  }

  private static int dp2(int N, int start, int aim, int k) {
    if (N < 2 || start < 1 ||  start > N || aim < 1 || aim > N || k < 1){
      return -1;
    }

    int[][] dp = new int[N + 1][k + 1];
    dp[aim][0] = 1;

      for (int j = 1; j <= k; j++){
        for (int i = 1; i <= N; i++){
        if (i == 1){
          dp[1][j] = dp[2][j - 1];
        }else if(i == N){
          dp[N][j] = dp[N - 1][j - 1];
        }else {
          dp[i][j] = dp[i + 1][j - 1] + dp[i - 1][j - 1];
        }
      }
    }
    return dp[start][k];
  }

  private static int dp12(int N, int start, int aim, int k) {
    if (N < 2 || start < 1 ||  start > N || aim < 1 || aim > N || k < 1){
      return -1;
    }

    int[][] dp = new int[N + 1][k + 1];
    dp[aim][0] = 1;
    for (int rest = 1; rest <= k; rest++){
      dp[1][rest] = dp[2][rest - 1];
      for (int n = 2; n < N; n ++){
        dp[n][rest] = dp[n - 1][rest - 1] + dp[n + 1][rest - 1];

      }
      dp[N][rest] = dp[N - 1][rest - 1];

    }
    return dp[start][k];
  }

  private static int natureWisdom(int N, int start, int aim, int k) {

    if (N < 2 || start < 1 ||  start > N || aim < 1 || aim > N || k < 1){
      return -1;
    }

    return process1(N, start, aim, k);

  }

  private static int process1(int n, int cur, int aim, int rest) {
    if (rest == 0){
      return cur == aim? 1 : 0;
    }
    if (cur == 1){
      return process1(n, cur + 1, aim, rest - 1);
    }
    
    if (cur == n){
      return process1(n, cur - 1, aim, rest - 1);
    }
    
    return process1(n, cur - 1, aim, rest - 1) + process1(n, cur + 1, aim, rest - 1);

  }


}
