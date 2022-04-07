package com.gzy.code.basic.class12;

import java.util.ArrayList;
import java.util.List;

/**
 * description: isSBT date: 2022/3/30 10:44 上午
 * SBT 搜索二叉树， 就是按照中序遍历，然后数值是升序的
 * @author: guizhenyu
 */
public class IsSBT {


  public static class Node{
    public int value;
    public Node left;
    public Node right;

    public Node(int value){
      this.value = value;
    }

  }

  public static class NodeInfo{
    public boolean isSBT;

    public int min;

    public int max;

    public NodeInfo(boolean isSBT, int min, int max) {
      this.isSBT = isSBT;
      this.min = min;
      this.max = max;
    }
  }

  private static Node generateNode(int maxValue, int maxLevel) {
    return generate(1, maxValue, maxLevel);
  }

  private static Node generate(int level, int maxValue, int maxLevel) {
    if (level > maxLevel || Math.random() > 0.5){
      return null;
    }
    Node head = new Node((int) (maxValue * Math.random()));
    head.left = generate(level + 1, maxValue, maxLevel);
    head.right = generate(level + 1, maxValue, maxLevel);
    return head;
  }

  public static void main(String[] args) {
    int maxValue = 100;
    int maxLevel = 6;
    int testSize = 100000;
    for (int i = 0; i < testSize; i++){
      System.out.println(i);
      Node node = generateNode(maxValue, maxLevel);
      if (isSBT1(node) != isSBT2(node)){
        System.out.println("ops");
        return;
      }
    }
    System.out.println("finish");
  }


  public static boolean isSBT1(Node head){
    if (null == head){
      return true;
    }
    List<Node> nodes = new ArrayList<>();
    // 中序遍历返回数值集合
    in(head, nodes);
    int size = nodes.size();
    for(int i = 1; i < size; i++){
      if (nodes.get(i).value <= nodes.get(i - 1).value){
        return false;
      }
    }
    return true;
  }

  private static void in(Node head, List<Node> nodes) {
    if (head == null){
      return;
    }
    in(head.left, nodes);
    nodes.add(head);
    in(head.right, nodes);

  }

  public static boolean isSBT2(Node head){
    if (null == head){
      return true;
    }

    return process(head).isSBT;
  }

  public static NodeInfo process(Node head){
    if (null == head){
      return null;
    }
    NodeInfo left = process(head.left);
    NodeInfo right = process(head.right);

    int min = head.value;
    int max = head.value;

    Boolean isSBT = true;
    if (null != left){
      min = Math.min(min, left.min);
      max = Math.max(max, left.max);
      if (!left.isSBT){
        isSBT = false;
      }
    }

    if (null != right){
      max = Math.max(max, right.max);
      min = Math.min(min, right.min);
      if (!right.isSBT){
        isSBT = false;
      }
    }

    if (isSBT){
      if (null != left && left.max >= head.value){
        isSBT = false;
      }
      if (null != right && right.min <= head.value){
        isSBT = false;
      }
    }







    return new NodeInfo(isSBT, min, max);
  }
}
