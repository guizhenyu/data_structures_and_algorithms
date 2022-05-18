package com.gzy.code.basic.class17;

import java.util.ArrayList;

/**
 * description: PrintAllSubsquences date: 2022/4/7 3:14 下午
 *
 * @author: guizhenyu
 */
public class PrintAllSubsquences {

  public static void main(String[] args) {
    String str = "abcd";
    ArrayList<String> ans = generateSubStr1(str);
  }

  private static ArrayList<String> generateSubStr1(String str) {

    char[] chars = str.toCharArray();

    ArrayList<String> ans = new ArrayList<>();
    String path = "";
    generate(chars, 0, ans, path);

    return ans;
  }

  private static void generate(char[] chars, int index, ArrayList<String> ans, String path) {

    if (index == chars.length){
      ans.add(path);
      return ;
    }

    generate(chars, index + 1, ans, path);
    generate(chars, index + 1, ans, path + String.valueOf(chars[index]));

  }


}
