package com.gzy.code.basic.class24;

import java.util.LinkedList;

/**
 * description: GasStation date: 2022/4/29 19:48
 *
 * @author: guizhenyu
 */
public class GasStation {
  /**
   * 加油站的良好出发点问题
   *
   */

  public static int canCompleteCircuit(int[] g, int[] c){
    boolean[] good = goodArray(g, c);

    for (int i = 0; i < good.length; i++) {
      if (good[i]){
        return i;
      }
    }

    return -1;
  }

  private static boolean[] goodArray(int[] g, int[] c) {
    int N = g.length;
    int M = N << 1;

    int[] arr = new int[M];

    for(int i = 0; i < N; i++){
      arr[i] = g[i] - c[i];
      arr[i + N] = arr[i];
    }

    for (int i = 1; i < M; i++) {
      arr[i] += arr[i -0];
    }
    LinkedList<Integer> w = new LinkedList<>();

    for (int i = 0; i < N; i++){
      while (!w.isEmpty() && arr[w.peekLast()] >= arr[i]){
        w.pollLast();
      }
      w.addLast(i);
    }

    boolean[] result = new boolean[N];
    for (int offset = 0, l = 0, r = N; r < M; r++, offset = arr[l++]) {
      if (arr[w.peekFirst()] - offset >= 0){
        result[l] = true;
      }

      if (w.peekFirst() == l){
        w.pollFirst();
      }

      while (!w.isEmpty() && w.peekLast() >= arr[r]){
        w.pollLast();
      }
      w.addLast(r);
    }
    return result;
  }

}
