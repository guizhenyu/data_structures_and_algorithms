package com.gzy.code.basic.class05;

/**
 * description: CountOfRangeSum date: 2021/12/24 1:26 下午
 *  求一堆数组中， 子数组的和在 [lower, upper]之中的个数
 * @author: guizhenyu
 */
public class CountOfRangeSum {

  /**
   *
   * 1. 将数组arr 转化成 0~N数组和 sum
   * 2. 将求数组以下标为N结尾的，满足和在[lower, upper]中,
   *    *    转化成求 sum[0] ~ sum[N - 1] 满足在 【 sum[N】 - upper, sum[N] - lower), 这边的区间是左闭右开
   * 3. 上面还少了一个判断自己sum[N]是否在 [lower, upper] 区间内
   * 4. 但是要想降低时间复杂度，采用归并算法，将sum数组拆成左右两部分 sum_L, sum_R
   *    4.1 求 sum_L中， 满足 【 sum_R[N】 - upper, sum_R[N] - lower)
   *    4.2 计算完成后，将 sum_L, sum_R 归并排序，这样就保证了， sum_L和sum_R都是有序递增的，
   *    ************   也保证了，4.1的比较过程就是遍历一次（需要好好理解）
   *
   *  感悟！！！！！！！
   *  归并中用了递归方法，
   *  自己在理解 4.1的时候，为什么sum_L 和sum_R是递增的花了好长时间才想通
   *  其实归并拆分到最小粒度的时候，sum_L中只有一个元素sum[N -1]， 同样sum_R中只有一个元素sum[N]
   *  因为只有一个元素，其实也是有序的
   *  计算区间后
   *  然后归并这两个元素变成了 sum_L或者sum_R，也完成了排序，也就保证了 sum_L, sum_R都是有序递增的
   *
   *
   */
  public int countRangeSum(int[] arr, int lower, int upper){

    if (null == arr || arr.length == 0){
      return 0;
    }

    // 定义一个sum数组，用于保存0到 arr.length 的子数组和
    long[] sums = new long[arr.length];
    sums[0] = arr[0];
    // 计算sums
    for (int i = 1; i < arr.length; i++){
      sums[i] = sums[i -1 ] + arr[i];
    }

    return count(sums, lower, upper, 0, sums.length - 1);
  }


  /**
   * 具体的归并算法
   *
   * @param sums  数组前缀和
   * @param lower
   * @param upper
   * @param left
   * @param right
   * @return
   */
  private static int count(long[] sums, int lower, int upper, int left, int right) {

    // 3. 上面还少了一个判断自己sum[N]是否在 [lower, upper] 区间内
    if (left == right){
      if(sums[left] >= lower && sums[left] <= upper){
        return 1;
      }else{
        return 0;
      }
    }

    int mid = left + ((right - left) >> 1);

    return count(sums, lower, upper, left, mid) + count(sums, lower, upper, mid + 1, right)
        + merge(sums, lower, upper, left, mid, right);

  }

  /**
   * 具体的归并过程
   *
   * @param sums 数组前缀和
   * @param lower 区间最小数
   * @param upper 区间最大数
   * @param left merge的左边界
   * @param mid  merge的中间
   * @param right merge的右边界
   * @return
   */
  private static int merge(long[] sums, int lower, int upper, int left, int mid, int right) {
    // 2. 将求数组以下标为N结尾的，满足和在[lower, upper]中,
    //    转化成求 sum[0] ~ sum[N - 1] 满足在 【 sum[N】 - upper, sum[N] - lower), 这边的区间是左闭右开， 因为到不了sum[N]

    // 此时 sum[left~mid] 和 sum[mid + 1~right] 都是已经计算过他们各区区间的子数组和是否满足区间条件，而且都已经排序了，都是递增的
    // 所以就是求 以sum[mid + 1] ~ sum[right] 结尾的子数组中，sum[left] ~ sum[mid]中作为子数组的开始，并且满足 [lower, upper]
    // 由于sum[mid + 1] ~ sum[right]， sum[left] ~ sum[mid] 是递增的，所以只要遍历一次
    int ans = 0;
    int windowL = left;
    int windowR = left;

    // 窗口表达是【windowL, windowR)，左闭右开
    int rightIndex = mid + 1;
    while(rightIndex <= right){
      long lowerL = sums[rightIndex] - upper;
      long upperL = sums[rightIndex] - lower;
      // [lowerL, upperL]
      while (windowR <= mid && sums[windowR] <= upperL){
        // 因为这边采用的是后自增，所以是右开区间
        // 如果是右闭区间的话，windowR正好遍历到最后一位，条件也符合， windowR ++ > mid,就越界了，此时sum左[windowR] 不存在
        windowR++;
      }

      while(windowL <= mid && sums[windowL] < lowerL){
        windowL++;
      }
      rightIndex++;
      ans += windowR - windowL;
    }

    // 归并l 和 r
    long[] helpArr = new long[right - left + 1];

    int l = left;
    int r = mid + 1;
    int index = 0;
    // 左右两边先一起比较
    while(l <= mid && r <= right){
      helpArr[index++] = sums[l] <= sums[r]? sums[l++]:sums[r++];
    }

    // 右边已经遍历完了，左边还没遍历完
    while(l <= mid){
      helpArr[index++] = sums[l++];
    }

    // 左边已经遍历完了，右边还没遍历完
    while(r <= right){
      helpArr[index++] = sums[r++];
    }

    for (int i = 0; i < helpArr.length; i++){
      sums[left + i] = helpArr[i];
    }
    return ans;
  }

}
