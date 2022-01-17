package com.gzy.code.basic.class08;

import java.util.Arrays;

/**
 * description: RadixSort date: 2022/1/16 4:19 下午
 *
 *
 * @author: guizhenyu
 */
public class RadixSort {


  // for test
  public static void comparator(int[] arr) {
    Arrays.sort(arr);
  }

  // for test
  public static int[] generateRandomArray(int maxSize, int maxValue) {
    int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = (int) ((maxValue + 1) * Math.random());
    }
    return arr;
  }

  // for test
  public static int[] copyArray(int[] arr) {
    if (arr == null) {
      return null;
    }
    int[] res = new int[arr.length];
    for (int i = 0; i < arr.length; i++) {
      res[i] = arr[i];
    }
    return res;
  }

  // for test
  public static boolean isEqual(int[] arr1, int[] arr2) {
    if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
      return false;
    }
    if (arr1 == null && arr2 == null) {
      return true;
    }
    if (arr1.length != arr2.length) {
      return false;
    }
    for (int i = 0; i < arr1.length; i++) {
      if (arr1[i] != arr2[i]) {
        return false;
      }
    }
    return true;
  }

  // for test
  public static void printArray(int[] arr) {
    if (arr == null) {
      return;
    }
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }


  // for test
  public static void main(String[] args) {
    int testTime = 500000;
    int maxSize = 100;
    int maxValue = 100000;
    boolean succeed = true;
    for (int i = 0; i < testTime; i++) {
      int[] arr1 = generateRandomArray(maxSize, maxValue);
      int[] arr2 = copyArray(arr1);
      radixSort(arr1);
      comparator(arr2);
      if (!isEqual(arr1, arr2)) {
        succeed = false;
        printArray(arr1);
        printArray(arr2);
        break;
      }
    }
    System.out.println(succeed ? "Nice!" : "Fucking fucked!");

    int[] arr = generateRandomArray(maxSize, maxValue);
    printArray(arr);
    radixSort(arr);
    printArray(arr);

  }

  /**
   * 并排序
   * 思路：
   *     1.
   * @param arr1
   */
  public static void radixSort(int[] arr1) {
    if (null == arr1 || arr1.length < 2){
      return;
    }

    // 1.求数组是几位数


    // 2. 根据几位数，就遍历几次，完成排序
    radixSort(arr1, 0, arr1.length - 1, digit(arr1));

  }


  /**
   * arr[L..R]排序  ,  最大值的十进制位数digit
   *
   * @param arr
   * @param L
   * @param R
   * @param digit
   */
  private static void radixSort(int[] arr, int L, int R, int digit) {
    final int radix = 10;
    // 准备辅助空间
    int length = arr.length;
    int[] help = new int[length];

    for (int i = 0; i < digit; i++){
      int[] counts = new int[radix];
      for (int j = 0; j < length; j++){

        counts[getDigit(arr[j], i)]++;

      }

      for (int j = 1; j < radix; j++){
        counts[j] = counts[j] + counts[j - 1];
      }

      for (int j = R; j >= L; j--){
        int index = getDigit(arr[j], i);

        help[counts[index] - 1] = arr[j];
        counts[index]--;

      }

      for(int j = 0; j < length; j++){
        arr[j] = help[j];
      }

    }


  }


  public static int getDigit(int x, int d){

    return (x / (int)(Math.pow(10, d))) % 10;

  }

  /**
   * 获取几位数
   *
   * @param arr
   * @return
   */
  public static int digit(int[] arr){

    int max = Integer.MIN_VALUE;

    for (int i = 0; i < arr.length; i++){
      if (arr[i] > max){
        max = arr[i];
      }

    }

    int ans = 0;
    while(max > 0){
      ans++;
      max = max / 10;
    }

    return ans;

  }

}
