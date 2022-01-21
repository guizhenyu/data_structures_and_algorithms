package com.gzy.code.basic.class09;

import java.util.HashMap;
import java.util.Map;

/**
 * description: CopyNodeWithRandom date: 2022/1/20 6:28 上午
 *  一种特殊的单链表节点类描述如下
 * class Node {
 * int value;
 * Node next;
 * Node rand;
 * Node(int val) { value = val; }
 * }
 * rand指针是单链表节点结构中新增的指针，rand可能指向链表中的任意一个节点，也可能指向null。
 * 给定一个由Node节点类型组成的无环单链表的头节点 head，请实现一个函数完成这个链表的复制，并返回复制的新链表的头节点。
 * 【要求】
 * 时间复杂度O(N)，额外空间复杂度O(1)
 * @author: guizhenyu
 */
public class CopyNodeWithRandom {

  /**
   * todo: 题解思路
   * 方案一：
   *       遍历一次node ,把所有的node复制一个存在一个map中
   *       这种方案不符合题目的空间复杂的O(1)的要求
   *
   * 方案二：
   *       一次遍历，只遍历next节点，不关注random节点
   *           往链表中插入复制节点
   *       二次遍历，遍历的方法是每次遍历2个，将复制节点的random指针指向对应的复制node
   *       三次遍历，将链表拆掉，得出原链表和复制复制节点
   */
  static class Node{
    int value;
    Node next;
    Node random;
    Node(int val){
      value = val;
    }
  }

  /**
   * 方法一
   *
   * @param head
   * @return
   */
  public static Node copyListWithRand1(Node head){
    if (head == null){
      return null;
    }

    Map<Node, Node> nnMap = new HashMap<>();
    Node nextNode = head;
    while (nextNode != null){
      nnMap.put(nextNode, new Node(nextNode.value));
      nextNode = nextNode.next;
    }

    nextNode = head;
    while (nextNode != null){
      nnMap.get(nextNode).next = nnMap.get(nextNode.next);
      nnMap.get(nextNode).random = nnMap.get(nextNode.random);
      nextNode = nextNode.next;
    }

    return nnMap.get(head);
  }

  /**
   * 方案二
   *
   * @param head
   * @return
   */
  public static Node copyListWithRand2(Node head){
    if (null == head){
      return null;
    }

    Node nextNode = head;
    while (nextNode != null){
      Node newNode = new Node(nextNode.value);

      Node next = nextNode.next;
      nextNode.next = newNode;
      newNode.next = next;
      nextNode = next;
    }

    nextNode = head;
    while (nextNode != null){
      if (nextNode.random != null){
        nextNode.next.random = nextNode.random.next;
      }
      nextNode = nextNode.next.next;
    }

    nextNode = head;
    Node res = head.next;
    Node next = null;
    Node curCopy = null;
    while (nextNode != null){
      next = nextNode.next.next;

      curCopy = nextNode.next;
      nextNode.next = next;
      curCopy.next = next !=null? next.next:null;
      nextNode = next;
    }

    return res;
  }

  public static void printRandLinkedList(Node head) {
    Node cur = head;
    System.out.print("order: ");
    while (cur != null) {
      System.out.print(cur.value + " ");
      cur = cur.next;
    }
    System.out.println();
    cur = head;
    System.out.print("rand:  ");
    while (cur != null) {
      System.out.print(cur.random == null ? "- " : cur.random.value + " ");
      cur = cur.next;
    }
    System.out.println();
  }

  public static void main(String[] args) {
    Node head = null;
    Node res1 = null;
    Node res2 = null;
    printRandLinkedList(head);
    res1 = copyListWithRand1(head);
    printRandLinkedList(res1);
    res2 = copyListWithRand2(head);
    printRandLinkedList(res2);
    printRandLinkedList(head);
    System.out.println("=========================");

    head = new Node(1);
    head.next = new Node(2);
    head.next.next = new Node(3);
    head.next.next.next = new Node(4);
    head.next.next.next.next = new Node(5);
    head.next.next.next.next.next = new Node(6);

    head.random = head.next.next.next.next.next; // 1 -> 6
    head.next.random = head.next.next.next.next.next; // 2 -> 6
    head.next.next.random = head.next.next.next.next; // 3 -> 5
    head.next.next.next.random = head.next.next; // 4 -> 3
    head.next.next.next.next.random = null; // 5 -> null
    head.next.next.next.next.next.random = head.next.next.next; // 6 -> 4

    System.out.println("原始链表：");
    printRandLinkedList(head);
    System.out.println("=========================");
    res1 = copyListWithRand1(head);
    System.out.println("方法一的拷贝链表：");
    printRandLinkedList(res1);
    System.out.println("=========================");
    res2 = copyListWithRand2(head);
    System.out.println("方法二的拷贝链表：");
    printRandLinkedList(res2);
    System.out.println("=========================");
    System.out.println("经历方法二拷贝之后的原始链表：");
    printRandLinkedList(head);
    System.out.println("=========================");

  }

}
