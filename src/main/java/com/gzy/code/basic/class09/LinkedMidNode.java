package com.gzy.code.basic.class09;

/**
 * description: MideNode date: 2022/1/17 3:26 下午
 *
 * @author: guizhenyu
 */
public class LinkedMidNode {


  public static class Node{

    public int value;

    public Node next;

    public Node(int v){
      value = v;
    }
  }

  /**
   * 分一个快节点和慢节点，
   * 快节点的速度是慢节点的两倍
   * 快节点到的时候，慢节点就对应的是中间位置
   * 只不过，当中间数是2个的时候，这边求的是第二个中间数
   *
   *
   * @param head
   * @return
   */
  public static Node midOrUpMidNode(Node head){
    if (head == null || head.next == null || head.next.next == null){
      return head;
    }
    Node slow = head.next;
    Node fast = head.next.next;
    while (fast.next != null && fast.next.next != null){
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }


  public static Node midOrDownMidNode(Node head){
    if (head == null || head.next == null){
      return head;
    }

    Node slow = head.next;
    Node fast = head.next;

    while (fast.next != null && fast.next.next != null){
      slow = slow.next;
      fast = fast.next.next;

    }
    return slow;
  }

  public static Node midOrUpMidPreNode(Node head){
    if (head == null || head.next == null || head.next.next == null){
      return null;
    }

    Node slow = head;
    Node fast = head.next.next;

    while (fast.next != null && fast.next.next != null){
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }

  public static Node midOrDownMidPreNode(Node head){
    if (head == null || head.next == null){
      return null;
    }

    Node slow = head;
    Node fast = head.next;

    while (fast.next != null && fast.next.next != null){

      slow = slow.next;
      fast = fast.next.next;
    }

    return slow;
  }





}
