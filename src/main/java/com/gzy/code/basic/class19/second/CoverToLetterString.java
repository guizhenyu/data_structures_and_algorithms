package com.gzy.code.basic.class19.second;

import com.sun.deploy.util.StringUtils;

/**
 * description: CoverToLetterString date: 2022/7/21 16:20
 *
 * @author: guizhenyu
 */
public class CoverToLetterString {

  /**
   * 审题：
   * 1. 0-9组成一串数字，
   * 2. 将改数字转化成也就是 1 ~ 26 ，代表字符从 a  到 z
   * 3. 求总共有几种可能
   */

  public static void main(String[] args) {

    int testTime = 100000;
    int size = 30;

    for (int i = 0; i <= testTime; i++){
      int len = (int)(Math.random() * 30);
      String str = generateRandomStr(len);
      int ways = natureWidsom(str);
      int ways1 = dp(str);
      int ways2 = dp2(str);

      if (ways != ways1 || ways2 != ways1){
        System.out.println("Oops!");
      }
    }



  }


  // 从左往右计算
  private static int dp2(String str) {
    if (str == null || str.length() == 0 || str.startsWith("0")){
      return 0;
    }

    char[] chars = str.toCharArray();
    int N = chars.length;
    int[] dp = new int[N + 1];

    dp[0] = 1;
    for (int i = 1; i < N; i++){
      if (chars[i] == '0'){
        if (chars[i - 1] == '0' || chars[i - 1] - '0' > 2){
          // 前一个数为0，或者大于2，就不能跟当前的组成一个1 ~ 26之间的字母
          return 0;
        }else {
          dp[i] = i - 2 >= 0 ? dp[i - 2] : 1;
        }


      }else {
        dp[i] = dp[i - 1];
        if (chars[i - 1] != '0' && (chars[i - 1] - '0') * 10 + chars[i] - '0' <= 26){
          dp[i] += i - 2 >= 0? dp[i - 2] : 1;
        }



      }
    }




    return dp[N - 1];


  }

  private static int dp(String str) {
    if (str == null || str.length() == 0 || str.startsWith("0")){
      return 0;
    }

    char[] chars = str.toCharArray();
    int N = chars.length;
    int[] dp = new int[N + 1];
//    if (chars[N - 1] == '0'){
//      dp[N - 1] = 1;
//    }

    dp[N] = 1; // todo: 这边两个问题： 1. N 位置按理说不存在，2 为什还要设置值为1？
               //       只要是为了在在 N-2， N-1两个位置能组成一个数的是的情况，也就是最后两位数字能够组成字母的情况，也就是对应为1
               //       相当于补位
    // 从右往左计算
    for (int i = N - 1; i >= 0; i--){
      if (chars[i] == '0'){
        continue;
      }
      dp[i] = dp[i + 1];
      if (i + 1 < N && (chars[i] - '0')* 10 + chars[i + 1] - '0' <= 26 ){
        dp[i] += dp[i + 2];
      }
    }




    return dp[0];
  }
  

  private static int natureWidsom(String str) {
    if (str == null || str.length() == 0 || str.startsWith("0")){
      return 0;
    }

    char[] chars = str.toCharArray();

    return process(chars, 0);
  }

  private static int process(char[] chars, int index) {


    if (index == chars.length){
      return 1;
    }


    if (chars[index] == '0'){
      return 0;
    }
    // 没到最后
    // 方案一； 直接单转
    int ways = process(chars, index + 1);

    if (index + 1 < chars.length && (chars[index] - '0')* 10 + chars[index + 1] - '0' <= 26){
      ways += process(chars, index + 2);
    }

    return ways;
    
  }

  private static String generateRandomStr(int len) {
    char[] chars = new char[len];

    for (int i = 0; i < len; i++){
      chars[i] = (char)(Math.random() * 10 + '0');
    }

    return chars.toString();
  }

}
