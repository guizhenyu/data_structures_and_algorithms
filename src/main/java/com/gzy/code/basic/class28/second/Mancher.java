package com.gzy.code.basic.class28.second;

/**
 * description: Mancher date: 2022/8/31 13:33
 *
 * @author: guizhenyu
 */
public class Mancher {

  /**
   *
   * 假设字符串str长度为N，想返回最长回文子串的长度
   *
   * 时间复杂度O(N)
   */
  public static void main(String[] args) {
    int testTime = 100000;

    int maxLen = 30;

    int possibilities = 5;

    for (int i = 0; i < testTime; i++){

      String str = generateRandomStr(possibilities, maxLen);

      int ans = natureWisdom(str);

      int ans1 = manacher(str);
      if (ans != ans1){
        System.out.println("oops");
        System.out.println("str " + str);
        System.out.println("ans " + ans);
        System.out.println("ans1 " + ans1);
        System.out.println(right(str));
        break;
      }
    }
  }

  private static int manacher(String str) {

    if (str == null || str.length() == 0){
      return 0;
    }

    char[] chars = populateChars(str);
    int[] pArr = new int[chars.length];
    int C = -1;
    // 最右的扩成功位置的，下一位
    int R = -1;
    int ans = Integer.MIN_VALUE;
    for (int i = 0; i < chars.length; i++){

      pArr[i] = R > i? Math.min(R - i, pArr[2 * C - i]) : 1;

      while (i + pArr[i] < chars.length && i - pArr[i] >= 0){
        int L = 2 * C - R;
        int mirror_i = 2 * C - i;
        if (mirror_i >= 0 && mirror_i - pArr[mirror_i] > L){
          break;
        }else {
          if (chars[i - pArr[i]] == chars[i + pArr[i]]){
            pArr[i]++;
          }else {
            break;
          }
        }
      }

      if (i + pArr[i] > R){
        R = i + pArr[i];
        C = i;
      }

      ans = Math.max(ans, pArr[i]);
    }
    // 这边减1，是因为边界到不了
    return ans - 1;

  }

  private static int natureWisdom(String str) {
    if (null == str || str.length() == 0){
      return 0;
    }
    int ans = Integer.MIN_VALUE;

    char[] chars = populateChars(str);

    for (int i = 0; i < chars.length; i++){

      int l = i - 1;
      int r = i + 1;

      while (l >= 0 && r < chars.length && chars[l] == chars[r]){
        l--;
        r++;
      }
      ans = Math.max(ans, r - l - 1);
    }
    return ans / 2;
  }

  // for test
  public static int right(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }
//    char[] str = manacherString(s);
    char[] str = populateChars(s);
    int max = 0;
    for (int i = 0; i < str.length; i++) {
      int L = i - 1;
      int R = i + 1;
      while (L >= 0 && R < str.length && str[L] == str[R]) {
        L--;
        R++;
      }
      max = Math.max(max, R - L - 1);
    }
    return max / 2;
  }


  public static char[] populateChars(String str){

    int len = str.length();

    char[] ans = new char[2 * len];

    char[] chars = str.toCharArray();
    int index = 0;
    for (int i = 0; i < 2 * len; i++){

      ans[i] = (i & 1) == 0? '#' : chars[index++];

    }
    return ans;
  }

  private static String generateRandomStr(int possibilities, int maxLen) {
    char[] chars = new char[(int) (Math.random() * maxLen) + 2];
    for (int i = 0; i < chars.length; i++){
      chars[i] = (char)('a' + (int)(Math.random() * possibilities));
    }

    return String.valueOf(chars);
  }


}
