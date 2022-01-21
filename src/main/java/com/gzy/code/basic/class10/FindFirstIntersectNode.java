package com.gzy.code.basic.class10;

/**
 * description: FindFirstIntersectNode date: 2022/1/21 2:41 下午
 * 给定两个可能有环也可能无环的单链表，头节点head1和head2。
 * 请实现一个函数，如果两个链表相交，请返回相交的 第一个节点。如果不相交，返回null
 * 【要求】
 * 如果两个链表长度之和为N，时间复杂度请达到O(N)，额外空间复杂度 请达到O(1)。
 * @author: guizhenyu
 */
public class FindFirstIntersectNode {

  public static class Node{
    public int value;
    public Node next;
    public Node(int val){
      this.value = val;
    }
  }

  public static Node getIntersectNode(Node head1, Node head2){
    if (head1 == null || head1 == null){
      return null;
    }

//     1. 想判断两个链表是否有循环节点
//     2. 根据是否有循环节点的情况可以排除哪些情况是确定没有相交节点的
//      2.1 两个都没有循环节点，是有相交的可能的
//      2.2 两个都是循环节点，也是有相交可能的
//      2.3 一个链表有循环节点，另一个链表没有循环节点，是没有相交的可能性的（需要自己去脑补下，确实没有）
    Node loopNode1 = getLoopNode(head1);
    Node loopNode2 = getLoopNode(head2);

    if (null == loopNode1 && null == loopNode2){
      return noLoop(head1, head2);
    }

    if (null != loopNode1 && null != loopNode2){
      return bothLoop(head1, loopNode1, head2, loopNode2);
    }


    return null;
  }

  /**
   * 两个都有循环节点的链表，求相交点
   *
   * @param head1
   * @param loopNode1
   * @param head2
   * @param loopNode2
   * @return
   */
  private static Node bothLoop(Node head1, Node loopNode1, Node head2, Node loopNode2) {

    // 分两种情况
    // loopNode1 == loopNode2
    // 相等的话，就看是不是在循环节点前相交
    //
    // loopNode1 != loopNode2
    // 那就看两个循环链表是否一样
    //
    Node cur1 = null;
    Node cur2 = null;
    if (loopNode1 == loopNode2){
      int n = 0;
      cur1 = head1;
      while (cur1 != loopNode1){
        n++;
        cur1 = cur1.next;
      }
      cur2 = head2;
      while (cur2 != loopNode2){
        n--;
        cur2 = cur2.next;
      }
      // 此时cur1是长节点
      cur1 = n > 0?head1:head2;
      cur2 = cur1 == head1?head2:head1;
      n = Math.abs(n);
      while (n > 0){
        cur1 = cur1.next;
        n--;
      }
      if (cur1 != cur2){
        cur1 = cur1.next;
        cur2 = cur2.next;
      }
      return cur1;
    }else {
       cur1 = loopNode1.next;
       while (cur1 != loopNode1){
         if (cur1 == loopNode2){
           return loopNode1;
         }
         cur1 = cur1.next;
       }

      return null;
    }


  }

  /**
   * 获取链表的循环节点
   * 没有就返回 null
   *
   * @param head
   * @return
   */
  public static Node getLoopNode(Node head){
    if (null == head || head.next == null || head.next.next == null){
      return null;
    }

    Node slow = head.next;
    Node fast = head.next.next;
    while (slow != fast){
      if (fast.next == null || fast.next.next == null){
        return null;
      }

      slow = slow.next;
      fast = fast.next.next;
    }


    fast = head;

    while (fast != slow){
      slow = slow.next;
      fast = fast.next;
    }

    return slow;
  }


  /**
   * 两个没有循环节点的链表 求相交节点
   *
   * @param head1
   * @param head2
   * @return
   */
  public static Node noLoop(Node head1, Node head2){
    if (null == head1 || null == head2){
      return null;
    }

    // 思路：
    //   计算两个链表的节点数误差 n;
    //   然后长链表第n个节点开始遍历，短链表直接遍历
    //   此时就是两个等长的链表在遍历，如果出现两个节点相等，就证明两个链表相交，否则就是没有相交节点
    int n = 0;
    Node cur = head1;
    while (cur != null){
      n++;
      cur = cur.next;
    }
    Node cur2 = head2;
    while (cur2 != null){
      n--;
      cur2 = cur2.next;
    }
    // 如果尾节点不相等，两个链表肯定不相交
    if (cur != cur2){
      return null;
    }
    Node longNode = n > 0? head1:head2;
    Node shortNode = longNode == head1? head2:head1;
    n = Math.abs(n);
    while (n > 0){
      longNode = longNode.next;
      n--;
    }

//    while (longNode != null){
//      if (longNode == shortNode){
//        return longNode;
//      }
//      longNode = longNode.next;
//      shortNode = shortNode.next;
//    }
//
//    return null;
    while (longNode != shortNode){
      longNode = longNode.next;
      shortNode = shortNode.next;
    }
    return longNode;

  }

  public static void main(String[] args) {
    // 1->2->3->4->5->6->7->null
    Node head1 = new Node(1);
    head1.next = new Node(2);
    head1.next.next = new Node(3);
    head1.next.next.next = new Node(4);
    head1.next.next.next.next = new Node(5);
    head1.next.next.next.next.next = new Node(6);
    head1.next.next.next.next.next.next = new Node(7);

    // 0->9->8->6->7->null
    Node head2 = new Node(0);
    head2.next = new Node(9);
    head2.next.next = new Node(8);
    head2.next.next.next = head1.next.next.next.next.next; // 8->6
    System.out.println(getIntersectNode(head1, head2).value);

    // 1->2->3->4->5->6->7->4...
    head1 = new Node(1);
    head1.next = new Node(2);
    head1.next.next = new Node(3);
    head1.next.next.next = new Node(4);
    head1.next.next.next.next = new Node(5);
    head1.next.next.next.next.next = new Node(6);
    head1.next.next.next.next.next.next = new Node(7);
    head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

    // 0->9->8->2...
    head2 = new Node(0);
    head2.next = new Node(9);
    head2.next.next = new Node(8);
    head2.next.next.next = head1.next; // 8->2
    System.out.println(getIntersectNode(head1, head2).value);

    // 0->9->8->6->4->5->6..
    head2 = new Node(0);
    head2.next = new Node(9);
    head2.next.next = new Node(8);
    head2.next.next.next = head1.next.next.next.next.next; // 8->6
    System.out.println(getIntersectNode(head1, head2).value);

  }





}
