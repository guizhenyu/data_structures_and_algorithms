package com.gzy.code.basic.class30;

/**
 * description: MorrisTraversal date: 2022/5/12 18:01
 *
 * @author: guizhenyu
 */
public class MorrisTraversal {

  /**
   * todo:
   *      一般对二叉树的遍历的空间复杂度是 O(logN)
   *      但是morris能在同等时间复杂度的基础上，实现空间复杂度为O(1)的遍历
   *      步骤：
   *      假设我们来到二叉树的节点cur
   *      1. cur没有左节点，cur直接指向cur的右节点
   *      2. cur有左节点，获取左节点的最右节点mostRight
   *        2.1 mostRight的右指针为null(第一次遇到)，将mostRight的右指针指向cur
   *            将cur指向cur的左节点
   *        2.2 mostRight的右指针指针指向cur，将mostRight的右节点置为null,
   *            cur指向cur的右节点
   *      3. cur 为null,结束
   * todo
   *      总结上面的遍历过程，为什么能实现空间复杂度为O(1)
   *      正常的遍历采取递归，一层一层的遍历，
   *      每一层其实就是一个桢栈的数据结构， 总共有O(logN)层，也就对应O(logN)个桢栈
   *      而morris,通过二叉树的空余的指针，达到了递归的效果，避免了重新开辟桢栈的空间消耗，从而实现了空间复杂度O(1)
   *      上面的 2.1就相当于，实现了递归效果
   *
   *
   *
   */

  public static class Node{
    int value;
    Node left;
    Node right;

    public Node(int v){
      value = v;
    }
  }

  public static void process(Node head){
    if (null == head){
      return;
    }
    // 1
    process(head.left);
    // 2
    process(head.right);
    // 3
  }

  public static void morris(Node head){
    if (null == head){
      return;
    }

    Node cur = head;
    Node mostRight = null;
    while (cur != null){
      mostRight = cur.left;
      if (mostRight != null){
        // 2. cur有左节点，获取左节点的最右节点mostRight
        while (mostRight.right != null && mostRight.right != cur){
          mostRight = mostRight.right;
        }

        if (mostRight.right == null){
         // 2.1 mostRight的右指针为null(第一次遇到)，
          // 将mostRight的右指针指向cur将cur指向cur的左节点
          mostRight.right = cur;
          cur = cur.left;
        }
        if (mostRight.right == cur){
          // 2.2 mostRight的右指针指针指向cur，
          // 将mostRight的右节点置为null,cur指向cur的右节点
          mostRight.right = null;
          cur = cur.right;
        }

      }else {
        // 1. cur没有左节点，cur直接指向cur的右节点
        cur = cur.right;
      }
    }
  }
  public static void morris1(Node head) {
    if (null == head) {
      return;
    }

    Node cur = head;
    Node mostRight = null;
    while (cur != null) {
      mostRight = cur.left;
      if (mostRight != null) {
        // 2. cur有左节点，获取左节点的最右节点mostRight
        while (mostRight.right != null && mostRight.right != cur) {
          mostRight = mostRight.right;
        }

        if (mostRight.right == null) {
          // 2.1 mostRight的右指针为null(第一次遇到)，
          // 将mostRight的右指针指向cur将cur指向cur的左节点
          mostRight.right = cur;
          cur = cur.left;
          continue;
        } else {
          mostRight.right = null;
        }

      }
      // 1. cur没有左节点，cur直接指向cur的右节点
      cur = cur.right;
    }
  }
  public static void morrisPre(Node head){
    if (null == head){
      return;
    }

    Node cur = head;
    Node mostRight = null;
    while (cur != null){
      mostRight = cur.left;
      if (mostRight != null) {
        // 2. cur有左节点，获取左节点的最右节点mostRight
        while (mostRight.right != null && mostRight.right != cur) {
          mostRight = mostRight.right;
        }

        if (mostRight.right == null) {
          // 2.1 mostRight的右指针为null(第一次遇到)，
          // 将mostRight的右指针指向cur将cur指向cur的左节点
          System.out.print(cur.value + " ");
          mostRight.right = cur;
          cur = cur.left;
          continue;
        }else {
          mostRight.right = null;
        }

      }else {
        System.out.print(cur.value + " ");
      }
      // 1. cur没有左节点，cur直接指向cur的右节点
      cur = cur.right;
    }
    System.out.println();
  }
  public static void morrisIn(Node head){
    if (null == head){
      return;
    }

    Node cur = head;
    Node mostRight = null;
    while (cur != null){
      mostRight = cur.left;
      if (mostRight != null) {
        // 2. cur有左节点，获取左节点的最右节点mostRight
        while (mostRight.right != null && mostRight.right != cur) {
          mostRight = mostRight.right;
        }

        if (mostRight.right == null) {
          // 2.1 mostRight的右指针为null(第一次遇到)，
          // 将mostRight的右指针指向cur将cur指向cur的左节点
          mostRight.right = cur;
          cur = cur.left;
          continue;
        }else {
          System.out.print(cur.value + " ");
          mostRight.right = null;
        }

      }else {
        System.out.print(cur.value + " ");
      }
      // 1. cur没有左节点，cur直接指向cur的右节点
      cur = cur.right;
    }

    System.out.println();
  }
  public static void morrisPos(Node head){
    if (null == head){
      return;
    }

    Node cur = head;
    Node mostRight = null;
    while (cur != null){
      mostRight = cur.left;
      if (mostRight != null){
        // 2. cur有左节点，获取左节点的最右节点mostRight
        while (mostRight.right != null && mostRight.right != cur){
          mostRight = mostRight.right;
        }

        if (mostRight.right == null){
          // 2.1 mostRight的右指针为null(第一次遇到)，
          // 将mostRight的右指针指向cur将cur指向cur的左节点
          mostRight.right = cur;
          cur = cur.left;
        }
        if (mostRight.right == cur){
          // 2.2 mostRight的右指针指针指向cur，
          // 将mostRight的右节点置为null,cur指向cur的右节点
          mostRight.right = null;
          printEdge(cur.left);
          cur = cur.right;
        }

      }else {
        // 1. cur没有左节点，cur直接指向cur的右节点
//        printEdge(cur);
        cur = cur.right;
      }
    }
    printEdge(head);
    System.out.println();
  }


  public static void printEdge(Node head){
    if (head == null){
      return;
    }
    Node tail = reverseEdge(head);
    Node cur = tail;
    while (cur != null){
      System.out.print(cur.value + " ");
      cur = cur.right;
    }
    reverseEdge(tail);
  }

  /**
   * 以当前节点开始，只反转他的所有右节点
   *
   * @param from
   * @return
   */
  private static Node reverseEdge(Node from) {
    // 当前节点未反转前的前一个节点
    Node pre = null;
    // 当前节点未反转前的后一个右节点
    Node next = null;
    while (from != null){
      // 当前节点的右节点作为一个循环的节点
      next = from.right;
      // 当前节点的右节点指向父节点
      from.right = pre;
      // 当前节点变为父节点
      pre = from;
      // form节点变为next
      from = next;
    }
    return pre;
  }

  public static void main(String[] args) {
    Node head = new Node(4);
    head.left = new Node(2);
    head.right = new Node(6);
    head.left.left = new Node(1);
    head.left.right = new Node(3);
    head.right.left = new Node(5);
    head.right.right = new Node(7);
    printTree(head);
    morrisIn(head);
    morrisPre(head);
    morrisPos(head);
    printTree(head);
  }

  private static void printTree(Node head) {

    if (null == head){
      return;
    }
    printInOrder(head, 0, "H", 17);
    System.out.println();
  }

  private static void printInOrder(Node head, int height, String mark, int spaceCount) {
    if (null == head){
      return;
    }


    printInOrder(head.right, height + 1, "v", spaceCount);
    String val = mark + head.value + mark;
    int lLen = (spaceCount - val.length()) >> 1;
    int rLen = spaceCount - lLen - val.length();
    val = getSpaces(lLen) + val + getSpaces(rLen);
    System.out.println(getSpaces(height * spaceCount) + val);
    printInOrder(head.left, height + 1, "^", spaceCount);
  }

  public static String getSpaces(int spaceCount){
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < spaceCount; i++){
      sb.append(" ");
    }
    return sb.toString();
  }

}
