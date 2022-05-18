package com.gzy.code.basic.class24;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * description: MinCoinsOnePaper date: 2022/4/28 20:52
 *
 * @author: guizhenyu
 */
public class MinCoinsOnePaper {

  /**
   *  给定一个数组 arr[1,5,10,1,20,5,10,100,50,15] 代表不同面值的货币，有重复的
   *  给定一个数： aim (例如 87)
   *  求 从数组中选取N张货币的值正好等于 aim, N的最小值
   */


  public static void main(String[] args) {
    int testTime = 10000;

    // 货币数
    int maxArrLen = 20;
    // 货币的最大面值
    int maxValue = 30;
    // aim的最大值
    int aimMax = 30;


    for (int i = 0; i < testTime; i++){

      int[] arr = generate(maxArrLen, maxValue);
      int aim = randomByValue(aimMax);
      int ans1 = natureWisdom(arr, aim);
      int ans2 = natureWisdom1(arr, aim);
      int ans3 = dp1(arr, aim);
      int ans4 = dp2(arr, aim);
      int ans5 = dp3(arr, aim);
      if (ans1 != ans2 || ans1 != ans3 || ans2 != ans4
          || ans2 != ans5){
        System.out.println("oops");
        break;
      }
    }
  }

  private static int dp1(int[] arr, int aim) {
    int N = arr.length;
    int[][] dp = new int[N + 1][aim + 1];
    dp[N][0] = 0;
    for (int i = 1; i <= aim; i++){
      dp[N][i] = Integer.MAX_VALUE;
    }

    for (int index = N - 1; index >=0; index--){
      for (int rest = 0; rest <= aim; rest++){
        int p1 =dp[index + 1][rest];
        int p2 = Integer.MAX_VALUE;
        if (rest >= arr[index]){
          int next =dp[index + 1][rest - arr[index]];
          if (next != Integer.MAX_VALUE){
            p2 = 1 + next;
          }
        }
        dp[index][rest] =  Math.min(p1, p2);
      }
    }

    return dp[0][aim];
  }

  private static int natureWisdom1(int[] arr, int aim){
    Info info = getInfo(arr);
    int[] counts = info.getCount();
    int[] values = info.getValues();
    return process1(values, counts, 0, aim);
  }

  private static int process1(int[] values, int[] counts, int index, int rest) {

    if (index == values.length){
      return rest == 0? 0 : Integer.MAX_VALUE;
    }

    // 情况
    // 1. 当前索引的货币不要
    int p1 = process1(values, counts, index + 1, rest);

    // 2. 要当前的货币
    int p2 = Integer.MAX_VALUE;
    for (int zhang = 1; zhang <= rest / values[index] && zhang <= counts[index]; zhang++) {
      int next = process1(values, counts, index + 1, rest - zhang * values[index]);
      if (next != Integer.MAX_VALUE){
        p2 =  Math.min(p2, zhang + next);
      }
    }
    return Math.min(p1, p2);

  }

  private static int dp2(int[] arr, int aim){
    Info info = getInfo(arr);
    int[] counts = info.getCount();
    int[] values = info.getValues();
    int N = values.length;
    int[][] dp = new int[N + 1][aim + 1];

    dp[N][0] = 0;
    for (int i = 1; i <= aim; i++){
      dp[N][i] = Integer.MAX_VALUE;
    }

    for (int index = N - 1; index >=0; index--) {
      for (int rest = 0; rest <= aim; rest++){
        // 情况
        // 1. 当前索引的货币不要
        int p1 = dp[index + 1][rest];

        // 2. 要当前的货币
        int p2 = Integer.MAX_VALUE;
        for (int zhang = 1; zhang <= rest / values[index] && zhang <= counts[index]; zhang++) {
          int next = dp[index + 1][rest - zhang * values[index]];
          if (next != Integer.MAX_VALUE){
            p2 =  Math.min(p2, zhang + next);
          }
        }
        dp[index][rest] =  Math.min(p1, p2);
      }

    }

    return dp[0][aim];
  }


  private static int dp3(int[] arr, int aim){
    if (aim == 0){
      return 0;
    }
    Info info = getInfo(arr);
    int[] values = info.getValues();
    int[] counts = info.getCount();

    int N = values.length;
    int[][] dp = new int[N + 1][aim + 1];

    dp[N][0] = 0;

    for (int i = 1; i <= aim; i++){
      dp[N][i] = Integer.MAX_VALUE;
    }

    /**
     * todo 下面要做的就是要把这个二维数组，从下往上，从左往右填满
     *      dp1: 是直接是由 最原始的暴力递归 更改而来, 完全没有考虑重复的货币，所以时间复杂度是
     *
     *
     */

    for (int index = N - 1; index >= 0; index--){
      // 第一层，代表货币面值所在的下标索引，也就是代表了所有可能的货币面值 对应的就是dp[][]的第一维度 0 ~ N, N已经填好了，所以从（N - 1） -> 0 填
      for (int mod = 0; mod < Math.min(aim + 1, values[index]); mod++){
        /**
         *  第二层其实代表的是就是dp的 第二维度， 也就是 0 ~ aim
         *  参考 dp3, 我们得知，dp[i][rest] 是  dp[i + 1][rest], dp[i + 1][rest - values[i] * 1] .....dp[i + 1][rest - values[i] * n] 中的最小值
         *  原来每算一次都要去比较一次得出最小值（这边并不是根据累加和），还是有重复的计算，所以我们这里采用 双端队列 更新最小值，以达到减少时间复杂度的目的
         *  todo 怎么去铺满    每个货币值，对应的 0 ~ aim呢
         *       也就是 mod + arr[index] <= aim
         *
         */

        LinkedList<Integer> w = new LinkedList<>();
        // todo 这边为什直接等于，而没有比较呢，应为 mod 的范围在 0 ~ arr[index], 没有依赖多个数据，只依赖  dp[index + 1][mod]
        dp[index][mod] = dp[index + 1][mod];
        // todo 将 mod直接放入双端队列, 代表目前 mod 对应的是 dp[index + 1][mod] 是 dp[index][rest]所依赖的初始最小值
        w.add(mod);

        for (int rest = mod + values[index]; rest <= aim; rest += values[index]){
          while (!w.isEmpty() && (dp[index + 1][w.peekLast()] == Integer.MAX_VALUE
          || dp[index + 1][w.peekLast()] + compensate(values[index],rest, w.peekLast()) >= dp[index + 1][rest])){
            /**
             * 这边while的循环条件思考和解释
             * 1. !w.isEmpty() 首先得保证队列不能为空
             * 2. 根据双端队列极值更新原则，相等取最新值
             * 3. dp[index + 1][w.peekLast()] + compensate(arr[index],rest, w.peekLast()) >= dp[index + 1][rest])
             *    这个条件有点搞脑子
             *    3.1 如果只是 dp[index + 1][w.peekLast()]>= dp[index + 1][rest]， 这个应该没有疑问，就是正常的更新双端队列的操作
             *
             *    3.2 但是为什么要 + compensate(arr[index],rest, w.peekLast()）
             *        参考暴力递归的 p2 =  Math.min(p2, zhang + next);
             *        这边的 dp[index + 1][w.peekLast()] --> next
             *        compensate(arr[index],rest, w.peekLast()）--> zhang
             */
            w.pollLast();
          }
          w.addLast(rest);

          int outOfRestValue = rest - values[index] *(counts[index] + 1);
          // 跟新补偿的边界
          if (w.peekFirst() == outOfRestValue){
            w.pollFirst();
          }
          dp[index][rest] = dp[index + 1][w.peekFirst()] + compensate(values[index], rest, w.peekFirst());
        }

      }
    }
    return dp[0][aim];
  }

  private static int compensate(int value, int rest0, int restEdge){
    return (rest0 - restEdge) / value;
  }

  private static Info getInfo(int[] arr){

    Map<Integer, Integer> map = new HashMap<>();
    int len = arr.length;
    for (int i = 0; i < len; i++){
      if (map.containsKey(arr[i])){
        int count = map.get(arr[i]) + 1;
        map.put(arr[i], count);
      }else {
        map.put(arr[i], 1);
      }
    }

    Set<Integer> valueSet = map.keySet();
    int size = valueSet.size();
    int[] values = new int[size];
    int[] counts = new int[size];
    int index = 0;

    for (Integer value : valueSet) {
      values[index] = value;
      counts[index++] = map.get(value);
    }
    return new Info(values, counts);
  }
  /**
   * 自然智慧，也就是暴力递归
   *
   * @param arr
   * @param aim
   * @return
   */
  private static int natureWisdom(int[] arr, int aim) {
    return process(arr, 0, aim);
  }

  private static int process(int[] arr, int index, int rest) {
    if (index == arr.length){
      return rest == 0 ? 0 : Integer.MAX_VALUE;
    }

    // 就是选这个货币，或者不选这个货币两种可能
    int p1 = process(arr, index + 1, rest);
    int p2 = Integer.MAX_VALUE;
    if (rest >= arr[index]){
      int next = process(arr, index + 1, rest - arr[index]);
      if (next != Integer.MAX_VALUE){
        p2 = 1 + next;
      }
    }
    return Math.min(p1, p2);
  }

  private static int[] generate(int maxArrLen, int maxValue) {
    int len = randomByValue(maxArrLen);

    int[] arr = new int[len];

    for (int i = 0; i < len; i++){
      arr[i] = randomByValue(maxValue);
    }

    return arr;
  }

  private static int randomByValue(int v){
    return (int)(Math.random() * v) + 1;
  }

  
}

class Info{
  private int[] values;
  private int[] count;

  public Info(int[] values, int[] count) {
    this.values = values;
    this.count = count;
  }

  public int[] getValues() {
    return values;
  }

  public void setValues(int[] values) {
    this.values = values;
  }

  public int[] getCount() {
    return count;
  }

  public void setCount(int[] count) {
    this.count = count;
  }
}
