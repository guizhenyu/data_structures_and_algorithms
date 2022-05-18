package com.gzy.code.basic.class24;

import java.util.LinkedList;

/**
 * description: SlidingWindowMaxArray date: 2022/4/29 10:46
 *
 * @author: guizhenyu
 */
public class SlidingWindowMaxArray {

  /**
   * 假设一个固定大小为W的窗口，依次划过arr，
   * 返回每一次滑出状况的最大值
   * 例如，arr = [4,3,5,4,3,3,6,7], W = 3
   * 返回：[5,5,5,4,6,7]
   *
   */

  public static void main(String[] args) {
    int testTime = 1000;
    int maxLen = 100;
    int maxValue = 100;

    for (int i = 0; i < testTime; i++){
      int[] arr = generate(maxLen, maxValue);
      int w = randomByValue(arr.length + 1);

      int[] ans = right(arr, w);
      int[] ans1 = natureWisdom(arr, w);
      int[] maxWindow = getMaxWindow(arr, w);


      if (!equals(ans, maxWindow) || !equals(ans1, ans)){
        equals(ans, maxWindow);
        equals(ans1, ans);
        printArr(arr);
        printArr(ans);
        printArr(ans1);
        printArr(maxWindow);
        break;
      }
    }
  }

  private static boolean equals(int[] arr1, int[] arr2) {

    if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
      return false;
    }
    if (arr1 == null && arr2 == null) {
      return true;
    }
    if (arr1.length != arr2.length) {
      return false;
    }
    for (int i = 0; i < arr1.length; i++){
      if (arr1[i] != arr2[i]){
        return false;
      }

    }
    return true;
  }

  private static void printArr(int[] arr){
    if (null == arr || arr.length == 0){
      return;
    }
    for (int i : arr){
      System.out.print(i + " ");
    }
    System.out.println();
  }

  private static int[] natureWisdom(int[] arr, int w) {
    if (arr == null || w < 1 || arr.length < w) {
      return null;
    }
    int l = 0;
    int r = l + w - 1;
    int len = arr.length;

    int[] result = new int[len - w + 1];
//    int maxValue = 0;
    int resultIndex = 0;
    while (r < len){
      int maxValue = 0;
      for (int i = l; i <= r; i++){
        maxValue = Math.max(arr[i], maxValue);
      }
      result[resultIndex++] = maxValue;
      l++;
      r++;
    }

    return result;


  }

  public static int[] right(int[] arr, int w) {
    if (arr == null || w < 1 || arr.length < w) {
      return null;
    }
    int N = arr.length;
    int[] res = new int[N - w + 1];
    int index = 0;
    int L = 0;
    int R = w - 1;
    while (R < N) {
      int max = arr[L];
      for (int i = L + 1; i <= R; i++) {
        max = Math.max(max, arr[i]);

      }
      res[index++] = max;
      L++;
      R++;
    }
    return res;
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

  private static int[] getMaxWindow(int[] arr, int w){
    if (null == arr || w < 1 || arr.length < w){
      return null;
    }

    //qmax 窗口最大值的更新结构
    LinkedList<Integer> qmax = new LinkedList<>();
    int len = arr.length;

    int[] result = new int[len - w + 1];
    int resultIndex = 0;
    for (int i = 0; i < len; i++){
      while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]){
        qmax.pollLast();
      }
      qmax.addLast(i);

      if (qmax.peekFirst() == i - w){
        qmax.pollFirst();
      }
      if (i >= w - 1){
        result[resultIndex++] = arr[qmax.peekFirst()];
      }

    }

    return result;
  }


}
