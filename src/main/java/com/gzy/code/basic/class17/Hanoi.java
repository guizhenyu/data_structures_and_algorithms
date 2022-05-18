package com.gzy.code.basic.class17;

/**
 * description: Hanoi date: 2022/4/7 8:39 上午
 *
 * @author: guizhenyu
 */
public class Hanoi {

  public static void hanoi1(int n){
    leftToRight(n);
  }

  public static void leftToRight(int n){
    // base case
    if(n == 1){
      System.out.println("Move 1 from left to right");
      return;
    }

    // 先把 n - 1 都移动到mid
    leftToMid(n - 1);
    System.out.println("Move " + n + " from left to right");
    midToRight(n - 1);

  }

  private static void midToRight(int n) {
    // base case
    if(n == 1){
      System.out.println("Move 1 from mid to right");
      return;
    }
    // 把 n - 1 移动到左边
    midToLeft(n - 1);
    System.out.println("Move " + n + " from mid to right");
    leftToRight(n - 1);

  }

  private static void midToLeft(int n) {
    // base case
    if(n == 1){
      System.out.println("Move 1 from mid to left");
      return;
    }
    midToRight(n - 1);
    System.out.println("Move " + n + " from mid to left");
    rightToLeft(n - 1);
  }

  private static void rightToLeft(int n) {
    // base case
    if(n == 1){
      System.out.println("Move 1 from right to left");
      return;
    }
    rightToMid(n - 1);
    System.out.println("Move " + n + " from right to left");
    midToLeft( n - 1);
  }

  private static void leftToMid(int n) {
    // base case
    if(n == 1){
      System.out.println("Move 1 from left to mid");
      return;
    }

    // 把 n - 1 移动到右边
    leftToRight(n - 1);
    System.out.println("Move " + n + " from left to mid");
    rightToMid(n - 1);
  }

  private static void rightToMid(int n) {
    // base case
    if(n == 1){
      System.out.println("Move 1 from right to mid");
      return;
    }
    rightToLeft(n - 1);
    System.out.println("Move " + n + " from right to mid");
    leftToMid(n - 1);

  }

  public static void main(String[] args) {
    hanoi1(3);
    System.out.println("===========================");
    hanoi2(3);
  }

  private static void hanoi2(int n) {
    hanoiFunction(n, "left", "right", "mid");
  }

  private static void hanoiFunction(int n, String from, String to, String other) {
    if (n == 1){
      System.out.println("Move 1 from " + from + " to " + to);
      return;
    }

    hanoiFunction(n - 1, from, other, to);
    System.out.println("Move " + n + " from " + from + " to " + to);
    hanoiFunction(n - 1, other, to, from);

  }


}
