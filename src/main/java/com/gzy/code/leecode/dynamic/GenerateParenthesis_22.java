package com.gzy.code.leecode.dynamic;

import java.util.ArrayList;
import java.util.List;

/**
 * description: BracketGenerate date: 2022/11/2 14:46
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * @author: guizhenyu
 */
public class GenerateParenthesis_22 {

  /**
   * 1 <= n <= 8
   * @param n
   * @return
   */
  public List<String> generateParenthesis(int n) {
    List<String> ans = new ArrayList<String>();
    StringBuilder sb = new StringBuilder();
    process(n, sb, 0, 0,ans);
    return ans;
  }

  private void process(int n, StringBuilder str, int open, int close, List<String> ans) {

    if (str.length() == 2 * n){
      ans.add(str.toString());
    }

    // 左括号
    if(open < n){
      str.append("(");
      process(n, str, open + 1, close, ans);
      str.deleteCharAt(str.length() - 1);
    }

    // 右括号
    if (close < open){
      str.append(")");
      process(n,  str, open, close + 1, ans);
      str.deleteCharAt(str.length() - 1);
    }
  }


}
