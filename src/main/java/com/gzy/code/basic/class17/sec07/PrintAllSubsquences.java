package com.gzy.code.basic.class17.sec07;

import java.util.ArrayList;
import java.util.List;

/**
 * description: PrintAllSubSquences date: 2022/7/15 10:10
 * 求一个字符串的全排列序列
 * @author: guizhenyu
 */
public class PrintAllSubsquences {

  /**
   * 1. 暴力递归的方法
   *
   *
   *
   */

  public static void main(String[] args) {
    String s = "asbccv";
    List<String> ans1 = subs(s);

    for (String str : ans1){
      System.out.println(str);

    }
    List<String> ans2 = subsWithoutRepeat(s);

    for (String str : ans1){
      System.out.println(str);

    }


  }

  private static List<String> subsWithoutRepeat(String s) {

    // 用一个set集合保存数据

    return null;

  }

  private static List<String> subs(String s) {
    char[] chars = s.toCharArray();
    String path = "";
    List<String> ans = new ArrayList<>();
    process1(chars,0, ans, path);
    return ans;
  }

  private static void process1(char[] chars, int index, List<String> ans, String path) {
    if (index == chars.length){
      ans.add(path);
      return;
    }

    process1(chars, index + 1, ans, path);
    path = path + chars[index];

    process1(chars, index + 1, ans, path);


  }





}
