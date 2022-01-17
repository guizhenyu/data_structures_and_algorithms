package com.gzy.code.basic.class08;

import java.util.Arrays;
import java.util.Collections;

/**
 * description: BucketSort date: 2022/1/13 2:52 下午
 *
 *  桶排序
 *
 *
 * @author: guizhenyu
 */
public class BucketSort {


  public static void main(String[] args){

    int testTime = 100000;
    int maxSize = 100;
    int maxValue = 100;
    System.out.println("test started!");
    for (int i = 0; i < testTime; i++){

      int[] arr1 = generateRandomArray(maxSize, maxValue);
      int[] arr2 = copyArray(arr1);
      bucketSort(arr1);
      comparatorSort(arr2);

      if (!equals(arr1, arr2)){
        printArray(arr1);
        printArray(arr2);
        System.out.println("Fucking fucked!");
        break;

      }

    }

    System.out.println("test success!");
  }

  private static void printArray(int[] arr1) {
    if (null == arr1 ){
      return;
    }
    for (int value : arr1){
      System.out.print(value + " ");
    }
    System.out.println();
  }

  private static boolean equals(int[] arr1, int[] arr2) {
    if ((null == arr1 && null != arr2) || (null != arr1 && null == arr2)){
      return false;
    }
    if (null == arr1 && null == arr2){
      return true;
    }

    if (arr1.length != arr2.length){
      return false;
    }

    for (int i = 0; i < arr2.length; i++){
      if (arr1[i] != arr2[i]){
        return false;
      }
    }
    return true;
  }

  private static void comparatorSort(int[] arr2) {

    Arrays.sort(arr2);
  }

  private static void bucketSort(int[] arr) {
    if (null == arr || arr.length < 2){
      return;
    }

    int maxValue = Integer.MIN_VALUE;
    for (int i = 0; i < arr.length; i++) {
      maxValue = Math.max(maxValue, arr[i]);
    }

    int[] bucket = new int[maxValue + 1];

    for (int i = 0; i < arr.length; i++){
      bucket[arr[i]]++;
    }

    int index = 0;
    for (int i = 0; i < bucket.length; i++){
      while (bucket[i]-- > 0){
        arr[index++] = i;
      }
    }



  }

  private static int[] copyArray(int[] arr) {
    if (arr == null) {
      return null;
    }
    int[] res = new int[arr.length];
    for (int i = 0; i < arr.length; i++) {
      res[i] = arr[i];
    }
    return res;
  }

  private static int[] generateRandomArray(int maxSize, int maxValue) {

    int size = (int)(Math.random() * (maxValue + 1));

    int[] ans = new int[size];

    for (int i = 0; i < size; i++){
      int value = (int)(Math.random() * (maxValue + 1));
      ans[i] = value;
    }

    return ans;

  }

}
