package com.gzy.code.leecode.dynamic;

/**
 * description: RegularExpressionMatch_44 date: 2022/11/12 14:23
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 *
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 * 两个字符串完全匹配才算匹配成功。
 *
 * 说明:
 *
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
 * @author: guizhenyu
 */
public class RegularExpressionMatch_44 {

  public static void main(String[] args) {
    String s = "adceb";
    String p = "*a*b";
    System.out.println(isMatch(s, p));
  }

  public static boolean isMatch(String s, String p) {
    if ((s == null || s.length() == 0) && (p == null || p.length() == 0)){
      return true;
    }
    if (p == null || p.length() == 0){
      return false;
    }
    int n = s == null || s.length() == 0 ? 0 : s.length();
    int m = p.length();
    boolean[][] dp = new boolean[n + 1][m + 1];
    dp[0][0] = true;
    process(s, p, 0, 1, dp);
    return dp[n][m];
  }

  private static void process(String s, String p, int i, int j, boolean[][] dp) {
    if (i >= s.length() + 1 || j >= p.length() + 1){
      return;
    }

    if (p.charAt(j - 1) == '*'){
      dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
//      if (i > 0 && match(s, p, i - 1, j - 1)){
//        dp[i][j] = dp[i][j] || dp[i - 1][j];
//      }
      if (dp[i][j]){
        process(s, p, i, j + 1, dp);
        process(s, p, i + 1, j + 1, dp);
        process(s, p, i + 1, j, dp);
      }
    }else{
      if (match(s, p, i, j)){
        dp[i][j] = true;
        process(s, p, i + 1, j + 1, dp);
      }
    }
  }



  public static boolean match(String s, String p, int i, int j){
//    if (i == 0 && j == 0){
//
//    }
    if (i == 0){
      return false;
    }

    if (p.charAt(j - 1) == '?'){
      return true;
    }

    return s.charAt(i - 1) == p.charAt(j - 1);
  }
}