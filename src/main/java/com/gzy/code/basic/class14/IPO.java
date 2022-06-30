package com.gzy.code.basic.class14;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * description: IPO date: 2022/6/30 09:02
 *
 * @author: guizhenyu
 */
public class IPO {
  /**
   * 条件：
   * 1. 启动资金 initialCapital
   * 2. 有许多投资项目，每个投资项目需要的启动资金为cost, 对应的收益为earnings
   * 3. 只能投资 m 个项目，求最大收益
   *
   */


  /**
   *
   * @param K 最多做k个项目
   * @param W 启动资金
   * @param profits 项目对应的利润数组
   * @param capital 项目对应的成本数组
   * @return
   */
  public static int findMaximizedCapital(int K, int W, int[] profits, int[] capital){
    PriorityQueue<Program> minCostComp = new PriorityQueue<>(new MinCostComparator());
    PriorityQueue<Program> maxProfitComp = new PriorityQueue<>(new MaxProfitComparator());
    int result = 0;
    for (int i = 0; i < profits.length; i++){
      minCostComp.add(new Program(profits[i], capital[i]));
    }

    while (K > 0 && (!minCostComp.isEmpty() || !maxProfitComp.isEmpty())){
      while (!minCostComp.isEmpty() && minCostComp.peek().c <= W){
        Program poll = minCostComp.poll();
        maxProfitComp.add(poll);
        W -= poll.c;
      }

      if (maxProfitComp.isEmpty()){
        break;
      }

      Program income = maxProfitComp.poll();
      K--;
      result += income.p;
      W += income.p;
    }


    return result;
  }


  public static  class Program{

    public int p;
    public int c;

    public Program(int p, int c){
      this.c = c;
      this.p = p;
    }
  }

  public static class MinCostComparator implements Comparator<Program>{

    @Override
    public int compare(Program o1, Program o2) {
      return o1.c - o2.c;
    }
  }

  public static class MaxProfitComparator implements Comparator<Program>{

    @Override
    public int compare(Program o1, Program o2) {
      return o2.p - o1.p;
    }
  }

}
