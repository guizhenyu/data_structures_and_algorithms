package com.gzy.code.leecode.dynamic;

/**
 * description: HuiWenShu date: 2021/8/23 10:09 上午
 *
 * @author: guizhenyu
 */
public class HuiWenShu {


  /**
   * 思路：
   * 1. 回文数的特点是对称的，对称分两种，
   * 1.1 偶数类型的比如 abba, 中心点是bb
   * 1.2 奇数类型 如 adcba 中信点是c
   * 2. 从字符串下标为1的字符串为中心点开始寻找符合要求的回文数
   *    endIndex是标记最后要遍历的中心点，随着找到的回文字符串长度而变化
   * 3. 遍历查询
   * @param str
   * @return
   */

  public static String getLongestHuiwen(String str){
    int length = str.length();


    int endIndex = length -1;

    String longestHuiWenStr = "";

    for (int i = 1; i < endIndex; i ++) {

      String longestByIndex = getLongestByIndex(str, i);

      if (longestByIndex.length() > longestHuiWenStr.length()){
        longestHuiWenStr = longestByIndex;
        if (longestHuiWenStr.length() > 2){
          endIndex = length - longestHuiWenStr.length() / 2;
        }
      }
    }
    return longestHuiWenStr;
  }

  public static String getLongestByIndex(String str, int index){

    int startHuiWenIndex = index;

    int endHuiWenIndex = index + 1;

    String centerPointerStr = str.substring(startHuiWenIndex, endHuiWenIndex);

    while (startHuiWenIndex >= 0 && endHuiWenIndex <= str.length() - 1){
      if (str.substring(startHuiWenIndex - 1, startHuiWenIndex)
          .equals(centerPointerStr)){
        startHuiWenIndex -= 1;
        continue;
      }

      if (str.substring(endHuiWenIndex, endHuiWenIndex + 1)
          .equals(centerPointerStr)){
        endHuiWenIndex += 1;
        continue;
      }

      if (str.substring(startHuiWenIndex - 1, startHuiWenIndex).
          equals(str.substring(endHuiWenIndex, endHuiWenIndex + 1))){
        startHuiWenIndex --;
        endHuiWenIndex ++;
      }else {
        break;
      }
    }

    return str.substring(startHuiWenIndex, endHuiWenIndex);

  }


  public static void main(String[] args) {
    String str = "sdgfdgfdgdf";
//    String str = "abbbbbbb";
//    String str = "abciiiicba";

    System.out.println(getLongestHuiwen(str));
    System.out.println(getLongestHuiwen(str));

    System.out.println(longestPalindrome0(str));
  }


  /**
   * 动态规划求最长回文数
   * @param str
   * @return
   */
  public static String longestPalindrome(String str){
    int len = str.length();
    if (len <= 2){
      return str;
    }

    boolean[][] dp = new boolean[len][len];

    for (int i = 0; i < len; i++) {
      dp[i][i] = true;
    }

    // 最长字符串的长度
    int maxLen =1;
    // 最长字符串的起始下标
    int begin = 0;

    char[] chars = str.toCharArray();

    // 这边抽象成x和y的二维空间，进行true或者false的填充
    // 对称线上的点都是true
    // d[i][j] = d[i+1][j-1] & chars[i]==chars[j]

    for (int l = 1; l < len - 1; l++){
      for(int x = 0; x < len; x++){
        int y = x + l;
        if(y >= len){
          break;
        }

        if (chars[y] != chars[x]) {
          if (y - x < 3 ){
            dp[x][y] = true;
          }else {
            dp[x][y] = false;
          }

        }else{

          if (y - x < 3 ){
            dp[x][y] = true;
          }else{
            dp[x][y] = dp[x + 1][y - 1];
          }
        }

        if (dp[x][y] && y - x + 1 > maxLen){
          maxLen = y - x + 1;
          begin = x;
        }
      }
    }

    return str.substring(begin, begin + maxLen - 1);
  }

  public static String longestPalindrome0(String s) {
    int len = s.length();
    if (len < 2) {
      return s;
    }

    int maxLen = 1;
    int begin = 0;
    // dp[i][j] 表示 s[i..j] 是否是回文串
    boolean[][] dp = new boolean[len][len];
    // 初始化：所有长度为 1 的子串都是回文串
    for (int i = 0; i < len; i++) {
      dp[i][i] = true;
    }

    char[] charArray = s.toCharArray();
    // 递推开始
    // 先枚举子串长度
    for (int L = 2; L <= len; L++) {
      // 枚举左边界，左边界的上限设置可以宽松一些
      for (int i = 0; i < len; i++) {
        // 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
        int j = L + i - 1;
        // 如果右边界越界，就可以退出当前循环
        if (j >= len) {
          break;
        }

        if (charArray[i] != charArray[j]) {
          dp[i][j] = false;
        } else {
          if (j - i < 3) {
            dp[i][j] = true;
          } else {
            dp[i][j] = dp[i + 1][j - 1];
          }
        }

        // 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
        if (dp[i][j] && j - i + 1 > maxLen) {
          maxLen = j - i + 1;
          begin = i;
        }
      }
    }
    return s.substring(begin, begin + maxLen);
  }


}
