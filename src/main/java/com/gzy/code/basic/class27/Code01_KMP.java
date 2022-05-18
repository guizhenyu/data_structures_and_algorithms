package com.gzy.code.basic.class27;

/**
 * description: Code01_KMP date: 2022/5/7 11:21
 *
 * @author: guizhenyu
 */
public class Code01_KMP {

  /**
   * KMP 算法思路
   * 解决问题： 字符串 S1, 字符串S2, 在S1中找S2
   * N = S1.length
   * M = S2.length
   * 常规思路解决，时间复杂度度是  N * M
   * KMP能实现时间复杂度为 N + M ~= N (N >> M)
   *
   * todo: 说明
   *    1. next数组
   *      next[i] = n
   *      表示 S2[0 ~ (n - 1)] == S2[(i - n) ~ (i - 1)]
   *      S2: *********************************i***
   *          -------        ==        -------
   *      也就是说i位置往前的字符串和从0位置开始的相等的长度
   *    2. 在遍历S1时，可以利用这个next数组，完成加速
   *       加速的过程：
   *      S1: ***************i**********************
   *      S2: ********j***********
   *      j = 15;
   *      i = 8;
   *      next[8] = 3
   *      S1[7 ~ 14] == S2[0 ~ 7]
   *      S2[0 ~ 2] == S2[5 ~ 7] == S1[12 ~ 14]
   *      2.1 S1[15] == S2[8]
   *          i++ = 16
   *          j++ = 9
   *      2.2 S1[15] != S2[8]
   *          并且 S1[12 ~ 14] == S2[0 ~ 2]
   *          所以要比较下 S1[15] ？== S2[3]
   *          然后继续循环上面的逻辑
   *
   *
   *
   */

  public static void main(String[] args) {
    int possibilities = 5;
    int strSize = 20;
    int matchSize = 5;
    int testTimes = 5000000;
    System.out.println("test begin");
    for (int i = 0; i < testTimes; i++) {
      String str = getRandomString(possibilities, strSize);
      String match = getRandomString(possibilities, matchSize);
      if (getIndexOf(str, match) != str.indexOf(match)) {
        System.out.println("Oops!");
      }
    }
    System.out.println("test finish");
  }

  private static int getIndexOf(String str, String match) {

    if (null == str || match == null || match.length() <1 || str.length() < match.length()){
      return -1;
    }
    char[] chars1 = str.toCharArray();
    char[] chars2 = match.toCharArray();
    int[] next = generateNext(chars2);

    int y = 0;
    int i = 0;
    while (i < chars1.length && y < chars2.length){

      if (chars1[i] == chars2[y]){
        i++;
        y++;
      }else if(next[y] >= 0){
        y = next[y];
      }else{
        i++;
      }
    }

    return y == match.length()? i - y : -1;
  }

  private static int[] generateNext(char[] chars) {
    if (chars.length == 1) {
      return new int[] { -1 };
    }
    int[] next = new int[chars.length];

    next[0] = -1;
    next[1] = 0;

    int i = 2;
    int cn = 0;
    while (i < chars.length){
      if (chars[i - 1] == chars[cn]){
        next[i++] = cn++;
      }else if (cn > 0){
        cn = next[cn];
      }else {
        i++;
      }

    }
    return next;
  }

  // for test
  public static String getRandomString(int possibilities, int size) {
    char[] ans = new char[(int) (Math.random() * size) + 1];
    for (int i = 0; i < ans.length; i++) {
      ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
    }
    return String.valueOf(ans);
  }


}
