package com.gzy.code.leecode.dynamic;

import java.util.HashMap;
import java.util.Map;

/**
 * description: LongestValidParentheses date: 2022/11/10 13:54
 *    给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 *       0 <= s.length <= 3 * 104
 *       s[i] 为 '(' 或 ')'
 * @author: guizhenyu
 */
public class LongestValidParentheses {
  public int longestValidParentheses(String s) {
    if(s == null || s.length() <= 1){
      return 0;
    }
    Map<Integer, Integer> record = new HashMap<>();
    return process(s, 1, 0, record);
  }

  /**
   *
   * @param s
   * @param i
   * @param ans
   * @return
   */
  private int process(String s, int i, int ans, Map<Integer, Integer> record) {
    if (i == s.length()){
      return ans;
    }
    // 所有的正确结果肯定都是以 ')' 结尾的
    if (s.charAt(i) == ')'){
      int p = 0;

      if (s.charAt(i - 1) == '('){
        // i - 1的位置是 '('
        // 那么他们可以自然的匹配成一对 括号
        p = 2;
        // 需要判断， 匹配成功后，i - 2 的位置是什么字符，
        // 如果是'(', 由于对称性，直接忽略
        // 如果也是')' ,那么需要加到当前的括号长度中来
        if (i > 2 && record.containsKey(i - 2) && s.charAt(i - 2) == ')'){
          p += record.get( i - 2);
        }

      }else {

        //  )()(((())))(
        // i - 1的位置是 ')'
        // 需要判断 i - 1 位置上是否存在符合要求的括号长度
        // 如果存在，还要考虑， 以 i - 1 位置结束的组成括号串前面的字符(i - record.get(i - 1) - 1)是否存在
        //        如果存在,对应的括号必须是'(', 正好与当前的 ')'匹配
        //                对应的括号如果是')', 无法与当前的')'匹配
        if (record.containsKey(i - 1) && i > record.get(i - 1) && s.charAt(i - record.get(i - 1) - 1) == '('){
          p = record.get(i - 1) + 2;
          // i - record.get(i - 1) - 1 - 1 是 '）'，并且存在有效长度，则加到当时的有效长度中来
          if(record.containsKey(i - record.get(i - 1) - 1 - 1) && s.charAt(i - record.get(i - 1) - 1 - 1) == ')'){
            p += record.get(i - record.get(i - 1) - 1 - 1);
          }
          record.put(i, p);
        }
      }
      if (p > 0){
        record.put(i, p);
        ans = Math.max(ans, p);
      }

    }

    return process(s, i + 1, ans, record);
  }



  public int longestValidParenthesesDp(String s) {
    if(s == null || s.length() <= 1){
      return 0;
    }
    int[] dp = new int[s.length() + 1];
    int ans = 0;
    for (int i = 1; i < s.length(); i++){
      if (s.charAt(i) == ')'){
        if (s.charAt(i - 1) == '('){
          dp[i] = 2;
          if (i > 2 && dp[i - 2] > 0 && s.charAt(i - 2) == ')'){
            dp[i] += dp[i - 2];
          }
        }else{
          if (dp[i - 1] > 0 && i > dp[i - 1] && s.charAt(i - dp[i - 1] - 1) == '('){
            dp[i] = dp[i - 1] + 2;
            if(i - dp[i - 1]- 1 - 1 > 0 && dp[i - dp[i - 1]- 1 - 1] > 0 && s.charAt(i - dp[i - 1] - 1 - 1) == ')'){
              dp[i] += dp[i - dp[i - 1]- 1 - 1];
            }
          }
        }
        ans = Math.max(ans, dp[i]);
      }
    }

    return ans;
  }



}
