package com.gzy.code.basic.class10;

/**
 * description: RecursiveTraversalBT date: 2022/1/21 5:43 下午
 *
 * @author: guizhenyu
 */
public class RecursiveTraversalBT {

  public static class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int v) {
      value = v;
    }
  }



  public static void main(String[] args) {
    Node head = new Node(1);
    head.left = new Node(2);
    head.right = new Node(3);
    head.left.left = new Node(4);
    head.left.right = new Node(5);
    head.right.left = new Node(6);
    head.right.right = new Node(7);

    pre(head);
    System.out.println("========");
    in(head);
    System.out.println("========");
    pos(head);
    System.out.println("========");

  }

  private static void pos(Node head) {
    if (null == head){
      return;
    }
    pos(head.left);
    pos(head.right);
    System.out.println(head.value);
  }

  private static void in(Node head) {

    if (null == head){
      return;
    }
    in(head.left);
    System.out.println(head.value);
    in(head.right);

  }

  private static void pre(Node head) {
    if (null == head){
      return;
    }
    System.out.println(head.value);
    pre(head.left);
    pre(head.right);


  }

}
