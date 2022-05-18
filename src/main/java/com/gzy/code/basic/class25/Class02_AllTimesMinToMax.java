package com.gzy.code.basic.class25;

import java.util.Stack;

/**
 * description: Class01_MonotonousStack date: 2022/5/1 07:55
 *
 * @author: guizhenyu
 */
public class Class02_AllTimesMinToMax {

  /**
   * 给定一个只包含正数的数组arr，arr中任何一个子数组sub，
   * 一定都可以算出(sub累加和)* (sub中的最小值)是什么，
   * 那么所有子数组中，这个值最大是多少？
   */
  public static void main(String[] args) {

    int testTime = 100000;
    int maxLen = 30;
    int maxValue = 30;

    for (int i = 0; i < testTime; i++){
      int[] arr = generateRandomArr(maxLen, maxValue);

      int ans1 = natureWisdom(arr);
      int ans2 = monotonousStack(arr);
      if (ans1 != ans2){
        printArr(arr);
        System.out.println(ans1);
        System.out.println(ans2);

      }
    }

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


  private static int monotonousStack(int[] arr) {
    int len = arr.length;
    int maxValue = Integer.MIN_VALUE;

    int[] sumArr = new int[len];
    sumArr[0] = arr[0];
    for (int i = 1; i < len; i++){
      sumArr[i] = sumArr[i - 1] + arr[i];
    }

    Stack<Integer> stack = new Stack<Integer>();

    for (int i = 0; i < len; i++){
      while (!stack.isEmpty() &&
        arr[stack.peek()] >= arr[i]){
        int index = stack.pop();
        maxValue = Math.max(maxValue, (stack.isEmpty()? sumArr[i - 1] : (sumArr[i - 1] - sumArr[stack.peek()])) * arr[index]);
      }
      stack.push(i);
    }

    while (!stack.isEmpty()){
      int index = stack.pop();
      maxValue = Math.max(maxValue, (stack.isEmpty()? sumArr[len - 1] : (sumArr[len - 1] - sumArr[stack.peek()])) * arr[index]);
    }

    return maxValue;
  }

  private static int natureWisdom(int[] arr) {
    int len = arr.length;

    int maxValue = Integer.MIN_VALUE;

    for (int l = 0; l < len; l++){

      for (int r = l; r < len; r++){
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for (int j = l; j <= r; j++){
          sum += arr[j];
          min = Math.min(arr[j], min);
        }

        maxValue = Math.max(sum * min, maxValue);
      }

    }

    return maxValue;
  }

  private static int[] generateRandomArr(int maxLen, int maxValue) {
    int len = random(maxLen);
    int[] arr = new int[len];
    for (int i = 0; i < len; i++){
      arr[i] = random(maxValue);
    }
    return arr;
  }

  private static int random(int value){
    return (int)Math.random() * value + 1;
  }
}
