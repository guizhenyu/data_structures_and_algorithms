package com.gzy.code.basic.class06Heap;


import java.util.Arrays;

/**
 * description: MyHeapSort1 date: 2022/6/15 13:56
 *
 * @author: guizhenyu
 */
public class MyHeapSort1 {
  /**
   * 堆是一种完全二叉树的结构，由数组构成，从下标索引1开始
   * 索引下标为i， 他的父节点的下标索引是 (i - 1) / 2, 左子节点的索引是2i + 1, 右子节点的索引是2i + 2
   * 堆只有大根堆和小根堆
   * 大根堆就是最大的数是第一个，小根堆是最小的数是第一个
   * 大根堆：
   *       每次新增时，都是把数放到最末尾，然后跟他的父节点比较，如果大于，就直接交换（交换后继续重复该步骤），否则新增结束。
   *       弹出时，第一个数跟最后一个数进行交换，然后从第一个数开始往下比较，跟最大的子节点比较，如果小于就交换，交换后继续往下面比较
   *       
   */

  public static void main(String[] args) {
    int testTime = 10000;
    int maxValue = 100;
    int maxSize = 50;
    for (int i = 0; i < testTime; i++){
      int size = (int)(Math.random() * maxSize) + 1;
      int[] arr = generateRandomArray(size, maxValue);
      int[] arr1 = copy(arr);
      int[] arr2 = copy(arr);
      Arrays.sort(arr1);
      heapSort(arr2);
      if (!equals(arr1, arr2)){
        System.out.println("Oops !");
      }
      
    }
    
    
  }

  private static void heapSort(int[] arr) {
    if (arr.length == 1){
      return;
    }

    for (int i = 0; i < arr.length; i++){
      heapInsert(arr, i);
    }

    int heapSize = arr.length;

    while (heapSize > 0){
      swap(arr, 0, --heapSize);
      heapfy(arr, 0, heapSize);

    }



  }

  private static void heapInsert(int[] arr, int i) {

    while (arr[i] > arr[(i - 1) / 2]){
      swap(arr, i, (i - 1) / 2);
      i = (i - 1) / 2;
    }

  }

  private static void heapfy(int[] arr, int index, int heapSize){
    int left = 2 * index + 1;
    while (left < heapSize){

      int largest = left + 1 >= heapSize? left : arr[left] > arr[left + 1]? left : left + 1;
      largest = arr[index] > arr[largest]? index : largest;
      if (largest == index){
        return;
      }
      swap(arr, index, largest);
      index = largest;
      left = 2 * index + 1;

    }

  }


  public static void swap(int[] arr, int index1, int index2){

    int temp = arr[index1];
    arr[index1] = arr[index2];
    arr[index2] = temp;
  }

  private static int[] copy(int[] array) {
    if (array == null){
      return null;
    }

    int[] res = new int[array.length];
    for (int i = 0; i < array.length; i++) {
      res[i] = array[i];
    }
    return res;
  }

  public static int[] generateRandomArray(int size, int value){

    int[] arr = new int[(int) (Math.random() * (size + 1))];

    for (int i = 0; i < arr.length; i++){
      arr[i] = (int)((value + 1) * Math.random()) - (int) (value * Math.random());
    }

    return arr;
  }
  /**
   * 比较两个数组是否相等
   *
   * @param array
   * @param array1
   * @return
   */
  private static boolean equals(int[] array, int[] array1) {
    // 首先如果两个都是null的情况
    if (null == array && null == array1){
      return true;
    }
    // 一个为null, 一个不为null
    if ((null == array && null != array1) ||
        null != array && null == array1){
      return false;
    }

    // 两个都不为null
    if (array.length != array1.length){
      return true;
    }

    for (int i = 0; i < array.length; i++){
      if (array[i] != array1[i]){
        return false;
      }
    }
    return true;
  }

}
