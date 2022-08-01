package com.gzy.code.basic.class21;

/**
 * description: MinPathSum date: 2022/8/1 15:06
 * 给定一个二维数组matrix，一个人必须从左上角出发，最后到达右下角
 * 沿途只可以向下或者向右走，沿途的数字都累加就是距离累加和
 * 返回最小距离累加和
 * @author: guizhenyu
 */
public class MinPathSum {

  // for test
  public static int[][] generateRandomMatrix(int rowSize, int colSize) {
    if (rowSize < 0 || colSize < 0) {
      return null;
    }
    int[][] result = new int[rowSize][colSize];
    for (int i = 0; i != result.length; i++) {
      for (int j = 0; j != result[0].length; j++) {
        result[i][j] = (int) (Math.random() * 100);
      }
    }
    return result;
  }

  // for test
  public static void printMatrix(int[][] matrix) {
    for (int i = 0; i != matrix.length; i++) {
      for (int j = 0; j != matrix[0].length; j++) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    int rowSize = 10;
    int colSize = 10;
    int[][] m = generateRandomMatrix(rowSize, colSize);
    System.out.println(minPathSum1(m));
    System.out.println(minPathSum2(m));

  }



  private static int minPathSum1(int[][] m) {
    if (m == null || m.length == 0 || m[0] == null || m[0].length == 0){
      return 0;
    }

    int N = m.length;
    int M = m[0].length;

    int[][] dp = new int[N][M];
    dp[0][0] = m[0][0];
    for (int i = 1; i < N; i++){
      dp[i][0] = dp[i - 1][0] + m[i][0];
    }

    for (int i = 1; i < M; i++){
      dp[0][i] = dp[0][i - 1] + m[0][i];
    }


    for (int i = 1; i < N; i++){
      for(int j = 1; j < M; j++){
        dp[i][j] = Math.min(dp[i - 1][j] + m[i][j], dp[i][j - 1] + m[i][j]);
      }
    }
    return dp[N - 1][M - 1];
  }


  // 这边是做空间的优化，dp变成一维的
  private static int minPathSum2(int[][] m) {
    if (m == null || m.length == 0 || m[0] == null || m[0].length == 0){
      return 0;
    }
    int row = m.length;
    int col = m[0].length;
    int[] dp = new int[col];
    dp[0] = m[0][0];
    for (int i = 1; i < col; i++){
      dp[i] = dp[i - 1] + m[0][i];
    }
    for (int r = 1; r < row; r++){
      dp[0] += m[r][0];
      for (int c = 1; c < col; c++){
        dp[c] = Math.min(dp[c - 1], dp[c]) + m[r][c];
      }
    }


    return dp[col - 1];
  }




}
