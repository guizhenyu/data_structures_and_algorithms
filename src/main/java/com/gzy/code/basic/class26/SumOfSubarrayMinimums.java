//package com.gzy.code.basic.class26;
//
///**
// * description: SumOfSubarrayMinimums date: 2022/9/15 17:15
// * 给定一个整数数组 arr，找到 min(b) 的总和，其中 b 的范围为 arr 的每个（连续）子数组。
// * @author: guizhenyu
// */
//public class SumOfSubarrayMinimums {
//
//
//  public static void main(String[] args) {
//    int maxLen = 100;
//    int maxValue = 50;
//    int testTime = 100000;
//    System.out.println("测试开始");
//    for (int i = 0; i < testTime; i++) {
//      int len = (int) (Math.random() * maxLen);
//      int[] arr = randomArray(len, maxValue);
//      int ans1 = subArrayMinSum1(arr);
//      int ans2 = subArrayMinSum2(arr);
//      int ans3 = sumSubarrayMins(arr);
//      if (ans1 != ans2 || ans1 != ans3) {
//        printArray(arr);
//        System.out.println(ans1);
//        System.out.println(ans2);
//        System.out.println(ans3);
//        System.out.println("出错了！");
//        break;
//      }
//    }
//    System.out.println("测试结束");
//  }
//
//  private static int subArrayMinSum1(int[] arr) {
//
//
//  }
//
//  public static int[] randomArray(int len, int maxValue) {
//    int[] ans = new int[len];
//    for (int i = 0; i < len; i++) {
//      ans[i] = (int) (Math.random() * maxValue) + 1;
//    }
//    return ans;
//  }
//
//  public static void printArray(int[] arr) {
//    for (int i = 0; i < arr.length; i++) {
//      System.out.print(arr[i] + " ");
//    }
//    System.out.println();
//  }
//
//
//}
