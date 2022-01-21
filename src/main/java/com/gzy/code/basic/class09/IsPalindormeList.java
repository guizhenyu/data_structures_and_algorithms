package com.gzy.code.basic.class09;

import java.util.Stack;

/**
 * description: IsPalindormeList date: 2022/1/18 10:29 上午
 *
 * 是否是回文数
 * @author: guizhenyu
 */
public class IsPalindormeList {

  public static class Node{

    private int value;

    private Node next;

    public Node(int v){
      value = v;
    }
  }

  /**
   * 利用栈的数据结构判断是否是回文数
   *
   * @param head
   * @return
   */
  public static boolean isPalindrome1(Node head){
    if (null == head || head.next == null){
      return false;
    }

    Stack<Node> stack = new Stack<>();
    Node cur = head;
    while (cur != null){
      stack.push(cur);
      cur = cur.next;
    }

    while (head != null){

      if (head.value != stack.pop().value){
        return false;
      }
      head = head.next;
    }
    return true;
  }


  /**
   * 上面用的是栈的容量跟链表长度一样，可以优化成只使用一半的长度
   * 就是寻找链表的中点，而且是找到偏右边的节点
   *
   * @param head
   * @return
   */
  public static boolean isPalindrome2(Node head){
    if (null == head || head.next == null){
      return false;
    }

    Stack<Node> stack = new Stack<>();
    Node right = head.next; // tortoise
    Node rabbit = head.next;
    while(rabbit.next != null && rabbit.next.next != null){

      right = right.next;
      rabbit = rabbit.next.next;
    }

    while (right != null){
      stack.push(right);
      right = right.next;
    }

    while(!stack.isEmpty()){
      if (stack.pop().value != head.value){
        return false;
      }
      head = head.next;
    }
    return true;
  }


  public static boolean isPalindrome3(Node head){
    if (null == head || head.next == null){
      return false;
    }

    Node tortoise = head.next;
    Node rabbit = head.next;
    while (rabbit.next != null && rabbit.next.next != null){
      tortoise = tortoise.next;
      rabbit = rabbit.next.next;
    }

    // 此时得到的tortoise链表头节点，需要反向反转，然后再去跟head比较
    tortoise = recoverNode(tortoise);

    while (head != null && tortoise != null){
      if (head.value != tortoise.value){
        return false;
      }

      head = head.next;
      tortoise = tortoise.next;
    }

    head = recoverNode(head);

    return true;
  }

  /**
   * 反转链表
   *
   * 1 -> 2 -> 3 -> 4 -> 5 -> 6
   *
   * 6 -> 5 -> 4 -> 3 -> 2 -> 1
   *
   * 就是将只能反过来
   *
   * 比如我们在反转 1 到 2时， 要将 1 的next指针置为null,
   * 并且保存 2 的next指针，并且 2 指针指向 1
   *
   * @param head
   */
  public static Node recoverNode(Node head){
    if(head == null || head.next == null){
      return head;
    }

    // 初始化头节点
    Node node2 = head.next;
    head.next = null;
    Node tempNode = null;

    while (node2 != null){
      tempNode = node2.next;
      node2.next = head;
      head = node2;
      node2 = tempNode;
    }

    return head;
  }


  public static void printLinkedList(Node node) {
    System.out.print("Linked List: ");
    while (node != null) {
      System.out.print(node.value + " ");
      node = node.next;
    }
    System.out.println();
  }

  public static void main(String[] args) {

    Node head = null;
    printLinkedList(head);
    System.out.print(isPalindrome1(head) + " | ");
    System.out.print(isPalindrome2(head) + " | ");
    System.out.println(isPalindrome3(head) + " | ");
    printLinkedList(head);
    System.out.println("=========================");

    head = new Node(1);
    printLinkedList(head);
    System.out.print(isPalindrome1(head) + " | ");
    System.out.print(isPalindrome2(head) + " | ");
    System.out.println(isPalindrome3(head) + " | ");
    printLinkedList(head);
    System.out.println("=========================");

    head = new Node(1);
    head.next = new Node(2);
    printLinkedList(head);
    System.out.print(isPalindrome1(head) + " | ");
    System.out.print(isPalindrome2(head) + " | ");
    System.out.println(isPalindrome3(head) + " | ");
    printLinkedList(head);
    System.out.println("=========================");

    head = new Node(1);
    head.next = new Node(1);
    printLinkedList(head);
    System.out.print(isPalindrome1(head) + " | ");
    System.out.print(isPalindrome2(head) + " | ");
    System.out.println(isPalindrome3(head) + " | ");
    printLinkedList(head);
    System.out.println("=========================");

    head = new Node(1);
    head.next = new Node(2);
    head.next.next = new Node(3);
    printLinkedList(head);
    System.out.print(isPalindrome1(head) + " | ");
    System.out.print(isPalindrome2(head) + " | ");
    System.out.println(isPalindrome3(head) + " | ");
    printLinkedList(head);
    System.out.println("=========================");

    head = new Node(1);
    head.next = new Node(2);
    head.next.next = new Node(1);
    printLinkedList(head);
    System.out.print(isPalindrome1(head) + " | ");
    System.out.print(isPalindrome2(head) + " | ");
    System.out.println(isPalindrome3(head) + " | ");
    printLinkedList(head);
    System.out.println("=========================");

    head = new Node(1);
    head.next = new Node(2);
    head.next.next = new Node(3);
    head.next.next.next = new Node(1);
    printLinkedList(head);
    System.out.print(isPalindrome1(head) + " | ");
    System.out.print(isPalindrome2(head) + " | ");
    System.out.println(isPalindrome3(head) + " | ");
    printLinkedList(head);
    System.out.println("=========================");

    head = new Node(1);
    head.next = new Node(2);
    head.next.next = new Node(2);
    head.next.next.next = new Node(1);
    printLinkedList(head);
    System.out.print(isPalindrome1(head) + " | ");
    System.out.print(isPalindrome2(head) + " | ");
    System.out.println(isPalindrome3(head) + " | ");
    printLinkedList(head);
    System.out.println("=========================");

    head = new Node(1);
    head.next = new Node(2);
    head.next.next = new Node(3);
    head.next.next.next = new Node(2);
    head.next.next.next.next = new Node(1);
    printLinkedList(head);
    System.out.print(isPalindrome1(head) + " | ");
    System.out.print(isPalindrome2(head) + " | ");
    System.out.println(isPalindrome3(head) + " | ");
    printLinkedList(head);
    System.out.println("=========================");

  }
}
