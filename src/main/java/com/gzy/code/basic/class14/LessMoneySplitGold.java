package com.gzy.code.basic.class14;

import java.util.PriorityQueue;

/**
 * description: LessMoneySplitGold date: 2022/6/29 11:40
 *
 * @author: guizhenyu
 */
public class LessMoneySplitGold {

  /**
   * 一块金条切成两半，是需要花费和长度数值一样的铜板的。
   * 比如长度为20的金条，不管怎么切，都要花费20个铜板。 一群人想整分整块金条，怎么分最省铜板?
   *
   * 例如,给定数组{10,20,30}，代表一共三个人，整块金条长度为60，金条要分成10，20，30三个部分。
   *
   * 如果先把长度60的金条分成10和50，花费60; 再把长度50的金条分成20和30，花费50;一共花费110铜板。
   * 但如果先把长度60的金条分成30和30，花费60;再把长度30金条分成10和20， 花费30;一共花费90铜板。
   * 输入一个数组，返回分割的最小代价
   *
   *
   */


  public static int lessMoney1(int[] arr){

    if (null == arr || arr.length == 0){
      return 0;
    }

    return process(arr, 0);
  }

  /**
   * 暴力递归方式求解
   * 遍历所有的，可能把两个铜钱拼到一起
   *
   * @param arr 铜钱的金额数组
   * @param pre 之前的代价
   * @return
   */
  private static int process(int[] arr, int pre) {
    if (arr.length == 1){
      return  pre;
    }

    int ans = Integer.MAX_VALUE;
    for (int i = 0; i < arr.length; i++){
      for (int j = i + 1; j < arr.length; j++){
        ans = Math.min(ans, process(copyAndMerge(arr, i, j), pre + arr[i] + arr[j]));
      }
    }
    return ans;
  }

  public static int[] copyAndMerge(int[] arr, int i, int j){
    int[] ans = new int[arr.length - 1];
    int ansi = 0;
    for (int index = 0; index < arr.length; index++){
      if (index != i && index != j){
        ans[ansi++] = arr[index];
      }
    }

    ans[ansi] = arr[i] + arr[j];
    return ans;
  }




  /**
   * 贪心算法
   * 将所有的铜钱放入小根堆中，每次弹出两个，进行合并再放到小根堆中，直到堆中只剩一个
   *
   * @param arr
   * @return
   */
  public static int lessMoney2(int[] arr){

    if (null == arr || arr.length == 0){
      return 0;
    }

    PriorityQueue<Integer> heap = new PriorityQueue<>();

    for (int i = 0; i < arr.length; i++){
      heap.add(arr[i]);
    }

    int sum = 0;
    int cur = 0;
    while (heap.size() > 1){
      cur = heap.poll() + heap.poll();
      sum += cur;
      heap.add(cur);
    }
    return sum;
  }

  // for test
  public static int[] generateRandomArray(int maxSize, int maxValue) {
    int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = (int) (Math.random() * (maxValue + 1));
    }
    return arr;
  }

  public static void main(String[] args) {
    int testTime = 100000;
    int maxSize = 6;
    int maxValue = 1000;
    for (int i = 0; i < testTime; i++) {
      int[] arr = generateRandomArray(maxSize, maxValue);
      if (lessMoney1(arr) != lessMoney2(arr)) {
        System.out.println("Oops!");
      }
    }
    System.out.println("finish!");
  }


}
