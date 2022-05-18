package com.gzy.code.basic.class32;

/**
 * description: IndexTree date: 2022/5/14 13:49
 *
 * @author: guizhenyu
 */
public class IndexTree {

  /**
   * 索引树
   * todo:
   *      总结概要：将索引从1 ~ N的数，划分为 2^n的数据段，而每个数据段的和（不一定包含整个数据段的数）保存在help数组中
   *      比如 arr   : 3 13 4 6 2 5 7 9 11 32  8  1  14  2  1  11  10 9
   *          index : 1  2 3 4 5 6 7 8 9 10  11  12 13  14 15 16  17 18
   *                                                         大小
   *          范围是 1-1 大小 1
   *          help[1] = arr[1] = 3                            1
   *          2代表2-2 大小为1，往前找，正好找到跟他一样范围大小的数，所以把help[1]并过来，此时大小是2
   *          help[2] = arr[2] + help[1]= 16                  2
   *          范围是 3-3 大小为1， 往前找大小范围是2的help,所以这么只包含arr[3]
   *          help[3] = arr[3] = 4                            1
   *           4-4 == 3-3 ==> 3 - 4 == 1- 2 ==> 1 - 4
   *          help[4] =  arr[4] + help[3] + help[2] = 26      4
   *          help[5] = arr[5] = 2                            1
   *          help[6] = arr[6] + help[5] = 7                  2
   *          help[7] = arr[7] = 7                            1
   *          help[8] =                                       8 ( 1 + 1 => 2 + 2 => 4 + 4 => 8)
   *                  = arr[8] + help[7] + help[6] + help[4]
   *                  = 9 + 7 + 7 + 26 = 49
   *          help[9] = arr[9] = 11                           1
   *          help[10] = arr[10] + help[9] = 43               2
   *          help[11] = arr[11] = 8                          1
   *          help[12] =                                      4
   *                   = arr[12] + help[11] + help[10]
   *                   = 1 + 8 + 43 = 52
   *          help[13] = arr[13] = 14                         1
   *          help[14] = arr[14] + help[13] = 16              2
   *          help[15] = arr[15] = 1                          1
   *          help[16] =                                      16 (1 + 1 => 2 + 2 => 4 + 4 => 8 + 8 => 16 )
   *                   = arr[16] + help[15] + help[14] + help[12] + help[8]
   *                   = 11 + 1 + 16 + 52 + 49 = 129
   *          help[17] = arr[17] = 10                          1
   *          help[18] = arr[18] + help[17] = 19               2
   *       总结：将help[index]索引对应和范围arr[i]，也就是
   *          1 2 1 4 1 2 1 8 1 2 1 4 1 2 1 16 1 2
   *          比如 help[18]
   *          18 转为为二进制的数： 10010
   *          10010
   *          那么 help[18] = help[10010] = arr[10001] +...+ arr[10010]
   *                       = arr[17] + ... +arr[18]
   *               help[15] = help[01111] = arr[01111] +...+ arr[01111] = arr[15]
   *               help[16] = help[10000] = arr[00001] + ... + arr[10000] = arr[1] + ... + arr[16]
   *           规律就是： help[index] 代表哪些数的和？
   *                    1.将 index 转化成二进制  0110100
   *                    2.help[index] = arr[二进制数最右边的1移到最右边 -> 0110001] + ~ + arr[0110100]
   *           所以 sum[index] = help[index] + help[index  - 最右边的1(二进制) = x ] + help[x - x最右边的二进制] +
   *                            ... help[n(n为二进制状态下只有一个1)]
   *

   */


  public int N;
  public int[] tree;

  public IndexTree(int n){
    N = n + 1;
    tree = new int[N];
  }

  public void add(int index, int val){

    while (index < N){
      tree[index] += val;
      index += index & -index; // 取最右边的1
    }

  }


  public int sum(int index){
    int res = 0;
    while (index > 0){
      res += tree[index];
      index -= index & -index;
    }
    return res;
  }
  public static class Right {
    private int[] nums;
    private int N;

    public Right(int size) {
      N = size + 1;
      nums = new int[N + 1];
    }

    public int sum(int index) {
      int ret = 0;
      for (int i = 1; i <= index; i++) {
        ret += nums[i];
      }
      return ret;
    }

    public void add(int index, int d) {
      nums[index] += d;
    }

  }

  public static void main(String[] args) {
    int N = 100;
    int V = 100;
    int testTime = 2000000;
    IndexTree tree = new IndexTree(N);
    Right test = new Right(N);
    System.out.println("test begin");
    for (int i = 0; i < testTime; i++) {
      int index = (int) (Math.random() * N) + 1;
      if (Math.random() <= 0.5) {
        int add = (int) (Math.random() * V);
        tree.add(index, add);
        test.add(index, add);
      } else {
        if (tree.sum(index) != test.sum(index)) {
          System.out.println("Oops!");
        }
      }
    }
    System.out.println("test finish");
  }


}
