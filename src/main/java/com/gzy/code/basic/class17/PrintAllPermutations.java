package com.gzy.code.basic.class17;

import java.util.ArrayList;
import java.util.List;

/**
 * description: PrintAllPermutations date: 2022/4/7 5:28 下午
 * 求一个字符串的全排列序列
 * @author: guizhenyu
 */
public class PrintAllPermutations {


  public static void main(String[] args) {

    String str= "acccc";
//
//    permutations(str);
//    System.out.println("========================");
//    permutations1(str);
//    System.out.println("========================");
    permutations2(str);

  }

  private static void permutations2(String str) {

    if (null == str || str.length() == 0){
      System.out.println();
    }
    /**
     * 思路
     * 1. 将字符串转化成 char数组
     * 2.
     *
     *
     */

    char[] chars = str.toCharArray();
    solution3(chars,0);
  }

  private static void permutations1(String str) {
    if (null == str || str.length() == 0){
      System.out.println();
    }
    /**
     * 思路
     * 1. 将字符串转化成 char数组
     * 2.
     *
     *
     */

    char[] chars = str.toCharArray();
    solution2(chars,0);

  }

  private static void solution2(char[] chars, int index) {
    if (index == chars.length){
      System.out.println(String.valueOf(chars));
      return;
    }

    for (int i = index; i < chars.length; i++){
      swap(chars, i, index);
      solution2(chars, index + 1);
      swap(chars, index, i);
    }
  }

  private static void permutations(String str) {
    if (null == str || str.length() == 0){
      System.out.println();
    }

    char[] chars = str.toCharArray();

    String path = "";
    List<String> ans = new ArrayList<>();
    ArrayList<Character> rest = new ArrayList<Character>();
    for (char cha : chars) {
      rest.add(cha);
    }
    solution1(rest, path, ans);

    for (String string : ans){
      System.out.println(string);
    }

  }

  private static void solution1(ArrayList<Character> rest , String path, List<String> ans) {
    if (rest.isEmpty()){
      ans.add(path);
      return;
    }
    int size = rest.size();
    for (int i = 0; i < size; i++){
      char chr = rest.get(i);

      rest.remove(i);

      solution1(rest, path + String.valueOf(chr),  ans);

      rest.add(i, chr);


    }


  }


  private static void solution3(char[] chars, int index) {

    if (index == chars.length){
      System.out.println(String.valueOf(chars));
      return;
    }

    boolean[] isExist = new boolean[256];
    int size = chars.length;
    for (int i = index; i < size; i++){
      if (!isExist[chars[i]]){
        isExist[chars[i]] = true;
        swap(chars, index, i);
        solution3(chars, index + 1);
        swap(chars, index, i);

      }
    }

  }
  public static void swap(char[] chars, int swap1, int swap2){
    char tmp = chars[swap1];
    chars[swap1] = chars[swap2];
    chars[swap2] = tmp;

  }

}
