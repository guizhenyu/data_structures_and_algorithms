package com.gzy.code.basic.class10;


/**
 * description: RecursiveTraversalBT11 date: 2022/2/19 2:51 下午
 *
 * @author: guizhenyu
 */
public class RecursiveTraversalBT1 {

  public static class Node{
    public int value;
    public Node left;
    public Node right;

    public Node(int value){
      this.value = value;
    }


  }


  public static void pre(Node head){
    if (null == head){
      return;
    }
    System.out.println(head.value);
    pre(head.left);
    pre(head.right);

  }

  public static void pos(Node head){
    if (null == head){
      return;
    }

    pos(head.left);
    pos(head.right);
    System.out.println(head.value);

  }

  public static void in(Node head){
    if (null == head){
      return;
    }

    in(head.left);
    System.out.println(head.value);
    in(head.right);
  }

  public static void main(String[] args) {
    RecursiveTraversalBT1.Node head = new RecursiveTraversalBT1.Node(1);
    head.left = new RecursiveTraversalBT1.Node(2);
    head.right = new RecursiveTraversalBT1.Node(3);
    head.left.left = new RecursiveTraversalBT1.Node(4);
    head.left.right = new RecursiveTraversalBT1.Node(5);
    head.right.left = new RecursiveTraversalBT1.Node(6);
    head.right.right = new RecursiveTraversalBT1.Node(7);

    pre(head);
    System.out.println("========");
    in(head);
    System.out.println("========");
    pos(head);
    System.out.println("========");

  }
}
