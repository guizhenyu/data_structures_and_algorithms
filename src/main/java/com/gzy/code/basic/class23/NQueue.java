package com.gzy.code.basic.class23;

/**
 * description: Queue date: 2022/4/24 18:29
 *
 * @author: guizhenyu
 */
public class NQueue {


  public static void main(String[] args) {
    int n = 15;
    
    int ways = natureWisdom(n);
    System.out.println(ways);
//    System.out.println(num2(n));
  }

  private static int natureWisdom(int n) {
    if (n < 1){
      return 0;
    }

    int[] record = new int[n];

    return process(record, 0, n);
  }

  private static int process(int[] record, int row, int n) {

    if (row == n){
      return 1;
    }

    int rest = 0;

    for (int i = 0; i < n; i++){

      if (canPut(record, row, i)){
        record[row] = i;
        rest += process(record, row + 1, n);
      }
    }


    return rest;
  }

  public static boolean canPut(int[] record, int row, int column){
    // 判断是否和之前位置同列，或者是在之前位置的对角线上
    for(int r = 0; r < row; r++){
      if (record[r] == column || Math.abs(record[r] - column) == Math.abs(row - r)){
        return false;
      }
    }
    return true;
  }

  /**
   * 采用 位运算
   *
   * @param n
   * @return
   */
  public static int num2(int n){
    // 32维，一搬的计算机算不出来，计算时间太长了
    if (n < 1 || n > 32){
      return 0;
    }

    int limit = n == 32 ? -1 : (1 << n) - 1;

    return process2(limit, 0, 0, 0);
  }

  /**
   *
   *
   * @param limit
   * @param colLim
   * @param leftLim
   * @param rightLim
   * @return
   */
  private static int process2(int limit, int colLim, int leftLim, int rightLim) {

    if (colLim == limit){
      return 1;
    }
    // pos中所有是1的位置，是你可以去尝试皇后的位置
    int pos = limit & (~(colLim | leftLim | rightLim));
    int mostRightOne = 0;
    int res = 0;
    while (pos != 0){
      // 取最右边的1
      mostRightOne = pos & (~pos + 1);
      pos = pos - mostRightOne;
      res += process2(limit, colLim | mostRightOne, (leftLim | mostRightOne) << 1,
          (rightLim | mostRightOne) >> 1);
    }

    return res;
  }

}
