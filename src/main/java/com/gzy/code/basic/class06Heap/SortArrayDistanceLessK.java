package com.gzy.code.basic.class06Heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * description: SortArrayDistanceLessK date: 2022/1/4 8:18 下午
 * 给定一个数组，数组是比较有序，即将数组中的每一个数字排序，每一个数字最多移动k个位置
 *
 * @author: guizhenyu
 */
public class SortArrayDistanceLessK {


  public static void sortArrayDistanceLessK(int[] arr, int k){
    if ( k == 0){
      return;
    }

    PriorityQueue<Integer> heap = new PriorityQueue<>();
    int index = 0;
    // 保证了只用了 <= k个堆空间
    for(;index <= Math.min(arr.length - 1, k - 1); index++){
      heap.add(arr[index]);
    }
    // 现在从堆中取数据，就是从小到大
    int i = 0;
    for (;index < arr.length ; i++, index++){
      heap.add(arr[index]);
      arr[i] = heap.poll();
    }
    while (!heap.isEmpty()){
      arr[i++] = heap.poll();
    }
  }


  /**
   * 主函数实现对数器
   *
   * @param arr
   */
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
    System.out.println("test begin");
    int testTime = 500000;
    int maxSize = 100;
    int maxValue = 100;
    boolean succeed = true;
    for (int i = 0; i < testTime; i++) {
      int k = (int) (Math.random() * maxSize) + 1;
      int[] arr = generateRandomArray(maxSize, maxValue, k);
      int[] arr1 = copyArray(arr);
      int[] arr2 = copyArray(arr);
      sortArrayDistanceLessK(arr1, k);
      arraySort(arr2, k);
      if (!isEqual(arr1, arr2)) {
        succeed = false;
        System.out.println("K : " + k);
        printArray(arr);
        printArray(arr1);
        printArray(arr2);
        break;
      }
    }
    System.out.println(succeed ? "Nice!" : "Fucking fucked!");
  }


  public static void arraySort(int[] arr, int k){
    Arrays.sort(arr);
  }

  // for test
  public static int[] randomArrayNoMoveMoreK(int maxSize, int maxValue, int K) {
    int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
    }
    // 先排个序
    Arrays.sort(arr);
    // 然后开始随意交换，但是保证每个数距离不超过K
    // swap[i] == true, 表示i位置已经参与过交换
    // swap[i] == false, 表示i位置没有参与过交换
    boolean[] isSwap = new boolean[arr.length];
    for (int i = 0; i < arr.length; i++) {
      int j = Math.min(i + (int) (Math.random() * (K + 1)), arr.length - 1);
      if (!isSwap[i] && !isSwap[j]) {
        isSwap[i] = true;
        isSwap[j] = true;
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
      }
    }
    return arr;
  }
  public static int[] generateRandomArray(int maxSize, int maxValue, int k){
    int[] arr = new int[(int) (Math.random() * (maxSize))];
//    int k = (int)(Math.random() * (maxSize + 1));
    for (int i = 0; i < arr.length - 1; i++){
      arr[i] = (int)(Math.random()*(maxValue + 1)) - (int)(Math.random()*maxValue);
    }
    Arrays.sort(arr);

    boolean[] isSwap = new boolean[arr.length];
    for (int i = 0; i< arr.length - 1; i++){
      int j = Math.min((int)(Math.random() * (k + 1)) + i, arr.length - 1);

      if (!isSwap[j] && !isSwap[i]){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
        isSwap[j] = true;
        isSwap[i] = true;
      }
    }

    return arr;
  }


}
