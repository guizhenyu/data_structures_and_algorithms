package com.gzy.code.basic.class10;

import java.util.Stack;

/**
 * description: UnRecursiveTraversalBT date: 2022/1/21 6:36 下午
 *
 * @author: guizhenyu
 */
public class UnRecursiveTraversalBT {

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
    pos2(head);
    System.out.println("========");
  }

  /**
   *
   * @param head
   */
  private static void pos2(Node head) {

  }


  /**
   * 后续遍历
   * 两个栈
   * 栈1： 先实现，头左右，
   * 栈2： 把栈一依次弹出，放入栈二
   * 依次弹出栈二，实现 左右头
   *
   * @param head
   */
  private static void pos1(Node head) {

    if (null == head){
      return;
    }

    Stack<Node> stack1= new Stack<>();
    Stack<Node> stack2 = new Stack<>();
    stack1.push(head);

    while (!stack1.isEmpty()){
      Node pop = stack1.pop();
      stack2.push(pop);
      if (pop.left != null){
        stack1.push(pop.left);
      }
      if (pop.right != null){
        stack1.push(pop.right);
      }
    }

    while (!stack2.isEmpty()){
      System.out.print(stack2.pop().value + " ");
    }

    System.out.println();

  }

  /**
   * 中序遍历
   *
   * @param head
   */
  private static void in(Node head) {
    System.out.println("in order started!");
    if (null == head){
      return;
    }
    Stack<Node> stack = new Stack<>();
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

    System.out.println();
  }


  /**
   * 前序遍历 头左右
   * @param head
   */
  private static void pre(Node head) {
    System.out.println("pre-order started!");
    if (null == head){
      return;
    }

    Stack<Node> stack = new Stack<>();

    stack.push(head);

    while(!stack.isEmpty()){
      Node pop = stack.pop();
      System.out.print(pop.value + " ");

      if (pop.right != null){
        stack.push(pop.right);
      }
      if (pop.left != null){
        stack.push(pop.left);
      }
    }
    System.out.println();
  }

}
