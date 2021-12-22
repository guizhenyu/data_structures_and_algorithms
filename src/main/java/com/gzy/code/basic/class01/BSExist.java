package com.gzy.code.basic.class01;

import java.util.Arrays;

/**
 * description: BSExist date: 2021/12/21 1:17 下午
 * 在一个有序数组中，找某个数是否存在
 * 采用二分法实现
 *
 * @author: guizhenyu
 */
public class BSExist {

  public static boolean bSExist(int[] arr, int value){
    if (null == arr || arr.length == 0){
      return false;
    }

    int length = arr.length;
//    int process = process(arr, value, 0, length - 1);
    int process = processLoop(arr, value);

    return  process == -1? false : true;
  }



  public static int processLoop(int[] arr, int value){

    int L = 0;

    int R = arr.length - 1;
    int mid = 0;
    while(L < R){

      mid = L + ((R - L) >> 1);

      if (arr[mid] > value){
        R = mid - 1;
      }

      if (arr[mid] == value){
        return mid;
      }

      if (arr[mid] < value){
        L = mid + 1;
      }

    }
    return arr[L] == value? L:-1;
  }

  /**
   * 采用递归做，比价复杂
   * @param arr
   * @param value
   * @param left
   * @param right
   * @return
   */
  private static int process(int[] arr, int value, int left, int right) {

    if (arr[left] == value){
      return left;
    }
    if (arr[right] == value){
      return right;
    }

    if (left >= right){
      return -1;
    }

    int mid = left + ((right - left) >> 1);

    if (arr[mid] == value){
      return mid;
    }

    if (arr[mid] > value){
      return process(arr, value, left, mid);
    }

    return process(arr, value, mid + 1, right);

  }


  // for test
  public static boolean test(int[] sortedArr, int num) {
    for(int cur : sortedArr) {
      if(cur == num) {
        return true;
      }
    }
    return false;
  }


  // for test
  public static int[] generateRandomArray(int maxSize, int maxValue) {
    int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
    }
    return arr;
  }

  public static void main(String[] args) {
    int testTime = 1000000;
    int maxSize = 10;
    int maxValue = 100;
    boolean succeed = true;
    for (int i = 0; i < testTime; i++) {
      int[] arr = generateRandomArray(maxSize, maxValue);
      Arrays.sort(arr);
      int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
      if (test(arr, value) != bSExist(arr, value)) {
        succeed = false;
        System.out.println();
        break;
      }
    }
    System.out.println(succeed ? "Nice!" : "Fucking fucked!");
  }

}
