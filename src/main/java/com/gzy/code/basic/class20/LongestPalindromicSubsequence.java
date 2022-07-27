package com.gzy.code.basic.class20;

/**
 * description: LongestPalindromeSubseq date: 2022/7/27 10:31
 *
 * hard: mid
 *
 * 516
 *
 * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
 *
 * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-palindromic-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: guizhenyu
 */
class LongestPalindromeSubsequence {

  /**
   * 暴力递归：
   * 思路：
   *  罗列所有子序列，然后判断是否是回文，如果是回文就比较长度，留下长的
   * // todo: 这个思路比较复杂，改造成dp比较难
   * @param s
   * @return
   */

  public int longestPalindromicSubseq(String s) {

    if (s == null || s.length() == 0){
      return 0;
    }

    char[] chars = s.toCharArray();

    return violenceRecursive(chars, 0,"");

  }

  /**
   * 暴力递归2 ：
   * 思路；
   *     从字符串的左右点开始往中间靠
   *
   * @param s
   * @return
   */
  public int longestPalindromicSubseq1(String s) {

    if (s == null || s.length() == 0){
      return 0;
    }

    char[] chars = s.toCharArray();
    return violenceRecursive1(chars, 0, chars.length - 1);
  }


  public int longestPalindromicSubsequenceByDp(String s){
    if (s == null || s.length() == 0){
      return 0;
    }
    //转数组
    char[] chars = s.toCharArray();
    // 数组长度
    int N = chars.length;
    // 计算dp
    int[][] dp = new int[N][N];
    for (int i = 0; i < N; i++){
      dp[i][i] = 1;
    }
    /**
     * TODO： 根据暴力递归的公式分析每个点的依赖关系
     *       刚开始dp的第一维度，如果代表左边界， 第二维度代表右边界
     *       发现 dp上的每个点 依赖  (l + 1, r) (l, r - 1) (l + 1, r - 1)
     *       也就是 依赖左，下，左下的点
     *       就推导出， 要先填好对角线上的值 然后根据这两列的值去推导出其他两个地方的值
     *       也就是先先算 dp[0][r] 和dp[l][0]
     *
     */
    for (int level = 1; level < N; level++){
      // 这边斜线的平移， 临界点只要r < N 就行了，因为 l << r
      for (int l = 0, r = level ; r < N; l++, r++){
        int p1 = dp[l][r - 1];
        int p2 = dp[l + 1][r];
        int p3 = chars[l] == chars[r]? 2 +  dp[l + 1][r - 1] : 0;
        dp[l][r] = Math.max(p1, Math.max(p2, p3));
      }
    }
    return dp[0][N - 1];

  }

  private int violenceRecursive1(char[] chars, int l, int r) {
    if (l == r){
      return 1;
    }
    // TODO： 这边这个条件一定要加上，如果不加，会造成栈溢出
    //        因为会直接跳过l == r， 从而出现 l > r;
    //       但是正常做很容易漏掉这判断条件。建议上上面直接做 l > r 的拦截判断
    if (l == r - 1){
      return chars[l] == chars[r]? 2 : 1;
    }

    int p1 = violenceRecursive1(chars, l, r - 1);
    int p2 = violenceRecursive1(chars, l + 1, r);
    //todo: *************
    //      如果 chars[l] != chars[r] ,直接返回0， 没必要再计算，因为 violenceRecursive1(chars, l + 1, r - 1)
    //       已经包含在 p1, p2中了，而且肯定小于或者等于 p1, p2
    int p3 = chars[l] == chars[r]? 2 +  violenceRecursive1(chars, l + 1, r - 1) : 0;

    return Math.max(p1, Math.max(p2, p3));

  }


  private int violenceRecursive(char[] chars, int index, String str) {

    if (index == chars.length){
      return palindrome(str);
    }

    int p1 = violenceRecursive(chars, index + 1, str + chars[index]);
    int p2 = violenceRecursive(chars, index + 1, str);
    return Math.max(p1, p2);
  }

  private int palindrome(String str) {

    if (str == null || str.length() == 0){
      return 0;
    }
    char[] chars = str.toCharArray();
    int N = str.length();
    for (int i = 0; i < N; i++){
      if (chars[i] != chars[N - 1 - i]){
        return 0;
      }
    }
    return chars.length;
  }

}
