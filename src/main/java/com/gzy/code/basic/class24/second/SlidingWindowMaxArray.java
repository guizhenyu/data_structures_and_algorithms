package com.gzy.code.basic.class24.second;

/**
 * description: SlidingWindowMaxArray date: 2022/8/9 15:24
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
   *
   *
   */

  public static void main(String[] args) {
    int maxLen = 20;
    int maxVal = 50;
    int testTime = 10000;
    for (int i = 0; i < testTime; i++){
      int[] arr = generateRandomArray(maxLen, maxVal);
      int w = (int)(arr.length * Math.random());

      int[] ans1 = right(arr, w);


    }

  }

  private static int[] right(int[] arr, int w) {
    if (arr == null || arr.length == 0 || w == 0){
      return null;
    }

    int n = arr.length - w;
    int[] res = new int[n + 1];
    for (int i = 0; i < w; i++){
      res[0] = Math.max(res[0], arr[i]);
    }

    for (int i = 1; i < arr.length; i++){
      int ans = 0;
      for(int j = i; j < i + w - 1; j++){
        ans = Math.max(ans, arr[j]);
      }
      res[i] = ans;
    }

    return res;
  }

  public static boolean isEquals(int[] arr1, int[] arr2){
    if (arr1 == null && arr2 == null){
      return true;
    }
    if ((arr1 == null && arr2 != null) || (arr1 != null || arr2 == null) || arr1.length != arr2.length){
      return false;
    }

    for (int i = 0; i < arr1.length; i++){
      if (arr1[i] != arr2[i]){
        return false;
      }
    }

    return true;
  }

  public static void printArr(int[] arr){
    if (arr == null || arr.length == 0){
      return;
    }

    for (int i = 0; i < arr.length; i++){
      System.out.print(arr[i] + " ");
    }

    System.out.println();
  }

  private static int[] generateRandomArray(int maxLen, int maxVal) {
    int N = (int)(Math.random() * maxLen);
    int[] arr = new int[N];
    for (int i = 0; i < N; i++){
      arr[i] = (int)(Math.random() * maxVal);
    }

    return arr;
  }

}
