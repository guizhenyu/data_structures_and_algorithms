package com.gzy.code.basic.class17.sec07;

import java.util.ArrayList;
import java.util.List;

/**
 * description: PrintAllPermutations date: 2022/7/18 11:06
 *
 * @author: guizhenyu
 */
public class PrintAllPermutations {


  public static void main(String[] args) {
    String s = "adcccccc";
    /**
     * 打印他的全排列子序列
     *
     */
    char[] str = s.toCharArray();
    List<Character> charList = new ArrayList<Character>();
    for (char cha : str){
      charList.add(cha);
    }

    function(charList);
    function1(charList);


  }

  private static List<String> function(List<Character> charList) {
    String path = "";
    List<String> result = new ArrayList<>();
    process1(charList, path,  result);
    return result;
  }

  private static void process1(List<Character> charList, String path, List<String> result) {

    if (charList.isEmpty()){
      result.add(path);
      return;
    }
    int size = charList.size();
    for (int i = 0; i < size; i++){
      Character character = charList.get(i);
      charList.remove(i);
      process1(charList, path + character, result);
      charList.add(i, character);
    }

  }

  private static List<String> function1(List<Character> charList) {

    List<String> result = new ArrayList<>();

    process2(charList, result, 0);

    return result;
  }

  private static void process2(List<Character> charList, List<String> result, int index) {

    if (index == charList.size()){
      result.add(String.valueOf(charList));
    }
    int size = charList.size();

    for (int i = index; i < size; i++){
      swap(charList, i, index);
      process2(charList, result, index + 1);
      swap(charList, index, i);
    }
  }

  private static void process3(List<Character> charList, List<String> result, int index) {

    if (index == charList.size()){
      result.add(String.valueOf(charList));
    }
    int size = charList.size();

    // 创建一个容量为256的数组，记录哪些字符串已经跟indexJ交换过
    boolean[] visited = new boolean[256];
    for (int i = index; i < size; i++){
      if (visited[charList.get(i)]){
        continue;
      }
      swap(charList, i, index);
      process2(charList, result, index + 1);
      swap(charList, index, i);
    }
  }

  public static void swap(List<Character> charList, int index1, int index2){
    Character cha = charList.get(index1);

    charList.set(index1, charList.get(index2));
    charList.set(index2, cha);
  }

}
