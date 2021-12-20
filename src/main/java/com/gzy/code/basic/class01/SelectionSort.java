package com.gzy.code.basic.class01;

import java.util.Arrays;

/**
 * description: SelectionSort date: 2021/12/20 10:43 上午
 *
 * @author: guizhenyu
 */
public class SelectionSort {
  /**
   * 大小为N的arr[]数组，
   * 从小到大排序
   *
   * 选择排序思路：
   * index = 0 时，1 ~ N-1的数跟0的数比较和交换 ==》确定 0
   * index = 1 时，2 ~ N-1的数跟1的数比较和交换 ==》确定 1
   * ...
   * ...
   * ...
   * index = N - 1时，此时所以的数已经排好了     ==》确定 N - 1
   *
   *
   * 冒泡排序思路：
   *  0 ~ N - 1  ==》 N - 1 最大
   *  0 ~ N - 2  ==》 N - 2
   *  0 ~ N - 3  ==>  N - 3
   *  ...
   *  ...
   *  0
   *
   * 插入排序思路：
   * 从index = 1开始往后遍历
   * arr[index] 和 arr[index - 1] 的数做比较：
   * 如果 arr[index] < arr[index - 1]，就交换，然后继续往前（index--）比较，直到不满足就停止
   *
   *
   * 归并排序思路：
   * 分治的思想， 把数据分成
   *
   *
   *
   *
   *
   * 快速排序思路：
   *
   *
   *
   *
   */
  public static void selectionSort(int[] arr){
    if (null == arr || arr.length == 0){
      return;
    }

    for (int i = 0; i < arr.length - 1; i++) {

      int minIndex = i;
      for (int j = i; j < arr.length; j++) {
        if (arr[minIndex] > arr[j]) {
          minIndex = j;
        }
      }
      if (minIndex == i){
        continue;
      }
      swap1(arr, minIndex, i);
    }
  }


  public static void swap1(int[] arr, int i, int j){
    arr[i] = arr[i] ^ arr[j];
    arr[j] = arr[i] ^ arr[j];
    arr[i] = arr[i] ^ arr[j];
  }

  public static void swap(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }

  /**
   *  写比较器
   *  1. 生成数据得随机
   *  2. 测试的次数得很多
   *  3. 通过大量的数据测出，方法的可行性
   */

  public static int[] generateRandomArray(int maxSize, int maxValue){
    int[] arr = new int[(int)((maxSize + 1) * Math.random())];

    for (int i = 0; i < arr.length; i++){
      arr[i] = (int)((maxValue + 1) * Math.random()) - (int)(maxValue * Math.random());
    }

    return arr;
  }


  public static int[] copyArr(int[] arr){
    if (arr == null){
      return null;
    }

    int[] copyArray = new int[arr.length];
    for (int i = 0; i < arr.length; i++){
      copyArray[i] = arr[i];
    }
    return copyArray;
  }

  public static void comparator(int[] arr){
    Arrays.sort(arr);
  }

  public static boolean equals(int[] arr1, int[] arr2){
    if ((arr1 == null && arr2 !=null) || (arr1 != null && arr2 == null)){
      return false;
    }

    if (arr1 == null && arr2 == null){
      return true;
    }

    if (arr1.length != arr2.length){
      return false;
    }

    for (int i = 0; i < arr1.length; i ++){
      if (arr1[i] != arr2[i]){
        return false;
      }
    }

    return true;
  }


  public static void printArray(int[] arr){
    if (arr == null) {
      return;
    }


    for (int i = 0; i < arr.length; i++){
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {

    int testTime = 500000;
    int maxSize = 100;
    int maxValue = 100;

    boolean succeed = true;
    for(int i = 0; i < testTime; i++){
      int[] arr1 = generateRandomArray(maxSize, maxValue);
      int[] arr2 = copyArr(arr1);
      selectionSort(arr1);
      comparator(arr2);

      if (!equals(arr1, arr2)){
        succeed = false;
        printArray(arr1);
        printArray(arr2);
        break;
      }
    }

    System.out.println(succeed? "Nice" : "Fucking fucked!");

    int[] arr = generateRandomArray(maxSize, maxValue);
    printArray(arr);
    selectionSort(arr);
    printArray(arr);

  }




}
