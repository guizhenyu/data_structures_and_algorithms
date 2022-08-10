package com.gzy.code.basic.class23.second;

/**
 * description: NQueue date: 2022/8/8 14:36
 *  N皇后问题是指在N*N的棋盘上要摆N个皇后，
 *  要求任何两个皇后不同行、不同列， 也不在同一条斜线上
 *  给定一个整数n，返回n皇后的摆法有多少种。  n=1，返回1
 *  n=2或3，2皇后和3皇后问题无论怎么摆都不行，返回0
 *  n=8，返回92
 * @author: guizhenyu
 */
public class NQueens {


  public static void main(String[] args) {
    int N = 15;


    System.out.println(f2(N));
//    System.out.println(violenceRecursive(N));
  }

  /**
   * 采用位运算去
   *
   * @param N
   * @return
   */
  private static int f2(int N) {
    if (N < 1 || N > 32){
      return 0;

    }


    int limit = N == 32? -1 : (1 << N) - 1;

    return process1(limit, 0, 0, 0);

  }

  /**
   *
   * @param limit  由1 组成的二进制的数，每个1代表一个queen
   * @param colLimit 转化成二进制，1代表该列已经有queen了
   * @param leftLimit 转化成二进制， 1代表该位置正好于前面的queen在同一个斜线上
   * @param rightLimit 转化成二进制， 1代表该位置正好于前面的queen在同一个斜线上
   * @return
   */
  private static int process1(int limit, int colLimit, int leftLimit, int rightLimit) {
    // 情况1： limit == colLimit, 代表所有的皇后填满了
    if (limit == colLimit){
      return 1;
    }
    // 情况2：继续填
    // 求当前有哪些位置可以填
    int pos = limit & (~(colLimit | leftLimit | rightLimit));

    // 这边有个技巧： pos 转成二进制后，1的位置代表当前可以放queen
    // 所以我们可以依次取 pos最右边的1
    int mostRightOne = 0;
    int ans = 0;
    while (pos != 0){
      mostRightOne = pos & (~pos + 1);
      pos -= mostRightOne;
      ans += process1(limit, colLimit | mostRightOne, (mostRightOne | leftLimit) << 1, (mostRightOne | rightLimit) >> 1);
    }

    return ans;
  }

  private static int violenceRecursive(int N) {
    /**
     * 思考： 要肯定要一行一列的去填写
     * 需要两个变量，一个是 行号， 一个是记录之前已经填写的行对应的那一列
     * 然后每一行的填写时需要有个校验方法
     */
    if (N == 0){
      return 0;
    }

    int[] record = new int[N];
    return process(0, record, N);

  }

  /**
   * 代表当前来到第 row 行去填写
   *
   * @param row
   * @param record
   * @param n
   * @return
   */
  private static int process(int row, int[] record, int n) {

    if (row == n){
      return 1;
    }
    int ans = 0;
    for (int col = 0; col < n; col++){
      if (validate(col, row, record)){
        record[row] = col;
        ans += process(row + 1, record, n);
      }
    }
    return ans;
  }

  /**
   * 校验是否在同一个斜线上，并且不在同一列
   *
   * @param col
   * @param row
   * @param record
   * @return
   */
  private static boolean validate(int col, int row, int[] record) {
    for (int r = 0; r < row; r++){
      if (record[r] == col || Math.abs(col - record[r]) == Math.abs(row - r)){
        return false;
      }
    }

    return true;
  }

}
