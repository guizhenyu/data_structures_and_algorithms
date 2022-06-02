package com.gzy.code.basic.class46;

import java.util.LinkedList;

/**
 * description: MaxSumLengthNoMore date: 2022/6/2 14:07
 * 给定一个数组arr，和一个正数M，
 * 返回在arr的子数组在长度不超过M的情况下，最大的累加和
 * @author: guizhenyu
 */
public class MaxSumLengthNoMore {

  public static void main(String[] args) {
    int maxLen = 50;
    int maxValue = 100;
    int testTime = 10000;

    for (int i = 0; i < testTime; i++){
      int len = (int)(Math.random() * maxLen) + 1;
      int M = (int)(Math.random() * maxLen);
      int[] arr = generateArr(len, maxValue);
      long ans0 = natureWisdom(arr, M);
      long ans1 = way1(arr, M);
      long ans2 = test(arr, M);

      if (ans0 != ans1 || ans2 != ans0){
        System.out.println("Oops!");
      }
    }
  }

  public static int maxSum(int[] arr, int M) {
    if (arr == null || arr.length == 0 || M < 1) {
      return 0;
    }
    int N = arr.length;
    int[] sum = new int[N];
    sum[0] = arr[0];
    for (int i = 1; i < N; i++) {
      sum[i] = sum[i - 1] + arr[i];
    }
    LinkedList<Integer> qmax = new LinkedList<>();
    int i = 0;
    int end = Math.min(N, M);
    for (; i < end; i++) {
      while (!qmax.isEmpty() && sum[qmax.peekLast()] <= sum[i]) {
        qmax.pollLast();
      }
      qmax.add(i);
    }
    int max = sum[qmax.peekFirst()];
    int L = 0;
    for (; i < N; L++, i++) {
      if (qmax.peekFirst() == L) {
        qmax.pollFirst();
      }
      while (!qmax.isEmpty() && sum[qmax.peekLast()] <= sum[i]) {
        qmax.pollLast();
      }
      qmax.add(i);
      max = Math.max(max, sum[qmax.peekFirst()] - sum[L]);
    }
    for (; L < N - 1; L++) {
      if (qmax.peekFirst() == L) {
        qmax.pollFirst();
      }
      max = Math.max(max, sum[qmax.peekFirst()] - sum[L]);
    }
    return max;
  }
  public static int test(int[] arr, int M) {
    if (arr == null || arr.length == 0 || M < 1) {
      return 0;
    }
    int N = arr.length;
    int max = Integer.MIN_VALUE;
    for (int L = 0; L < N; L++) {
      int sum = 0;
      for (int R = L; R < N; R++) {
        if (R - L + 1 > M) {
          break;
        }
        sum += arr[R];
        max = Math.max(max, sum);
      }
    }
    return max;
  }

  private static long way1(int[] arr, int m) {
    if (arr == null || arr.length == 0 || m < 1){
      return 0;
    }
    /**
     * 思路，
     * 1. 利用前缀和，来解决求每个子串和的时间复杂度，降低到O(1)
     * 2. 每个点作为子串的开始节点时，往右滑动窗口，使窗口大小不超过m, 并且利用队列保存大的和
     *    可以实现往后的遍历不回退。
     *    2.1 点A作为子串的头节点时，往右遍历M - 1 长度后，会得出A点作为头结点的最大的和
     *    2.2 A的下一个节点B, 先来来到B作为头结点， 剔除A点的保存在队列中的最大值（如果存在），而B ~ (A + m - 2) 中的前缀和不需要遍历，
     *        已经存在队列中，所以实现了不回退，总体的时间复杂度就是2N
     *
     *
     */

    int n = arr.length;
    int min = Math.min(n, m);
    int[] sums = new int[n];
    sums[0] = arr[0];
    for (int i = 1; i < n; i++){
      sums[i] = sums[i - 1] + arr[i];
    }

    LinkedList<Integer> qmax = new LinkedList<>();

    int L = 0;
    int R = 0;
    // 这边取min, 是应对，n < m 可能
    // 计算 头结点从0开始计算到 min的所以子串的最大值
    for (; R < min; R++){
      while (!qmax.isEmpty() && sums[qmax.peekLast()] <= sums[R]){
        qmax.pollLast();
      }
      qmax.add(R);
    }
    int max = sums[qmax.peekFirst()];

    // 头结点从 m 到 n - m的最大值
    for (;R < n; L++, R++){
      if (qmax.peekFirst() == L){
        qmax.pollFirst();
      }
      while (!qmax.isEmpty() && sums[qmax.peekLast()] <= sums[R]){
        qmax.pollLast();
      }
      qmax.add(R);
      max = Math.max(max, sums[qmax.peekFirst()] - sums[L]);
    }

    // 剩余的
    for (;L < n - 1; L++){
      if (qmax.peekFirst() == L){
        qmax.pollFirst();
      }
      max = Math.max(max, sums[qmax.peekFirst()] - sums[L]);
    }

    return max;
  }

  private static long natureWisdom(int[] arr, int m) {
    if (arr == null || arr.length == 0 || m < 1){
      return 0;
    }

    int n = arr.length;

    int max = Integer.MIN_VALUE;


    for (int L = 0; L < n; L++){
      int sum = 0;
        for (int R = L; R < n; R++){
          if (R - L + 1 > m){
            break;
          }
          sum += arr[R];
          max = Math.max(max, sum);
        }
    }

    return max;

  }

  private static int[] generateArr(int len, int maxValue) {
    int[] arr = new int[len];

    for (int i = 0; i < len; i++){
      arr[i] = (int)(maxValue * Math.random() - maxValue * Math.random());
    }

    return arr;
  }

}
