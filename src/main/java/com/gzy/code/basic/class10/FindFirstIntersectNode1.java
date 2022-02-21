package com.gzy.code.basic.class10;


/**
 * description: FindFirstIntersectNode11 date: 2022/2/18 4:56 下午
 * 给定两个可能有环也可能无环的单链表，头节点head1 和head2。
 * 请实现一个函数，如果两个链表相交，请返回相交的 第一个节点。如果不相交，返回null
 * 【要求】
 * 如果两个链表长度之和为N，时间复杂度请达到O(N)，额外空间复杂度 请达到O(1)。
 * @author: guizhenyu
 */
public class FindFirstIntersectNode1 {

  public static class Node{
    public int value;
    public Node next;

    public Node(int value){
      this.value = value;
    }
  }


  public static Node getIntersectNode1(Node node1, Node node2){
    if (node1 == null || node2 == null){
      return null;
    }
    /**
     * 求两个链表是否有相交点的情况总结：
     * 1.先判断，两个链表是否是循环节点
     * 1.1 都不是循环节点
     *     直接是判断两个链表是否相交
     *
     * 1.2 两个都是循环节点
     * 1.2.1 是在循环外面相交
     * 1.2.2 是在循环里面相交
     *
     * 1.3 一个是循环节点，一个不是循环节点
     * 这种情况是没有，脑补下
     */

    Node loopNode1 = isLoopNode(node1);
    Node loopNode2 = isLoopNode(node2);

    if (loopNode1 == null && loopNode1 == null){
      return noLoop(node1, node2);
    }

    if (loopNode1 != null && loopNode2 != null){
      return boothLoop(node1, loopNode1, node2, loopNode1);
    }

    return null;
  }

  private static Node boothLoop(Node node1, Node loopNode1, Node node2, Node loopNode2) {
    /**
     * 有两种相交情况，
     * 情况1： 循环节点前就相交
     * 情况2： 循环链表中间相交
     *
     */
    Node cur1 = null;
    Node cur2 = null;
    if (loopNode1 == loopNode2){
      // 情况1
      cur1 = node1;
      cur2 = node2;
      int n = 0;

      while (cur1 != loopNode1){
        n++;
        cur1 = cur1.next;
      }

      while (cur2 != loopNode2){
        n--;
        cur2 = cur2.next;
      }

      Node longNode = null;
      Node shortNode = null;
      longNode = n > 0? node1:node2;
      shortNode = longNode == node1?node2:node1;
      n = Math.abs(n);

      while (n > 0){
        n--;
        longNode = longNode.next;
      }

      while (longNode != shortNode){
        longNode = longNode.next;
        shortNode = shortNode.next;
      }


      return longNode;
    }else {
      // 情况2
      cur1 = loopNode1;
      while (cur1 != loopNode1){
        if (cur1 == loopNode2){
          return loopNode2;
        }
        cur1 = cur1.next;
      }

    }


    return null;
  }

  private static Node noLoop(Node node1, Node node2) {
    if (node1 == null || node2 == null){
      return null;
    }
    /**
     * 两个都不是循环链表
     * 1. 如果尾节点不相等，肯定不想交
     * 2. 链表长度先比较长短节点，然后让长链表从距离尾节点距离和短链表长度一样开始一起遍历
     */
    Node cur1 = node1;
    int n = 0;
    while (cur1.next != null){
      n++;
      cur1 = cur1.next;
    }

    Node cur2 = node2;
    while (cur2.next != null){
      n--;
      cur2 = cur2.next;
    }

    if (cur2 != cur1){
      return null;
    }

    Node longNode = null;
    Node shortNode = null;
    longNode = n > 0 ? node1:node2;
    shortNode = longNode == node1?node2:node1;

    n = Math.abs(n);

    while (n > 0){
      longNode = longNode.next;
      n--;
    }

    while (longNode != shortNode){
      longNode = longNode.next;
      shortNode = shortNode.next;
    }

    return longNode;


  }

  /**
   * 判断节点是否是循环节点
   * @param node
   * @return
   */
  public static Node isLoopNode(Node node){
    if (null == node || null == node.next || null == node.next.next){
      return null;
    }

    // 判断是否是循环节点的办法：
    // 用快慢节点的方式去判断
    Node fastNode = node.next.next;
    Node slowNode = node.next;

    while(fastNode != slowNode){
      if (fastNode.next == null || fastNode.next.next == null){
        return null;
      }
      slowNode = slowNode.next;
      fastNode = fastNode.next.next;
    }

    fastNode = node;

    while (fastNode != node){
      fastNode = fastNode.next;
      slowNode = slowNode.next;
    }

    return slowNode;




  }

  public static void main(String[] args) {
    // 1->2->3->4->5->6->7->null
    FindFirstIntersectNode1.Node head1 = new FindFirstIntersectNode1.Node(1);
    head1.next = new FindFirstIntersectNode1.Node(2);
    head1.next.next = new FindFirstIntersectNode1.Node(3);
    head1.next.next.next = new FindFirstIntersectNode1.Node(4);
    head1.next.next.next.next = new FindFirstIntersectNode1.Node(5);
    head1.next.next.next.next.next = new FindFirstIntersectNode1.Node(6);
    head1.next.next.next.next.next.next = new FindFirstIntersectNode1.Node(7);

    // 0->9->8->6->7->null
    FindFirstIntersectNode1.Node head2 = new FindFirstIntersectNode1.Node(0);
    head2.next = new FindFirstIntersectNode1.Node(9);
    head2.next.next = new FindFirstIntersectNode1.Node(8);
    head2.next.next.next = head1.next.next.next.next.next; // 8->6
    System.out.println(getIntersectNode1(head1, head2).value);

    // 1->2->3->4->5->6->7->4...
    head1 = new FindFirstIntersectNode1.Node(1);
    head1.next = new FindFirstIntersectNode1.Node(2);
    head1.next.next = new FindFirstIntersectNode1.Node(3);
    head1.next.next.next = new FindFirstIntersectNode1.Node(4);
    head1.next.next.next.next = new FindFirstIntersectNode1.Node(5);
    head1.next.next.next.next.next = new FindFirstIntersectNode1.Node(6);
    head1.next.next.next.next.next.next = new FindFirstIntersectNode1.Node(7);
    head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

    // 0->9->8->2...
    head2 = new FindFirstIntersectNode1.Node(0);
    head2.next = new FindFirstIntersectNode1.Node(9);
    head2.next.next = new FindFirstIntersectNode1.Node(8);
    head2.next.next.next = head1.next; // 8->2
    System.out.println(getIntersectNode1(head1, head2).value);

    // 0->9->8->6->4->5->6..
    head2 = new FindFirstIntersectNode1.Node(0);
    head2.next = new FindFirstIntersectNode1.Node(9);
    head2.next.next = new FindFirstIntersectNode1.Node(8);
    head2.next.next.next = head1.next.next.next.next.next; // 8->6
    System.out.println(getIntersectNode1(head1, head2).value);

  }

}
