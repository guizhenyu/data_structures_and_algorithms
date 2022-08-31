package com.gzy.code.basic.class27.second;

/**
 * description: KMP date: 2022/8/31 10:53
 *
 * @author: guizhenyu
 */
public class Kmp {

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
    if (str == null || match == null || str.length() < 1 || match.length() < 1 || str.length() < match.length()){
      return -1;
    }
    char[] chars1 = str.toCharArray();
    char[] chars2 = match.toCharArray();

    int[] nexts = getNext(chars2);

    int X = 0;
    int Y = 0;
    while (X < chars1.length && Y < chars2.length){
      if (chars1[X] == chars2[Y]){
        X++;
        Y++;
      }else if(nexts[Y] >= 0){
        Y = nexts[Y];
      }else{
        X++;
      }
    }


    return Y == match.length()? X - Y: -1;
  }

  private static int[] getNext(char[] chars) {

    if (chars.length == 1) {
      return new int[] { -1 };
    }

    int[] nexts = new int[chars.length];


    int nl = 0;
    nexts[0] = -1;
    nexts[1] = 0;

    int i = 2;
    while (i < chars.length){
      if (chars[i - 1] == chars[nl]){
        nexts[i++] = ++nl;
      }else if(nexts[nl] > 0){
        nl = nexts[nl];
      }else {
        i++;
      }

    }


    return nexts;
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
