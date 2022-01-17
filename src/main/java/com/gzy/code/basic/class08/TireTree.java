package com.gzy.code.basic.class08;

import java.util.HashMap;
import java.util.Map;

/**
 * description: TireTree date: 2022/1/13 9:44 上午
 *
 * @author: guizhenyu
 */
public class TireTree {

  // 前缀树节点类型
  public static class Node1{
    public int pass;

    public int end;

    public Node1[] nexts;

    public Node1(){
      pass = 0;
      end = 0;
      // 这边用26，是默认了26个字母
      nexts = new Node1[26];
    }
  }


  public static class Tire1{
    private Node1 root;

    public Tire1(){
      root = new Node1();
    }

    public void insert(String word){
      if (null == word || word.length() == 0){
        return;
      }
      char[] chars = word.toCharArray();
      Node1 node = root;
      node.pass++;
      for (int i = 0; i < chars.length; i++) {
        int index = chars[i] - 'a';

        if (null == node.nexts[index]){
          node.nexts[index] = new Node1();
        }
        node = node.nexts[index];
        node.pass++;
      }

      node.end++;
    }

    public void delete(String word){
     if (search(word) != 0){
       char[] chars = word.toCharArray();
       Node1 node = root;
       node.pass--;

       for (int i = 0; i < chars.length; i++){
         int index = chars[i] - 'a';
         if (--node.nexts[index].pass == 0){
           //下面的节点都删掉
           node.nexts[index] = null;
           return;
         }
         node = node.nexts[index];
       }

       node.end--;

     }


    }

    public int search(String word){
      if (null == word || word.length() == 0){
        return 0;
      }

      char[] chars = word.toCharArray();
      Node1 node = root;

      for(int i = 0; i < chars.length; i++){
        if (null == node.nexts[chars[i] - 'a']){
          return 0;
        }
        node = node.nexts[chars[i] - 'a'];
      }

      return node.end;

    }


    public int prefixNumber(String pre){
      if (null == pre || pre.length() == 0){
        return 0;
      }

      char[] chars = pre.toCharArray();
      Node1 node = root;
      for (int i = 0; i < chars.length; i++){
        if (null == node.nexts[chars[i] - 'a']){
          return 0;
        }
        node = node.nexts[chars[i] - 'a'];
      }
      return node.pass;
    }

  }


  public static class Node2{
    public int pass;
    public int end;
    public Map<Integer, Node2> nexts;

    public Node2(){
      pass = 0;
      end = 0;
      nexts = new HashMap<>();
    }
  }


  public static class Tire2{
    private Node2 root;

    public Tire2(){
      root = new Node2();
    }



    public void insert(String word){
      if (null == word || word.length() == 0){
        return;
      }

      char[] chars = word.toCharArray();
      Node2 node = root;
      node.pass++;
      for (int i = 0; i < chars.length; i++){
        int index = chars[i];
        if (!node.nexts.containsKey(index)){
          node.nexts.put(index, new Node2());
        }
        node = node.nexts.get(index);
        node.pass++;
      }
      node.end++;
    }

    public int search(String word){
      if (null == word || word.length() == 0){
        return 0;
      }

      char[] chars = word.toCharArray();
      Node2 node = root;
      for (int i = 0; i < chars.length; i++){

        int index = chars[i];
        if (!node.nexts.containsKey(index)){
          return 0;
        }

        node = node.nexts.get(index);
      }

      return node.end;

    }


    public void delete(String word){
      if (search(word) != 0){

        char[] chars = word.toCharArray();
        Node2 node = root;
        node.pass--;
        for (int i = 0; i< chars.length; i++){
          int index = (int) chars[i];

          if (--node.nexts.get(index).pass == 0){
            node.nexts.remove(index);
            return;
          }
          node = node.nexts.get(index);

        }
        node.end--;
      }

    }

    public int prefixNumber(String pre){
      if (null == pre || pre.length() == 0){
        return 0;
      }
      char[] chars = pre.toCharArray();
      Node2 node = root;
      for (int i = 0; i < chars.length; i++){
        int index = (int)chars[i];
        if (!node.nexts.containsKey(index)){
          return 0;
        }
        node = node.nexts.get(index);
      }
      return node.pass;
    }
  }



  public static class Right{

    private Map<String, Integer> box;

    public Right(){
      box = new HashMap<>();
    }

    public void insert(String word){
      if (!box.containsKey(word)){
        box.put(word, 1);
      }else {
        box.put(word, box.get(word) + 1);
      }
    }


    public void delete(String word){
      if (box.containsKey(word)){
        Integer count = box.get(word) - 1;
        if (count == 0){
          box.remove(word);
        }else {
          box.put(word, count);
        }
      }
    }

    public int search(String word){
      if(!box.containsKey(word)){
        return 0;
      }
      return box.get(word);
    }


    public int prefixNumber(String word){
      int count = 0;
      for (String str : box.keySet()) {
        if (str.startsWith(word)){
          count += box.get(str);
        }
      }

      return count;
    }
  }


  // for test
  public static String generateRandomString(int strLen){
    char[] ans = new char[(int) (Math.random() * strLen) + 1];
    for (int i = 0; i < ans.length; i++){
      int value = (int)(Math.random() * 6);
      ans[i] = (char) (97 + value);
    }
    return String.valueOf(ans);
  }

  public static String[] generateRandomStringArray(int arrLen, int strLen){
    String[] ans = new String[(int) (Math.random() * arrLen) + 1];
    for (int i = 0; i < ans.length; i++){
      ans[i] = generateRandomString(strLen);
    }

    return ans;
  }

  public static void main(String[] args) {
    int arrLen = 100;
    int strLen = 20;
    int testTimes = 100000;

    for (int i = 0; i < testTimes; i++){
      String[] strArr = generateRandomStringArray(arrLen, strLen);
      Tire1 tire1 = new Tire1();
      Tire2 tire2 = new Tire2();
      Right right = new Right();

      for (int j = 0; j < strArr.length; j++){
        double decide = Math.random();

        if (decide < 0.25){
          tire1.insert(strArr[j]);
          tire2.insert(strArr[j]);
          right.insert(strArr[j]);
        }else if(decide < 0.5){
          tire1.delete(strArr[j]);
          tire2.delete(strArr[j]);
          right.delete(strArr[j]);
        }else if(decide < 0.75){
          int ans1 = tire1.search(strArr[j]);
          int ans2 = tire2.search(strArr[j]);
          int ans3 = right.search(strArr[j]);
          if (ans1 != ans2 || ans2 != ans3) {
            System.out.println("Oops!");
          }
        }else {
          int ans1 = tire1.prefixNumber(strArr[j]);
          int ans2 = tire2.prefixNumber(strArr[j]);
          int ans3 = right.prefixNumber(strArr[j]);
          if (ans1 != ans2 || ans2 != ans3) {
            System.out.println("Oops!");
          }
        }
      }
    }
  }
}
