package com.gzy.code.basic.class05Quick.countOfRangeSum;

/**
 * description: CountOfRangeSum1 date: 2022/3/14 9:58 上午
 *
 * 求一堆数组中，子数组的和在 【lower, upper】之中的个数
 *
 * 审题：
 *   1； 子数组，
 *   2. 组数组的和
 *
 *  思路
 *  先计算
 *
 * @author: guizhenyu
 */
public class CountOfRangeSum1 {

  public static int countOfRangeSum(int[] arr, int lower, int upper){

    if (null == arr || arr.length == 0){
      return 0;
    }

    int[] sum = new int[arr.length];

    sum[0] = arr[0];

    for (int i = 1; i < arr.length; i++){
      sum[i] = sum[i-1] + arr[i];
    }


    return count(sum, lower, upper, 0, sum.length - 1);

  }

  private static int count(int[] sum, int lower, int upper, int l, int r) {
    if (l == r){
      if (sum[l] >= lower && sum[l] <= upper){
        return 1;
      }else {
        return 0;
      }
    }

    int mid = l + (r - l + 1) / 2;

    return count(sum, lower, upper, l, mid) + count(sum, lower, upper, mid + 1, r)
        + merge(sum, lower, upper, l, mid, r);

  }

  private static int merge(int[] sum, int lower, int upper, int l, int mid, int r) {
    int ans = 0;

    int wl = l;
    int wr = l;


    for (int i = mid + 1; i <= r; i++){
      int upperL = sum[i] - lower ;
      int lowerL = sum[i] - upper;
      while(wr <= mid && sum[wr] <= upperL){
        wr++;
      }

      while(wl <= mid && sum[wl] < lowerL){
        wl++;
      }
      ans += wr - wl;
    }

    int[] helpArr = new int[r - l + 1];
    int helpIndex = 0;
    int leftIndex = l;
    int rightIndex = mid + 1;
    while (leftIndex <= mid && rightIndex <= r){
      helpArr[helpIndex++] = sum[leftIndex] < sum[rightIndex] ? sum[leftIndex++] : sum[rightIndex++];
    }

    while (leftIndex <= mid){
      helpArr[helpIndex++] = sum[leftIndex++];
    }

    while (rightIndex <= r){
      helpArr[helpIndex++] = sum[rightIndex++];
    }

    for (int i = 0; i < helpArr.length; i++){
      sum[l + i] = helpArr[i];
    }

    return ans;

  }


}
