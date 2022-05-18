package com.gzy.code.basic.class25;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * description: Class01_MonotonousStack date: 2022/5/1 09:00
 *
 * @author: guizhenyu
 */
public class Class01_MonotonousStack {

  /**
   *  单调栈
   */

  public static void main(String[] args) {
    int size = 10;
    int max = 20;
    int testTimes = 2000000;
    System.out.println("测试开始");

    for(int i = 0; i < testTimes; i++){

      int[] arr1 = generateRandomArrNoRepeat(size);
      int[] arr2 = generateRandomArr(size, max);

      // 求无重复数组中，数组中每个元素的左边最小值和右边最小值
      int[][] ans1 = natureWisdom(arr1);
      int[][] ans2 = monotonousStackFunction(arr1);

      if (!equals(ans1, ans2)){
        System.out.println("oops");
        printArr(arr1);

      }

      int[][] ans4 = natureWisdom(arr2);
      int[][] ans3 = monotonousStackRepeatValue(arr2);
      if (!equals(ans3, ans4)){
        System.out.println("oops");
        printArr(arr2);

      }

    }
  }

  private static int[][] monotonousStackRepeatValue(int[] arr) {
    if (null == arr || arr.length == 0) {
      return null;
    }

    int len = arr.length;
    int[][] result = new int[len][2];
    Stack<List<Integer>> monotonousStack = new Stack<List<Integer>>();

    for (int i = 0; i < len; i++) {
      while (!monotonousStack.isEmpty() &&
          arr[monotonousStack.peek().get(0)] > arr[i]) {

//        List<Integer> equalOrGreaterArrIndex = monotonousStack.peek();
//        if (equalOrGreaterArrIndex.get(0) == arr[i]) {
//          equalOrGreaterArrIndex.add(i);
//        } else {
          // 大于，要弹出
        List<Integer> equalOrGreaterArrIndex = monotonousStack.pop();
        int nearestSmallerIndex = monotonousStack.isEmpty() ? -1
            : monotonousStack.peek().get(monotonousStack.peek().size() - 1);
        for (Integer greaterIndex : equalOrGreaterArrIndex) {
          result[greaterIndex][1] = i;
          result[greaterIndex][0] = nearestSmallerIndex;
        }
//        }
      }


      if (!monotonousStack.isEmpty() &&
          arr[monotonousStack.peek().get(0)] == arr[i]){
        monotonousStack.peek().add(i);
      }else {
        List<Integer> indexArr = new ArrayList<>();
        indexArr.add(i);
        monotonousStack.push(indexArr);
      }

    }

    while (!monotonousStack.isEmpty()) {
      List<Integer> equalOrGreaterArrIndex = monotonousStack.pop();
      int nearestSmallerIndex = monotonousStack.isEmpty() ? -1
          : monotonousStack.peek().get(monotonousStack.peek().size() - 1);
      for (Integer greaterIndex : equalOrGreaterArrIndex) {
        result[greaterIndex][1] = -1;
        result[greaterIndex][0] = nearestSmallerIndex;
      }
    }
    return result;
  }

  private static int[][] monotonousStackFunction(int[] arr) {
    if (null == arr || arr.length == 0){
      return null;
    }
    int len = arr.length;
    int[][] result = new int[len][2];
    Stack<Integer> monotonousStack = new Stack<Integer>();
    for (int i = 0; i < len; i++){
      while (!monotonousStack.isEmpty() &&
      arr[monotonousStack.peek()] > arr[i]){

        int index = monotonousStack.pop();

        result[index][0] = monotonousStack.isEmpty() ? -1 : monotonousStack.peek();
        result[index][1] = i;
      }
      monotonousStack.push(i);
    }

    while (!monotonousStack.isEmpty()){
      Integer index = monotonousStack.pop();
      result[index][1] = -1;
      result[index][0] = monotonousStack.isEmpty() ? -1 : monotonousStack.peek();
    }

    return result;
  }

  private static int[][] natureWisdom(int[] arr) {
    if (null == arr || arr.length == 0){
      return null;
    }
    int len = arr.length;
    int[][] result = new int[len][2];
    for (int i = 0; i < len; i++){
      int leftMinIndex = -1;
      int rightMinIndex = -1;
      int leftIndex = i - 1;
      int rightIndex = i + 1;
      while (leftIndex >= 0){
        if (arr[leftIndex] < arr[i] ){
          leftMinIndex = leftIndex;
          break;
        }
        leftIndex--;
      }

      while (rightIndex < len){
        if (arr[rightIndex] < arr[i]){
          rightMinIndex = rightIndex;
          break;
        }

        rightIndex++;
      }
      result[i][0] = leftMinIndex;
      result[i][1] = rightMinIndex;

    }
    return result;
  }

  private static boolean equals(int[][] ans1, int[][] ans2) {
    if ((ans1 == null && ans2 != null) || (ans2 == null && ans1 != null)
        || ans1.length != ans2.length || ans1[0].length != ans2[0].length){
      return false;
    }

    int height = ans1.length;
    int width = ans1[0].length;
    for (int h = 0; h < height; h++){
      for (int w = 0; w < width; w++){
        if (ans1[h][w] != ans2[h][w]){
          return false;
        }
      }
    }

    return true;
  }

  private static void printArr(int[] arr){
    if (null == arr || arr.length == 0){
      return;
    }
    for (int i : arr) {
      System.out.print(i + " ");
    }
    System.out.println();
  }

  private static int[] generateRandomArrNoRepeat(int size) {

    int len = (int)(Math.random() * size) + 1;
    int[] arr = new int[len];
    for (int i = 0; i < len; i++){
      arr[i]= i;
    }

    for (int i = 0; i < len; i++){

      int swapIndex = (int)(len * Math.random());

      int swapValue = arr[swapIndex];
      arr[swapIndex] = arr[i];
      arr[i] = swapValue;

    }
    return arr;
  }


  private static int[] generateRandomArr(int size, int value){
    int len = (int)(Math.random() * size) + 1;
    int[] arr = new int[len];

    for (int i = 0; i < len; i++){
      arr[i] = (int)(Math.random() * value)  - (int)(Math.random() * value);
    }
    return arr;
  }

}
