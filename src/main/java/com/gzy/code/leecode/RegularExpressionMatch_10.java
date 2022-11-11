package com.gzy.code.leecode;

/**
 * description: RegularExpressionMatch date: 2022/11/1 18:30
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *
 *
 * @author: guizhenyu
 */
public class RegularExpressionMatch_10 {

  @Deprecated
  public boolean isMatch1(String s, String p) {
    /**
     *   todo: * 代表能匹配他前面一个字符的任意个
     *         首先通过暴力递归的方式去实现这个功能，然后将暴力递归改成动态规划
     *
     */
    if (s == null && s.length() == 0 && p == null && p.length() ==0){
      // 两个都是空字符串
      return true;
    }

    if ((s == null && s.length() == 0) || (p == null && p.length() ==0)){
      // 一个为空，另一个不为空
      return false;
    }

    // 暴力递归
    return process(s, p, 0, 0);

  }


  private boolean process(String s, String p, int i, int j) {

    if (i == s.length() && j == s.length()){
      // 遍历到最后一个都没有出现false，证明是前面都是true;
      return true;
    }

    if(i == s.length() || j == p.length()){
      return false;
    }

    /**
     * todo:根据j位置上的字符串是否是 * 分析
     *      1. 如果是 非 * ， 直接判断是否和s的i位置是否相等
     *      2. 如果是 *，代表可以 0 ~ n 个 （j - 1）位置上的字符
     *        2.1
     */
    if (p.charAt(j) == '*'){
      // 0 个
      // 0 个
      boolean p0 =  process(s, p, i, j + 1);

      boolean flag = s.charAt(i - 1) == s.charAt(i) || p.charAt(j - 1) == '.';
      int i1 = i;
      while (flag ) {
        p0 = p0 || process(s, p, i1 + 1, j + 1);
        if (i1 + 1 >= s.length()) {
          flag = false;
        } else {
          flag = s.charAt(i1) == s.charAt(++i1);
        }
      }
//      if (s.charAt(i - 1) == s.charAt(i)){
//        // 匹配 1 个 j - 1
//        p0 = p0 || process(s, p, i + 1, j + 1) ;
//      }
//      if ((s.charAt(i - 1) == s.charAt(i)) && (s.charAt(i) == s.charAt(i + 1)) ){
//        //  匹配 2 个 j - 1
//        p0 = p0 || process(s, p, i + 2, j + 1) ;
//      }
//      // ...... 这种方式无法确定边界

      return  p0;
    }else{
      if (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i)){
        return process(s, p, i + 1, j + 1);
      }
      return false;
    }
  }


  /**
   *   todo: '*' 符号, 是深度捆绑他前一个字符的，代表0个或者n个前面的字符串，
   *         如果代表0个的话，eg:
   *                           asdf* -->  asd
   *                           .*    -->  空字符串
   *                           .*d   --> d
   *         如果代表1个的话，eg:
   *                           asdf* -->  asdff
   *                           .*d   -->  ..d  --> (任意单个字符)(任意单个字符)d
   * @param s
   * @param p
   * @return
   */
  public boolean isMatch(String s, String p) {

    int n = s.length();
    int m = p.length();

    // dp[i][j] 表示 s 的前 i 个字符与 p 中的前 j 个字符是否能够匹配
    boolean[][] dp = new boolean[n + 1][m + 1];
    // *********   下面，我们要想办法把这个二维表填满

    // 代表 空 匹配 空，所以是true
    dp[0][0] = true;

    /**
     * TODO: 思路
     *      由于，p字符串中有正则匹配字符串，可能是整体的代表空，如 .* --> 空，这种情况 s也取空的话，得出来的结果：dp[0][j] = true;
     *      所以 s 从 空字符串 开始比较，p 从 第一个字符串开始比较
     */
    for(int i = 0; i <= n; i++){
      for(int j = 1; j <= m; j++){
        if (p.charAt(j - 1) == '*'){

          // a* -> 空
          dp[i][j] = dp[i][j - 2];
          // 比较 * 前面的字符是否相等，如果相等，就代表可以代表匹配一个前面的字符串，但是，还可以继续匹配2个字符串，
          // 如果一个一个比较类比下去，无法知道边界（i - 2 ,i-3..... 这些位置上的字符是否和j-1上的字符相等）
          // 但是，由于我们是从i = 0 到 i = i - 1,已经填了，所以，我们这边只要填一个，dp[i - 1][j] 因为他代表了 [i - 2][j] .....[i - n ][j]所有的并集结果
          if (matchs(s, p, i, j - 1)){

            dp[i][j] = dp[i - 1][j] || dp[i][j];
          }
        }else {
          if (matchs(s, p, i, j)){
            dp[i][j] = dp[i - 1][j - 1];
          }
        }
      }
    }

    return dp[n][m];
  }

  private boolean matchs(String s, String p, int i, int j) {

    if (i == 0){
      return false;
    }

    if (p.charAt(j - 1) == '.'){
      return true;
    }

    return s.charAt(i - 1) == p.charAt(j - 1);


  }


}
