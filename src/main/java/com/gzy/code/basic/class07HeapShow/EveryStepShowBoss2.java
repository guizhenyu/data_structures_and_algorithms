package com.gzy.code.basic.class07HeapShow;


/**
 * description: EveryStepShowBoss2 date: 2022/6/16 17:34
 *
 * @author: guizhenyu
 */
public class EveryStepShowBoss2 {




  public static Data randomData(int maxValue, int maxLen){
    int len = (int)(Math.random() * maxLen) + 1;
    int[] arr = new int[len];
    boolean[] op = new boolean[len];
    for(int i = 0; i < len; i++){
      arr[i] = (int)(Math.random() * maxValue );
      op[i] = Math.random() > 0.5 ? true : false;
    }

    return new Data(arr, op);
  }


  public static class Data{

    /**
     *  arr:
     *    下标表示时间点
     *    具体的金额表示消费金额
     *  op:
     *    表示是买商品还是退款
     */
    public int[] arr;
    public boolean[] op;

    public Data(int[] a, boolean[] o){
      arr = a;
      op = o;
    }
  }


  public static class Customer{

    /**
     * 客户的id
     *
     */
    public int id;

    /**
     * 客户的购买数量
     * 负数代表退款
     *
     */
    public int buy;

    /**
     * 进入候选池 或者 抽奖池的 时间点，方便排列
     *
     */
    public int enterTime;

    public Customer(int v, int b, int enterTime){
      id = v;
      buy = b;
      enterTime = enterTime;
    }
  }
}
