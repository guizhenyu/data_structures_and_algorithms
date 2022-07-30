package com.gzy.code.basic.class20;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * description: Coffee date: 2022/7/28 15:28
 * // 题目
 * // 数组arr代表每一个咖啡机冲一杯咖啡的时间，每个咖啡机只能串行的制造咖啡。
 * // 现在有n个人需要喝咖啡，只能用咖啡机来制造咖啡。
 * // 认为每个人喝咖啡的时间非常短，冲好的时间即是喝完的时间。
 * // 每个人喝完之后咖啡杯可以选择洗或者自然挥发干净，只有一台洗咖啡杯的机器，只能串行的洗咖啡杯。
 * // 洗杯子的机器洗完一个杯子时间为a，任何一个杯子自然挥发干净的时间为b。
 * // 四个参数：arr, n, a, b
 * // 假设时间点从0开始，返回所有人喝完咖啡并洗完咖啡杯的全部过程结束后，至少来到什么时间点。
 * @author: guizhenyu
 */
public class Coffee {


  // for test
  public static int[] randomArray(int len, int max) {
    int[] arr = new int[len];
    for (int i = 0; i < len; i++) {
      arr[i] = (int) (Math.random() * max) + 1;
    }
    return arr;
  }

  // for test
  public static void printArray(int[] arr) {
    System.out.print("arr : ");
    for (int j = 0; j < arr.length; j++) {
      System.out.print(arr[j] + ", ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    int len = 10;
    int max = 10;
    int testTime = 10;
    System.out.println("测试开始");
    for (int i = 0; i < testTime; i++) {
      int[] arr = randomArray(len, max);
      int n = (int) (Math.random() * 7) + 1;
      int a = (int) (Math.random() * 7) + 1;
      int b = (int) (Math.random() * 10) + 1;
      int ans1 = violenceRecursive(arr, n, a, b);
      int ans2 = minTime1(arr, n, a, b);
      int ans3 = minTime2(arr, n, a, b);
      if (ans1 != ans2 || ans2 != ans3) {
        printArray(arr);
        System.out.println("n : " + n);
        System.out.println("a : " + a);
        System.out.println("b : " + b);
        System.out.println(ans1 + " , " + ans2 + " , " + ans3);
        System.out.println("===============");
        break;
      }
    }
    System.out.println("测试结束");

  }

  /**
   *  贪心算法 + 动态规划
   *
   * @param arr 各个咖啡机对应的冲洗时间
   * @param n    喝咖啡的人
   * @param a    咖啡机洗咖啡的时间
   * @param b    自然风干的时间
   * @return
   */
  private static int minTime2(int[] arr, int n, int a, int b) {
    PriorityQueue<CaffeMachineTime> smallHeap = new PriorityQueue<>(new CaffeMachComp());
    for (int i = 0; i < arr.length; i++){
      smallHeap.add(new CaffeMachineTime(arr[i], 0));
    }

    int[] drinkTime = new int[n];
    for (int i = 0; i < n; i++){
      CaffeMachineTime machineTime = smallHeap.poll();
      drinkTime[i] = machineTime.workTime + machineTime.cost;
      machineTime.workTime = drinkTime[i];
      smallHeap.add(machineTime);
    }

    // todo: 这边用动态规划确定维度： 原来直接暴力递归有3个可变参数，也就是3个维度 index, washLine, time
    //       而且washLine跟time不是线性,  但其实 washLine 也是决定time参数
    //       所以这边可以先计算下全部机洗最大时间的值 maxTime, 然后由 maxTime跟 index,共同组成动态规划的维度

    int maxTime = 0;
    for (int i = 0; i < n; i++){
      maxTime = Math.max(maxTime, drinkTime[i]) + a;
    }

    int[][] dp = new int[n + 1][maxTime + 1];
    for (int index = n - 1; index >= 0; index--){
      for (int time = 0; time <= maxTime; time++){

        // 机洗的时间
        int selfCleanEnd = Math.max(drinkTime[index], time) + a;
        if (selfCleanEnd > maxTime){
          // 超出最大值了越界了
          break;
        }
        // 依赖的 index + 1位置上的对应的时间
        int washTime = dp[index + 1][selfCleanEnd];
        // 两者取最大的
        int p1 = Math.max(selfCleanEnd, washTime);

        // 自然风干的时间
        int selfClean2 = drinkTime[index] + b;
        // 由于是自然风干的，所以他依赖的下一个位置的杯子开始的风干时间就是对应的 当前时间（他不需要等）
        int washTIme2 = dp[index + 1][time];
        int p2 = Math.max(selfClean2, washTIme2);

        dp[index][time] = Math.min(p1, p2);
      }
    }

    return dp[0][0];



  }


  /**
   * 优化版的暴力递归
   * todo: 直接暴力递归，安排打咖啡机制作咖啡的时间可以优化成贪心算法
   *       采用小根堆的方式保存每个咖啡机下次工作的时间，每次堆顶的咖啡机就是离当前时间最近的
   *
   * @param arr  各个咖啡机对应的冲洗时间
   * @param n    喝咖啡的人
   * @param a    咖啡机洗咖啡的时间
   * @param b    自然风干的时间
   * @return
   */
  private static int minTime1(int[] arr, int n, int a, int b) {
    PriorityQueue<CaffeMachineTime> smallHeap = new PriorityQueue<>(new CaffeMachComp());
    for (int i = 0; i < arr.length; i++){
      smallHeap.add(new CaffeMachineTime(arr[i], 0));
    }

    int[] drinkTime = new int[n];
    for (int i = 0; i < n; i++){
      CaffeMachineTime machineTime = smallHeap.poll();
      drinkTime[i] = machineTime.workTime + machineTime.cost;
      machineTime.workTime = drinkTime[i];
      smallHeap.add(machineTime);
    }

    return processWash(drinkTime, 0, 0, 0, a, b);



  }

  public static class CaffeMachineTime{
    int cost;
    int workTime;

    public CaffeMachineTime(int c, int t){
      cost = c;
      workTime = t;
    }
  }


  public static class CaffeMachComp implements Comparator<CaffeMachineTime> {

    @Override
    public int compare(CaffeMachineTime o1, CaffeMachineTime o2) {
      return (o1.cost + o1.workTime) - (o2.cost + o2.workTime);
    }
  }
  /**
   * 暴力递归
   *
   * @param makeT 各个咖啡机对应的冲咖啡的时间
   * @param n    喝咖啡的人
   * @param a    咖啡机洗咖啡的时间
   * @param b    自然风干的时间
   * @return
   */
  private static int violenceRecursive(int[] makeT, int n, int a, int b) {
    /**
     * 思路：总共分两步，制作咖啡 + 洗咖啡两个步骤
     *
     */
    // 用于记录每个咖啡机制作能制作咖啡的时刻
    int[] workTime = new int[makeT.length];
    // 每个人喝咖啡的时刻
    int[] drinkTime = new int[n];

    return processMake(workTime, drinkTime, 0, n, a, b, makeT);
  }

  /**
   * 所有咖啡制作完 + 风干完
   *
   * @param workTime 每个咖啡机能够制作咖啡的时间点
   * @param drinkTime 每个人对应的喝咖啡的时间点
   * @param i 当前第几个人喝咖啡
   * @param n 总共几个人
   * @param a 咖啡机风干的时间
   * @param b 自然风干的时间
   * @param makeT
   * @return
   */
  private static int processMake(int[] workTime, int[] drinkTime, int i, int n, int a, int b, int[] makeT) {
    if (i == n){
      int[] drinkSorted = Arrays.copyOf(drinkTime, i);
      Arrays.sort(drinkSorted);
      return processWash(drinkSorted, 0, 0,0, a, b);
    }

    int time = Integer.MAX_VALUE;
    for (int index = 0; index < makeT.length; index++){
      int canWorkTime = workTime[index];
      int workCost = makeT[index];
      drinkTime[i] = canWorkTime + workCost;
      workTime[index] = canWorkTime + workCost;
      time = Math.min(time, processMake(workTime, drinkTime, i + 1, n, a, b, makeT));
      // 现场恢复
      drinkTime[i] = 0;
      workTime[index] = canWorkTime;
    }

    return time;
  }

  /**
   * 喝完咖啡的时间点已经确定，下面确定什么时间点能够最快把所有的咖啡杯风干完
   *
   * @param drinkTime 喝完咖啡的时间
   * @param index 当前到第几个杯子，决定是机器洗，还是自然风干
   * @param washLine 机器下一次能洗的时间
   * @param time 前一个杯子风干完的时间点
   * @param a 机器洗杯子的时间
   * @param b 自然风干的时间
   * @return
   */
  private static int processWash(int[] drinkTime, int index, int washLine, int time,  int a, int b) {
    if (index == drinkTime.length){
      return time;
    }

    // 机洗
    int machineTime = Math.max(drinkTime[index],washLine) + a;
    int timeWash = processWash(drinkTime, index + 1, machineTime, Math.max(time, machineTime), a, b);
    // 自然风干
    int freeTime = drinkTime[index] + b;
    int timeFree = processWash(drinkTime, index + 1, washLine, Math.max(time, freeTime), a, b);

    return Math.min(timeWash, timeFree);
  }


}
