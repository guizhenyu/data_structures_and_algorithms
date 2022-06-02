package com.gzy.code.basic.class47;

/**
 * description: StrangePointer date: 2022/5/30 13:38
 *
 * @author: guizhenyu
 */
public class StrangePointer {

  /**
   * 有台奇怪的打印机有以下两个特殊要求：
   *
   * 打印机每次只能打印由 同一个字符 组成的序列。
   * 每次可以在从起始到结束的任意位置打印新字符，并且会覆盖掉原来已有的字符。
   * 给你一个字符串 s ，你的任务是计算这个打印机打印它需要的最少打印次数。
   *
   * 来源：力扣（LeetCode）
   * 链接：https://leetcode-cn.com/problems/strange-printer
   * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
   */


  /**
   * 这种暴力递归的方法，如果样本量大的话会超时
   *
   * @param str
   * @return
   */
  public int strangePointer(String str){
    if (str == null || str.length() == 0){
      return 0;
    }

    char[] chars = str.toCharArray();

    return process(chars, 0, str.length() - 1);
  }

  /**
   * 暴力递归的基本函数
   * 首先要考虑需要哪些参数
   * todo: 基本思路就是寻找 左边先打印的字符，后面能不能碰到重复的，有重复的就能减少打印次数
   *
   * @param chars 所以字符组成的数组
   * @param l 打印的左边界
   * @param r 打印的右边界
   * @return
   */
  private int process(char[] chars, int l, int r) {
    // base case: 范围上只有一个字符
    if (l == r){
      return 1;
    }

    int ans = r - l + 1; // 这边给的是最差的可能
    // 下面讨论的情况就是：将这个字符数组，分成两段，先左右再右边（因为是暴力递归，其实也包括了先右边再左边所对应的操作可能）
    // l ~ k - 1, K ~ r
    // l如果跟k 对应的字符相等的话，就可以减掉一次
    for (int k = l + 1; k <= r; k++){

      ans = Math.min(ans,
          process(chars, l, k - 1) + process(chars, k, r)
              - (chars[l] == chars[k]? 1 : 0));

    }
    return ans;
  }

  /**
   * 基于暴力递归，有一些重复计算
   * 做记忆化搜索，也就是动态规划
   *
   * @param s
   * @return
   */
  public int strangePointerDp(String s){

    if (s == null || s.length() == 0){
      return 0;
    }

    char[] str = s.toCharArray();
    int n = str.length;
    int[][] dp = new int[n][n];

    return process(str, 0, n - 1, dp);
  }

  private int process(char[] str, int l, int r, int[][] dp) {

    if (dp[l][r] != 0){
      return dp[l][r];
    }

    if (l == r){
      dp[l][r] = 1;
      return 1;
    }
    int ans = r - l + 1; // 这边给的是最差的可能
    // 下面讨论的情况就是：将这个字符数组，分成两段，先左右再右边（因为是暴力递归，其实也包括了先右边再左边所对应的操作可能）
    // l ~ k - 1, K ~ r
    // l如果跟k 对应的字符相等的话，就可以减掉一次
    for (int k = l + 1; k <= r; k++){

      ans = Math.min(ans,
          process(str, l, k - 1, dp) + process(str, k, r, dp)
              - (str[l] == str[k]? 1 : 0));

    }

    dp[l][r] = ans;
    return ans;

  }

  public int strangePointerDpFinal(String s){
    if (s == null || s.length() == 0){
      return 0;
    }

    char[] str = s.toCharArray();
    int n = str.length;
    int[][] dp = new int[n][n];
    dp[n - 1][n - 1] = 1;
    for (int i = 0; i < n - 1; i++){
      dp[i][i] = 1;
      dp[i][i + 1] = str[i] == str[i + 1]? 1 : 2;
    }

    // 从下往上，让后从左往右计算
    for (int l = n - 3; l >= 0; l--){
      for (int r = l + 2; r < n; r++){
        int ans = r - l + 1;
        for (int k = l + 1; k <= r; k++){
          ans = Math.min(ans, dp[l][k - 1] + dp[k][r] - str[l] == str[k]? 1 : 0);
        }
        dp[l][r] = ans;
      }
    }



    return dp[0][n - 1];
  }

}
