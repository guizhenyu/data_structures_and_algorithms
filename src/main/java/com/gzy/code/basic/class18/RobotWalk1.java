package com.gzy.code.basic.class18;

/**
 * description: RobotWalk1 date: 2022/4/26 13:42
 *
 * @author: guizhenyu
 */
public class RobotWalk1 {

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


    int positions = 5;

    int destination = 4;

    int start = 2;

    int k = 6;

    int ways1 = natureWisdom(positions, destination, start, k);
    System.out.println("ways1 " + ways1);

    int ways2 = ways2(positions, destination, start, k);
    System.out.println("ways2 " + ways2);
  }

  private static int ways2(int positions, int destination, int start, int k) {

    int[][] dp = new int[positions + 1][k + 1];

    dp[destination][0] = 1;

    for(int rest = 1; rest <= k; rest++){
      dp[1][rest] = dp[2][rest - 1];
      for (int index = 2; index < positions; index++){
        dp[index][rest] = dp[index + 1][rest - 1] + dp[index - 1][rest - 1];
      }
      dp[positions][rest] = dp[positions - 1][rest - 1];
    }


    return dp[start][k];
  }

  private static int natureWisdom(int positions, int destination, int index, int k) {

    if (k == 0){
      return index == destination? 1 : 0;
    }
    int ans = 0;
    // 在最左边
    if (index == 1){
      ans += natureWisdom(positions, destination, index + 1, k - 1);
    }
    // 在最后边
    else if (index == positions){
      ans += natureWisdom(positions, destination, index - 1, k - 1);
    }
    else {
      ans += natureWisdom(positions, destination, index + 1, k - 1)
          + natureWisdom(positions, destination, index - 1, k - 1);
    }
    return ans;
  }



}
