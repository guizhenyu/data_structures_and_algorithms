package com.gzy.code.basic.class24;

import java.util.LinkedList;

/**
 * description: AllLessNumSubArray date: 2022/4/29 17:06
 *
 * @author: guizhenyu
 */
public class AllLessNumSubArray {

  /**
   * 给定一个整型数组arr，和一个整数num
   * 某个arr中的子数组sub，如果想达标，必须满足：
   * sub中最大值 – sub中最小值 <= num，
   * 返回arr中达标子数组的数量
   *
   */

  public static void main(String[] args) {
    int testTime = 1000;
    int maxLen = 100;
    int maxValue = 100;

    for (int i = 0; i < testTime; i++){
      int[] arr = generate(maxLen, maxValue);
      int w = randomByValue(maxValue);

      int ans1 = natureWisdom(arr, w);
      int ans2 = right(arr, w);
      int ans3 = num(arr, w);
      int maxWindow = getMaxWindow(arr, w);

      if (ans1 != maxWindow ){
        System.out.println("oops");
      }
    }
  }


  public static int num(int[] arr, int sum) {
    if (arr == null || arr.length == 0 || sum < 0) {
      return 0;
    }
    int N = arr.length;
    int count = 0;
    LinkedList<Integer> maxWindow = new LinkedList<>();
    LinkedList<Integer> minWindow = new LinkedList<>();
    int R = 0;
    for (int L = 0; L < N; L++) {
      while (R < N) {
        while (!maxWindow.isEmpty() && arr[maxWindow.peekLast()] <= arr[R]) {
          maxWindow.pollLast();
        }
        maxWindow.addLast(R);
        while (!minWindow.isEmpty() && arr[minWindow.peekLast()] >= arr[R]) {
          minWindow.pollLast();
        }
        minWindow.addLast(R);
        if (arr[maxWindow.peekFirst()] - arr[minWindow.peekFirst()] > sum) {
          break;
        } else {
          R++;
        }
      }
      count += R - L;
      if (maxWindow.peekFirst() == L) {
        maxWindow.pollFirst();
      }
      if (minWindow.peekFirst() == L) {
        minWindow.pollFirst();
      }
    }
    return count;
  }
  // 暴力的对数器方法
  public static int right(int[] arr, int sum) {
    if (arr == null || arr.length == 0 || sum < 0) {
      return 0;
    }
    int N = arr.length;
    int count = 0;
    for (int L = 0; L < N; L++) {
      for (int R = L; R < N; R++) {
        int max = arr[L];
        int min = arr[L];
        for (int i = L + 1; i <= R; i++) {
          max = Math.max(max, arr[i]);
          min = Math.min(min, arr[i]);
        }
        if (max - min <= sum) {
          count++;
        }
      }
    }
    return count;
  }

  private static int natureWisdom(int[] arr, int sum) {
    int ans = 0;
    if (null == arr || arr.length == 0 || sum < 0){
      return ans;
    }
    int len = arr.length;
    for (int l = 0; l < len; l++){
      for (int r = l; r < len; r++){
        int max = arr[l];
        int min = arr[l];

        for (int i = l + 1; i <= r; i++){
          max = Math.max(arr[i], max);
          min = Math.min(arr[i], min);
        }

        if (max - min <= sum){
          ans++;
        }
      }
    }

    return ans;
  }

  private static int getMaxWindow(int[] arr, int sum) {
    int ans = 0;
    if (null == arr || arr.length == 0 || sum < 0){
      return ans;
    }
    LinkedList<Integer> maxList = new LinkedList<>();
    LinkedList<Integer> minList = new LinkedList<>();
    int len = arr.length;
    int R = 0;
    for (int L = 0; L < len; L++){

      while (R < len){

        while (!maxList.isEmpty() && arr[maxList.peekLast()] <= arr[R]){
          maxList.pollLast();
        }
        maxList.addLast(R);
        while (!minList.isEmpty() && arr[minList.peekLast()] >= arr[R]){
          minList.pollLast();
        }
        minList.addLast(R);

        if (arr[maxList.peekFirst()] - arr[minList.peekFirst()] > sum){
          break;
        }else {
          R++;
        }
      }
      ans += R - L;

      if (maxList.peekFirst() == L){
        maxList.pollFirst();
      }
      if (minList.peekFirst() == L){
        minList.pollFirst();
      }

    }


    return ans;
  }

  private static int[] generate(int maxArrLen, int maxValue) {
    int len = randomByValue(maxArrLen);

    int[] arr = new int[len];

    for (int i = 0; i < len; i++){
      arr[i] = randomByValue(maxValue);
    }

    return arr;
  }

  private static int randomByValue(int v){
    return (int)(Math.random() * v) + 1;
  }

}
