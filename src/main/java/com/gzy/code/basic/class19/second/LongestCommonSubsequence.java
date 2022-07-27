package com.gzy.code.basic.class19.second;

/**
 * description: LongestCommonSubsequence date: 2022/7/26 15:35
 * 给定两个字符串  text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 *
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-common-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: guizhenyu
 */
public class LongestCommonSubsequence {

  public int longestCommonSubsequence(String text1, String text2) {
    if (text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0){
      return 0;
    }

    char[] str1 = text1.toCharArray();
    char[] str2 = text2.toCharArray();

    return process(str1, str2, str1.length - 1, str2.length - 1);
  }


  public int process(char[] str1, char[] str2, int i, int j){
    // 总思路是从右往左遍历递归
    // 情况一： i和j都到0了
    // 情况二： i == 0， j != 0
    // 情况三： j == 0,  i!=0
    // 情况四： i ！= 0 && j != 0
    if (i == 0 && j==0){
      return str1[i] == str2[j]? 1 : 0;
    }else if(i == 0){
      if (str1[i] == str2[j] ){
        return 1;
      }
      return process(str1, str2, i, j - 1);
    }else if(j == 0){
      if (str1[i] == str2[j]){
        return 1;
      }
      return process(str1, str2, i - 1, j);
    }else {
      int p1 = process(str1, str2, i, j - 1);
      int p2 = process(str1, str2, i - 1, j);
//      int p3 = str1[i] == str2[j]? 1 : 0 + process(str1, str2, i - 1, j - 1);
      //  上面计算p3的逻辑是不够高效会重复计算， 如果str1[i] != str2[j]， 就直接返回0， 而不需要在去计算 process(str1, str2, i - 1, j - 1)
      //  因为process(str1, str2, i - 1, j - 1) 这种情况已经包含在 p1 和 p2中了
      int p3 =  str1[i] == str2[j]? 1 + process(str1, str2, i - 1, j - 1) : 0 ;
      return Math.max(p1, Math.max(p2, p3));
    }
  }

  public int longestCommonSubsequencedp(String s1, String s2) {
    if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
      return 0;
    }
    char[] str1 = s1.toCharArray();
    char[] str2 = s2.toCharArray();
    int N = str1.length;
    int M = str2.length;
    int[][] dp = new int[N][M];
    // 这个二维数组，每一个点依赖他左边，下面和左上方的点
    // 所以先填写最最左边一列和最上面面一排的格子
    dp[0][0] = str1[0] == str2[0]? 1 : 0;
    // 填最左边一列
    for(int i = 1; i < N; i++){
      if (str1[i] == str2[0]){
        dp[i][0] = 1;
      }else{
        dp[i][0] = dp[i - 1][0];
      }
    }
    // 填最上面面一排
    for(int i = 1; i < M; i++){
      if (str1[0] == str2[i]){
        dp[0][i] = 1;
      }else{
        dp[0][i] = dp[0][i - 1];
      }
    }
    // 从下往上，从左往右填
    for (int i = 1; i < N; i++){
      for (int j = 1; j < M; j++){
        int p1 = dp[i][j - 1];
        int p2 = dp[i - 1][j];
        int p3 = str1[i] == str2[j]? 1 + dp[i - 1][j - 1] : 0;
        dp[i][j] = Math.max(p1, Math.max(p2, p3));
      }
    }

    return dp[N - 1][M - 1];
  }

}
