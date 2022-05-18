package com.gzy.code.basic.class19;


/**
 * description: ConvertToLetterString date: 2022/4/14 11:37 上午
 *
 * @author: guizhenyu
 */
public class ConvertToLetterString {


  public static void main(String[] args) {
    int N = 30;
    int testTIme = 100000;
    System.out.println("测试开始");

    for(int i = 0; i < testTIme; i++){
      for (int j = 0; j < N; j++){
        int len = (int)(Math.random() * N);
        String str = randomString(len);
        int ans0 = natureWisedom(str);
        System.out.println(ans0);
      }
    }
  }

  private static int natureWisedom(String str) {
    if (null == str || str.length() == 0){
      return 0;
    }

    char[] chars = str.toCharArray();

    return generate(chars,0);
  }


  private static int natureWisedomDp(String str) {
    if (null == str || str.length() == 0){
      return 0;
    }

    char[] chars = str.toCharArray();

    return generate(chars,0);
  }

  /**
   * 暴力递归
   *
   * @param chars 数字的char数组
   * @param index 下标
   */
  private static int generate(char[] chars, int index) {

    if(index == chars.length){
      return 1;
    }
    if (chars[index] == '0'){
      return 0;
    }
    //两种情况：
    // 第一种：只取一位              (char) ((int) (Math.random() * 10) + '0')
    int ans = generate(chars, index + 1);
    // 第二种: 连续取两位
    if (index + 1 < chars.length && (chars[index] - '0') * 10 + chars[index + 1] - '0' <= 26){
      ans += generate(chars, index + 2);
    }
    return ans;
  }

  private static String randomString(int len) {
    if (len == 0){
      return "";
    }
    char[] str = new char[len];

    for (int i = 0; i < len; i++){
     str[i] = (char)((int)(Math.random() * 10) + '0');
    }
    return String.valueOf(str);
  }

}
