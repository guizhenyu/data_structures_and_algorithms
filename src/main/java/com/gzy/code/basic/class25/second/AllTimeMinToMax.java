package com.gzy.code.basic.class25.second;


import java.util.Stack;

/**
 * description: AllTimeMinToMax date: 2022/8/19 15:46
 *
 * @author: guizhenyu
 */
public class AllTimeMinToMax {


  /**
   * 给定一个只包含正数的数组arr，arr中任何一个子数组sub，
   * 一定都可以算出(sub累加和)* (sub中的最小值)是什么，
   * 那么所有子数组中，这个值最大是多少？
   */



  public static void main(String[] args) {

    int testTime = 100000;
    int maxLen = 30;
    int maxValue = 30;

    for (int i = 0; i < testTime; i++) {
      int[] arr = generateRandomArr(maxLen, maxValue);

      long ans1 = natureWisdom(arr);
      long ans2 = monotonousStack(arr);
      if (ans1 != ans2) {
        int ans3 = natureWisdom1(arr);
        printArr(arr);
        System.out.println(ans1);
        System.out.println(ans2);

      }
    }

  }

  private static int natureWisdom1(int[] arr) {
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

  private static long monotonousStack(int[] arr) {
    if (arr == null || arr.length < 1){
      return 0;
    }

    int N = arr.length;
    long[] sums = new long[N];
    sums[0] = arr[0];
    for (int i = 1; i < N; i++){
      sums[i] = sums[i - 1] + arr[i];
    }
    long ans = 0;
    Stack<Integer> stack = new Stack<Integer>();
    for (int i = 0; i < N; i++){
      while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]){
        Integer index = stack.pop();
        int left = stack.isEmpty()? - 1 : stack.peek();
        int right = i - 1;
        ans = Math.max(ans, (sums[right] - (left == -1? 0 : sums[left])) * arr[index]);
      }
      stack.push(i);
    }

    while (!stack.isEmpty()){
      Integer index = stack.pop();
      int left = stack.isEmpty()? - 1 : stack.peek();
      int right = N - 1;
      ans = Math.max(ans, (sums[right] - (left == -1? 0 : sums[left])) * arr[index]);
    }



    return ans;
  }

  private static int natureWisdom(int[] arr) {
    if (arr == null || arr.length < 1){
      return 0;
    }

    int N = arr.length;
    int L = 0;
    int ans = 0;


    while (L < N){
      int R = L;
      while (R < N){
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for (int i = L; i <= R; i++){
          sum += arr[i];
          min = Math.min(min, arr[i]);
        }

        ans = Math.max(sum * min, ans);
        R++;
      }
      L++;
    }

    return ans;
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

  private static int[] generateRandomArr(int maxLen, int maxValue) {
    int len = random(maxLen);
    int[] arr = new int[len];
    for (int i = 0; i < len; i++){
      arr[i] = random(maxValue);
    }
    return arr;
  }

  private static int random(int value){
    return (int)(Math.random() * value) + 1;
  }


}
