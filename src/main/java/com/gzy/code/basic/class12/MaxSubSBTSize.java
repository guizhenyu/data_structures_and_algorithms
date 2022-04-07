package com.gzy.code.basic.class12;


import java.util.ArrayList;
import java.util.List;

/**
 * description: MaxSubSBT date: 2022/3/30 1:58 下午
 * 求最大子 搜索二叉树
 * @author: guizhenyu
 */
public class MaxSubSBTSize {

  public static class Node{
    public int value;
    public Node left;
    public Node right;

    public Node(int value){
      this.value = value;
    }

  }

  public static class NodeInfo{

    public int allSize;

    public int maxSubSBTSize;

    public int min;

    public int max;

    public NodeInfo(int allSize, int maxSubSBTSize ,int min, int max) {
      this.allSize = allSize;
      this.maxSubSBTSize = maxSubSBTSize;
      this.min = min;
      this.max = max;
    }
  }

  public static void main(String[] args) {

    int maxLevel = 4;
    int maxValue = 100;
    int testTime = 100000;
    for (int i = 0; i < testTime; i++){
      Node head = generateNode(maxLevel, maxValue);
      if (maxSubSBTSize1(head) != maxSubSBTSize2(head)){
        System.out.println("oops");
        return;
      }
    }

    System.out.println("finish");
  }


  public static int getSBTSize(Node head){
    if (null == head){
      return 0;
    }

    List<Node> nodes = new ArrayList<>();
    in(head, nodes);
    int size = nodes.size();
    for (int i = 1; i < size; i++){
      if (nodes.get(i).value <= nodes.get(i - 1).value){
        return 0;
      }
    }
    return size;
  }
  private static int maxSubSBTSize1(Node head) {
    if (null == head){
      return 0;
    }

    int h = getSBTSize(head);
    if (h != 0){
      return h;
    }

    return Math.max(maxSubSBTSize1(head.left), maxSubSBTSize1(head.right));



  }

  private static void in(Node head, List<Node> nodes) {
    if (head == null){
      return;
    }

    in(head.left, nodes);
    nodes.add(head);
    in(head.right, nodes);

  }

  private static int maxSubSBTSize2(Node head) {

    if (null == head){
      return 0;
    }

    return process(head).maxSubSBTSize;

  }

  public static NodeInfo process(Node node){
    if (null == node){
      return null;
    }
    NodeInfo left = process(node.left);
    NodeInfo right = process(node.right);

    int allSize = 1;
    int max = node.value;
    int min = node.value;
    int leftSubSBTSize = -1;
    int rightSubSBTSize = -1;
    int maxSubSBTSize = -1;
    boolean leftNodeIsSBT = true;
    boolean rightNodeIsSBT = true;

    if (null != left){
      max = Math.max(max, left.max);
      min = Math.min(min, left.min);
      leftSubSBTSize = left.maxSubSBTSize;
      allSize += left.allSize;

      if (leftSubSBTSize != left.allSize){
        leftNodeIsSBT = false;
      }
    }
    if (null != right){
      max = Math.max(max, right.max);
      min = Math.min(min, right.min);
      rightSubSBTSize = right.maxSubSBTSize;
      allSize += right.allSize;

      if (rightSubSBTSize != right.allSize){
        rightNodeIsSBT = false;
      }

    }



    if (leftNodeIsSBT && rightNodeIsSBT){

      boolean leftLessValue = left == null ? true : left.max < node.value;
      boolean rightGreaterValue = right == null ? true : right.min > node.value;
//      if (leftLessValue && rightGreateValue){
//
//        maxSubSBTSize = leftSubSBTSize + rightSubSBTSize + 1;
//
//
//      }
      if (leftLessValue && rightGreaterValue){

        maxSubSBTSize = (left == null? 0 : left.allSize) + (right == null? 0 : right.allSize) + 1;
      }
    }



    return new NodeInfo(allSize, Math.max(leftSubSBTSize,Math.max(rightSubSBTSize, maxSubSBTSize)), min, max);

  }
  private static Node generateNode(int maxLevel, int maxValue) {
    return generate(1, maxLevel, maxValue);
  }

  private static Node generate(int level, int maxLevel, int maxValue) {
    if (level > maxLevel || Math.random() < 0.5){
      return null;
    }

    Node head = new Node((int) (Math.random() * maxValue));

    head.left = generate(level + 1, maxLevel, maxValue);
    head.right = generate(level + 1, maxLevel, maxValue);
    return head;
  }

}
