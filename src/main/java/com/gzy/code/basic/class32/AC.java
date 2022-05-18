package com.gzy.code.basic.class32;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * description: AC date: 2022/5/17 18:04
 *
 * @author: guizhenyu
 */
public class AC {

  public static class Node{
    Node[] nexts;

    String end;

    boolean used;

    Node fail;

    Node(){
      nexts = new Node[26];
      used = false;
    }
  }

  public static class ACAutomation{
    private Node root;

    public ACAutomation(){
      root = new Node();
    }

    /**
     * 将关键字添加到ac树结构中
     *
     * @param str
     */
    public void insert(String str){

      char[] chars = str.toCharArray();
      Node cur = root;
      for (int i = 0; i < chars.length; i++){
        int index = chars[i] - 'a';
        if (cur.nexts[index] == null){
          cur.nexts[index] = new Node();
        }
        cur = cur.nexts[index];
      }

      cur.end = str;
    }


    /**
     * 主要构建 fail指针
     * 从跟节点开始，进行宽度优先遍历
     *
     */
    public void build(){
      Queue<Node> queue = new LinkedList<>();
      queue.add(root);

      Node cur = null;
      Node cfail = null;

      while (!queue.isEmpty()){
        cur = queue.poll();
        for (int i = 0; i < 26; i++){
          if (null != cur.nexts[i]){
            cur.nexts[i].fail = root;
            cfail = cur.fail;
            while (cfail != null){
              if (cfail.nexts[i] != null){
                cur.nexts[i].fail = cfail.nexts[i];
                break;
              }
              cfail = cfail.fail;
            }
            queue.add(cur.nexts[i]);
          }
        }
      }

    }

    public List<String> containWords(String str){
      List<String> ans = new ArrayList<>();
      char[] chars = str.toCharArray();
      Node cur = root;
      Node follow = null;
      for (int i = 0; i < chars.length; i++){
        int acIndex = chars[i] - 'a';

        // 从 cur点开始寻找，以及往父节点寻找到第一个跟chars[i]相等的点
        while (cur.nexts[acIndex] == null && cur != root){
          cur = cur.fail;
        }

        cur = cur.nexts[acIndex] == null ? root : cur.nexts[acIndex];
        follow = cur;
        while (follow != root){
          if (follow.used){
            break;
          }
          if (null != follow.end){
            ans.add(follow.end);
            follow.used = true;
          }
          follow = follow.fail;
        }


      }


      return ans;
    }
  }

  public static void main(String[] args) {
    ACAutomation ac = new ACAutomation();
    ac.insert("dhe");
    ac.insert("he");
    ac.insert("abcdheks");
    // 设置fail指针
    ac.build();

    List<String> contains = ac.containWords("abcdhekskdjfafhasldkflskdjhwqaeruv");
    for (String word : contains) {
      System.out.println(word);
    }
  }
}
