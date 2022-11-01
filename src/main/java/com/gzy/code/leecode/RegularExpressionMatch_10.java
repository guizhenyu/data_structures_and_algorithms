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


  public boolean isMatch(String s, String p) {
    if(s == null || s.length()== 0 || p == null || p.length() == 0){
      return false;
    }
    return process(s.toCharArray(), p.toCharArray(), 0, 0);
  }

  public boolean process(char[] charsA, char[] charsP, int n, int m){
    if(n == charsA.length && charsP.length == m){
      return true;
    }

    if (charsA[n] == charsP[m]){
      return true;
    }

    if (charsP[m] == '.'){
      return process(charsA, charsP, n + 1, m + 1);
    }else if (charsP[m] == '*'){
      char pre = charsA[n - 1];

      return process(charsA, charsP, n, m + 1) || (pre == charsA[n]?  : );
    }else {
      return false;
    }

  }

}
