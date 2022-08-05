package com.gzy.code.basic.class21;

/**
 * description: BobDie date: 2022/8/5 15:54
 * 给定5个参数，N，M，row，col，k
 * 表示在N*M的区域上，醉汉Bob初始在(row,col)位置
 * Bob一共要迈出k步，且每步都会等概率向上下左右四个方向走一个单位
 * 任何时候Bob只要离开N*M的区域，就直接死亡
 * 返回k步之后，Bob还在N*M的区域的概率
 *
 * @author: guizhenyu
 */
public class BobDie {

  public static void main(String[] args) {

    System.out.println(livePosibility2(6, 6, 10, 50, 50));

    System.out.println(dp(6, 6, 10, 50, 50));
  }

  private static int livePosibility2(int row, int col, int k, int N, int M) {


    if (row < 0 || row == N || col < 0 || col == M){
      return 0;
    }


    if (k == 0){
      return 1;
    }

    int ways = 0;
    ways += livePosibility2(row + 1, col, k - 1, N, M);
    ways += livePosibility2(row - 1, col, k - 1, N, M);
    ways += livePosibility2(row, col + 1, k - 1, N, M);
    ways += livePosibility2(row, col - 1, k - 1, N, M);


    return ways;
  }

  private static int dp(int row, int col, int k, int N, int M) {
    if (row < 0 && row >= N && col < 0 && col >= M){
      return 0;
    }

    int[][][] dp = new int[N][M][k + 1];

    for (int r = 0; r < N; r++){
      for (int c = 0; c < M; c++){
        dp[r][c][0] = 1;
      }
    }


    for (int step = 1; step <= k; step++){
      for (int r = 0; r < N; r++){
        for (int c = 0; c < M; c++){
          dp[r][c][step] += getWays(dp, r - 1, c, step - 1, N, M);
          dp[r][c][step] += getWays(dp, r + 1, c, step - 1, N, M);
          dp[r][c][step] += getWays(dp, r, c - 1, step - 1, N, M);
          dp[r][c][step] += getWays(dp, r, c + 1, step - 1, N, M);
        }
      }

    }

    return dp[row][col][k];

  }

  private static int getWays(int[][][] dp, int r, int c, int step, int N, int M) {
    if (r < 0 || r >= N || c < 0 || c >= M){
      return 0;
    }
    return dp[r][c][step];

  }

}
