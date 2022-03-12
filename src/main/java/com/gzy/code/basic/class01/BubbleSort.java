package com.gzy.code.basic.class01;

import static com.gzy.code.basic.class01.SelectionSort.printArray;
import static com.gzy.code.basic.class01.SelectionSort.selectionSort;

import java.util.Arrays;

/**
 * description: BubbleSort date: 2021/12/20 3:27 下午
 *
 * @author: guizhenyu
 */
public class BubbleSort {

  public static void bubbleSort(int[] arr){
    if (arr == null || arr.length == 0){
      return;
    }


    for (int i = 0; i < arr.length - 1; i ++){
      for (int j = i + 1; j < arr.length; j ++){
        if (arr[i] > arr[j]){
          swap(arr, i, j);
        }
      }
    }
  }


  public static void swap(int[] arr, int i, int j){
    arr[i] = arr[i] ^ arr[j];
    arr[j] = arr[i] ^ arr[j];
    arr[i] = arr[i] ^ arr[j];
  }


  public static void comparator(int[] arr){
    Arrays.sort(arr);
  }

  public static void main(String[] args) {
    /**
     * 验证我们写的算法排序正确性
     * 1. 最简单的方法实现比较功能（不考虑时间复杂度和空间复杂度）
     * 2. 然后随机生成数组，通过两种排序方法验证，自己写的算法正确性
     * 3. 通过大批量次数去验证
     *
     */

    int testTime = 500000;
    int maxSize = 100;
    int maxValue = 100;


    boolean succeed = true;
    for (int i = 0; i < testTime; i++){

      int[] arr = generateArrayRandom(maxSize, maxValue);
      int[] arr1 = copyArray(arr);

      bubbleSort(arr);
      comparator(arr1);

      if(!equals(arr, arr1)){
        succeed = false;
        printArray(arr);
        printArray(arr1);
        break;
      }
    }

    int[] arr = generateArrayRandom(maxSize, maxValue);
    System.out.println(succeed?"Nice!":"Fucking fucked!");
    printArray(arr);
    selectionSort(arr);
    printArray(arr);

  }

  private static boolean equals(int[] arr, int[] arr1) {

    if ((arr == null && arr1 != null) || (arr != null && arr1 == null)){
      return false;
    }

    if (arr == null && arr1 == null){
      return true;
    }

    if(arr.length != arr.length){
      return false;
    }


    for (int i = 0; i < arr.length; i ++){

      if(arr[i] != arr1[i]){
        return false;
      }
    }

    return true;
  }

  private static int[] copyArray(int[] arr1) {
    if (null == arr1){
      return null;
    }

    int[] copyArr = new int[arr1.length];

    for (int i = 0; i < arr1.length; i++){
      copyArr[i] = arr1[i];
    }

    return copyArr;
  }

  private static int[] generateArrayRandom(int maxSize, int maxValue) {


    int arrSize = (int)((maxSize + 1) * Math.random());

    int[] arr = new int[arrSize];

    for (int i = 0; i < arrSize; i++){
      arr[i] = (int)((maxValue + 1) * Math.random()) - (int)((maxValue) * Math.random());
    }

    return arr;

  }

}
