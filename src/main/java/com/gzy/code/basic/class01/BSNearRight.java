package com.gzy.code.basic.class01;

import java.util.Arrays;

/**
 * description: BSExist date: 2021/12/21 1:17 下午
 * 在一个有序数组中，找某个数是否存在
 * 采用二分法实现
 *
 * @author: guizhenyu
 */
public class BSNearRight {

  public static int  bSNearLeft(int[] arr, int value){

    if (null == arr || arr.length == 0){
      return -1;
    }
    int L = 0;
    int R = arr.length - 1;
    int index = -1;

    while (L <= R){
      int mid = L + ((R - L) >> 1);
      if (arr[mid] <= value){
        index = mid;
        L = mid + 1;
      }else {
        R = mid - 1;
      }
    }

    return index;
  }


  // for test
//  public static int test(int[] arr, int value) {
//    int index = -1;
//    for (int i = 0; i < arr.length; i++) {
//      if (arr[i] >= value) {
//        index =  i;
//      }
//    }
//    return index;
//  }
// for test
  public static int test(int[] arr, int value) {
    for (int i = arr.length - 1; i >= 0; i--) {
      if (arr[i] <= value) {
        return i;
      }
    }
    return -1;
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
      if (test(arr, value) != bSNearLeft(arr, value)) {
        succeed = false;
        System.out.println();
        break;
      }
    }
    System.out.println(succeed ? "Nice!" : "Fucking fucked!");
  }

}
