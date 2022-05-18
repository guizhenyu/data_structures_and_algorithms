package com.gzy.code.basic.class18;

/**
 * description: RobotWalk date: 2022/4/8 8:43 下午
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
    // 总共7个位置
    int positions = 5;

    int destination = 4;
    int start = 2;

    int k = 6;

    int ways = walk1(start, destination, positions, k);
    System.out.println("walk1: " + ways);

    int ways2 = walk2(start, destination, positions, k);
    System.out.println("walk2: " + ways2);

    int ways3 = walk3(start, destination, positions, k);
    System.out.println("walk3: " + ways3);


  }

  private static int walk3(int start, int destination, int positions, int k) {

    int[][] dp = new int[positions + 1][k + 1];

    dp[destination][0] = 1;

    for (int rest = 1; rest <= k; rest++){
      dp[1][rest] = dp[2][rest - 1];
      for (int n = 2; n < positions; n++){
        dp[n][rest] = dp[n - 1][rest - 1] + dp[n + 1][rest - 1];
      }
      dp[positions][rest] = dp[positions - 1][rest - 1];
    }

    return dp[start][k];
  }

  /**
   * 动态规划
   * @param start
   * @param destination
   * @param positions
   * @param k
   * @param
   * @return
   */
  private static int walk2(int start, int destination, int positions, int k) {

    int[][] dp = new int[positions + 1][k + 1];

    for (int i = 0; i <= positions; i++) {
      for (int j = 0; j <= k; j++) {
        dp[i][j] = -1;
      }
    }
    return way2(start, destination, positions, k, dp);
  }

  private static int way2(int cur, int destination, int positions, int k, int[][] dp) {

    if (dp[cur][k] != -1){
      return dp[cur][k];
    }
    int ans = 0;
    if (k == 0){
      ans = cur == destination ? 1 : 0;
    }else if (cur == 1){
      ans =  way2(cur + 1, destination, positions, k - 1, dp);
    }else if (cur == positions){
      ans =  way2(cur - 1, destination, positions, k - 1, dp);
    }else {
      ans = way2(cur + 1, destination, positions, k - 1, dp) + way2(cur - 1, destination, positions, k - 1, dp);
    }
    dp[cur][k] = ans;
    return ans;
  }

  private static int walk1(int cur, int destination, int positions, int k) {
    if (k == 0){
      return cur == destination? 1 : 0;
    }
    if (cur == 1){
      return walk1(cur + 1, destination, positions, k - 1);
    }
    if (cur == positions){
      return walk1(cur - 1, destination, positions, k - 1);
    }
    return walk1(cur + 1, destination, positions, k - 1) + walk1(cur - 1, destination, positions, k - 1);
  }

}
