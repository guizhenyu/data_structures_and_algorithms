package com.gzy.code.basic.class10;


import java.util.Stack;

/**
 * description: UnRecursiveTraversalBT1 date: 2022/2/19 3:17 下午
 * 非递归方式实现
 *
 * 使用栈的方式实现
 * @author: guizhenyu
 */
public class UnRecursiveTraversalBT1 {

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
    pos1(head);
    System.out.println("========");
  }


  /**
   *
   *
   * @param head
   */
  private static void pos1(Node head) {
    if(null == head){
      return;
    }

    Stack<Node> stack = new Stack<>();
    Stack<Node> stack1 = new Stack<>();
    stack.push(head);

    while (!stack.isEmpty()){
      Node pop = stack.pop();
      stack1.push(pop);

      if (null != pop.left){
        stack.push(pop.left);
      }
      if (null != pop.right){
        stack.push(pop.right);
      }

    }

    while (!stack1.isEmpty()){
      System.out.print(stack1.pop().value + " ");
    }

  }

  private static void in(Node head) {
    if(null == head){
      return;
    }

    Stack<Node> stack = new Stack<>();
//    stack.push(head);

    while (!stack.isEmpty() || head != null){
      if (head == null){
        Node pop = stack.pop();
        System.out.print(pop.value + " ");
        head = pop.right;
      }else {
        stack.push(head);
        head = head.left;

      }

    }
  }

  private static void pre(Node head) {
    if(null == head){
      return;
    }

    Stack<Node> stack = new Stack<>();

    stack.push(head);
    while (!stack.isEmpty()){
      Node pop = stack.pop();
      System.out.print(pop.value + " ");
      if (null != pop.right){
        stack.push(pop.right);
      }
      if (null != pop.left){
        stack.push(pop.left);
      }
    }
  }


}
