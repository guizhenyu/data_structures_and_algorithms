package com.gzy.code.basic.class20;

/**
 * description: HorseJump date: 2022/7/28 13:50
 *
 * @author: guizhenyu
 */
public class HorseJump {

  /**
   * 棋盘的大小是 10 * 9
   * 象棋中的马，走日
   * 从（0，0）位置总共可以走10步，一共有多少种可能到（X, Y）
   * @param args
   */
  public static void main(String[] args) {
    int x = 7;
    int y = 7;
    int rest = 10;

    System.out.println(jump(x, y, rest));
    System.out.println(dp(x, y, rest));


  }

  private static int dp(int x, int y, int rest) {
    int[][][] dp = new int[10][9][rest + 1];
    dp[x][y][0] = 1;

    for (int r = 1; r <= rest; r++){
      for (int a = 0; a < 10; a++){
        for (int b = 0; b < 9; b++){
          int ways = pick(dp,a + 1, b + 2, r - 1);
          ways += pick(dp,a + 1, b - 2, r - 1);
          ways += pick(dp,a - 1, b - 2, r - 1);
          ways += pick(dp,a - 1, b + 2, r - 1);
          ways += pick(dp,a + 2, b + 1, r - 1);
          ways += pick(dp,a + 2, b - 1, r - 1);
          ways += pick(dp,a - 2, b + 1, r - 1);
          ways += pick(dp,a - 2, b - 1, r - 1);
          dp[a][b][r] = ways;
        }
      }
    }


    return dp[0][0][rest];

  }

  private static int pick(int[][][] dp, int a, int b, int r) {
    if (a < 0 || a > 9 || b < 0 || b > 8){
      return 0;
    }
    return dp[a][b][r];
  }

  private static int  jump(int x, int y, int rest) {

    return process(0, 0, x, y, rest);

  }

  private static int process(int x0, int y0, int x, int y, int rest) {

    if (x0 < 0 || x0 > 9 || y0 < 0 || y0 > 8){
      return 0;
    }
    if (rest == 0){
      return (x0 == x && y0 == y)? 1 : 0;
    }

    int ways = process(x0 + 2, y0 + 1, x, y, rest - 1);
    ways += process(x0 + 1, y0 + 2, x, y, rest - 1);
    ways += process(x0 + 2, y0 - 1, x, y, rest - 1);
    ways += process(x0 + 1, y0 - 2, x, y, rest - 1);
    ways += process(x0 - 2, y0 + 1, x, y, rest - 1);
    ways += process(x0 - 1, y0 + 2, x, y, rest - 1);
    ways += process(x0 - 2, y0 - 1, x, y, rest - 1);
    ways += process(x0 - 1, y0 - 2, x, y, rest - 1);

    return ways;
  }

}
