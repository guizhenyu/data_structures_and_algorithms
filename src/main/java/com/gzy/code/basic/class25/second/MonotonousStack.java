package com.gzy.code.basic.class25.second;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * description: MonotonousStack date: 2022/8/15 09:24
 *
 * 给定一个只包含正数的数组arr，arr中任何一个子数组sub，
 * 一定都可以算出(sub累加和 )* (sub中的最小值)是什么，
 * 那么所有子数组中，这个值最大是多少？
 *
 * @author: guizhenyu
 */
public class MonotonousStack {


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

  private static int[][] monotonousStackRepeatValue(int[] arr1) {
    if (arr1 == null || arr1.length < 1){
      return null;
    }
    int N = arr1.length;
    int[][] result = new int[N][2];
    Stack<List<Integer>> stack = new Stack<>();
    List<Integer> list0 = new ArrayList<>();
    list0.add(0);
    stack.push(list0);
    for (int i = 1; i < N; i++){
      while (!stack.isEmpty() && arr1[stack.peek().get(0)] > arr1[i]){
        List<Integer> lasts = stack.pop();
        // 弹出
        List<Integer> befores = stack.isEmpty()? null : stack.peek();

        int lessIndex = befores == null || befores.isEmpty()? -1 : befores.get(befores.size() - 1);
        for (Integer index : lasts){
          result[index][0] = lessIndex;
          result[index][1] = i;
        }
      }

      if (!stack.isEmpty() && arr1[stack.peek().get(0)] == arr1[i]){
        List<Integer> lasts = stack.peek();
        // 补进去
        lasts.add(i);
      }else {
        // 新建
        List<Integer> list = new ArrayList<>();
        list.add(i);
        stack.push(list);
      }

    }

    while (!stack.isEmpty()){
      List<Integer> indexs = stack.pop();
      List<Integer> befores = stack.isEmpty()? null : stack.peek();

      int lessIndex = befores == null || befores.isEmpty()? -1 : befores.get(befores.size() - 1);
      for (Integer index : indexs){
        result[index][0] = lessIndex;
        result[index][1] = -1;
      }
    }



    return result;

  }

  private static int[][] monotonousStackFunction(int[] arr1) {
    if (arr1 == null || arr1.length < 1){
      return null;
    }

    int N = arr1.length;
    int[][] result = new int[N][2];

    Stack<Integer> stack = new Stack<>();

    for (int i = 0; i < N; i++){
      while (!stack.isEmpty() && arr1[i] < arr1[stack.peek()]){

        Integer pop = stack.pop();

        result[pop][0] = stack.isEmpty()? -1 : stack.peek();
        result[pop][1] = i;
      }
      stack.push(i);
    }

    while (!stack.isEmpty()){
      Integer pop = stack.pop();
      result[pop][0] = stack.isEmpty()? -1 : stack.peek();
      result[pop][1] = -1;
    }


    return result;
  }

  private static int[][] natureWisdom(int[] arr1) {
    if (arr1 == null || arr1.length < 1){
      return null;
    }
    int N = arr1.length;
    int[][] result = new int[N][2];

    for (int i = 0; i < N; i++){
      int[] ans = new int[2];
      ans[0] = -1;
      ans[1] = -1;
      if (i > 0){
        for (int l = i - 1; l >= 0; l--){
          if (arr1[l] < arr1[i]){
            ans[0] = l;
            break;
          }
        }
      }
      if (i < N - 1){
        for (int r = i + 1; r < N; r++){
          if (arr1[r] < arr1[i]){
            ans[1] = r;
            break;
          }
        }
      }
      result[i] = ans;
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
