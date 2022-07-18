package com.gzy.code.basic.class17.sec07;

/**
 * description: Hanoi1 date: 2022/7/7 09:24
 *
 * @author: guizhenyu
 */
public class Hanoi1 {

  public static void main(String[] args) {
    function(3);
  }

  private static void function(int i) {

    process(i, "left", "right", "mid");
  }

  private static void process(int n, String from, String to, String other) {

    if (n == 1){
      System.out.println("1 from " + from + " to " + to);
      return;
    }

    process(n - 1, from, other, to);
    System.out.println(n + "from " + from + " to " + to);
    process(n - 1, other, to, from);
  }

}
