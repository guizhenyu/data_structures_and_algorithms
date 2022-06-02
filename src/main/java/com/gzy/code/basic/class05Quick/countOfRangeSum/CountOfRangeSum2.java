package com.gzy.code.basic.class05Quick.countOfRangeSum;

/**
 * description: CountOfRangeSum2 date: 2022/3/23 3:48 下午
 * 求一堆数组中， 子数组的和在 [lower, upper]之中的个数
 * @author: guizhenyu
 */
public class CountOfRangeSum2 {
  /**
   * 求一堆数组中， 子数组的和在 [lower, upper]之中的个数
   * 问题分析：
   * 常规思路：
   * 分别求出所有子数组的和，然后比较，这种方法的时间复杂度太高了: n!
   *
   * 可以转化一下：
   * 1. 先求所有数组的前缀和
   * 2. 子数组的和，就转化成 后面的的前缀和 - 前面的前缀和，用这个去比较是否在【lower, upper】中
   * 3. 但是这样是不能降低时间复杂度，还得两两相减去比较
   *
   * // todo 4. 这边有个比较巧妙的地方，就是采用归并排序法，
   *         将前缀和数组进行排序。每次排序时，就进行比较，由于是归并排序，
   *         所以比较之前两个数组内的数已经是排好序的，因此只要一次遍历一次，就能计算出满足要求的子数组（因为是可以传递的）
   *
   */

  public int countRangeSum(int[] arr, int lower, int upper){

    if (null == arr || arr.length == 0){
      return 0;
    }

    int length = arr.length;
    // 这边必须要用long类型，否则会出现丢失精度
    long[] sums = new long[length];

    sums[0] = arr[0];
    for (int i = 1; i < length; i++){
      sums[i] = sums[i - 1] + arr[i];
    }

    return count(sums, lower, upper, 0, length - 1);

  }

  private int count(long[] sums, int lower, int upper, int l, int r) {

    if (l == r){
      return (sums[l] >= lower && sums[l] <= upper) ? 1 : 0;
    }

    int mid = l + ((r - l) >> 1);

    return count(sums, lower, upper, l, mid) + count(sums, lower, upper, mid + 1, r)
        + merge(sums, lower, upper, l, mid, r);
  }

  private int merge(long[] sums, int lower, int upper, int l, int mid, int r) {
    // 此时已经计算好 sums[l, mid] 和 sums[mid + 1, upper] 中满足要求的个数，而且sums[l, mid] 和 sums[mid + 1, upper]是单调递增的
    //TODO  上的不好理解，可以采用极限思维想一下，
    // 这边由于采用的是递归思想，所以递归的终止条件，就是 l == r 返回值是0或者1 此时sums[n]是一个数同样肯定也是有序的,
    // l == r 的前一个条件，就是 l + 1 = r, sum[l] 和 sum[r] 同样也是有序的。
    // 再往前的情况自己可以脑补下
    // 还有一个需要理解的就是：
    // 这边排序了，首先是不影响计算的结果值，其次减少了计算量。（具体的可以随便举个例子计算一下就能认可这个结论）


    // 所以现在就是要求 sums[mid + 1, upper] - sums[l, mid] 中符合要求的个数
    // 或者说就是在
    int ans = 0;
    // 这边设置两个时间窗口边界，ans = windowR - windowL
    //
    int windowR = l;
    int windowL = l;
    int rightIndex = mid + 1;
    //    int leftIndec = l;
    while (rightIndex <= r){
      long leftUpper = sums[rightIndex] - lower;
      long leftLower = sums[rightIndex] - upper;

      while (windowR <= mid && sums[windowR] <= leftUpper){
        windowR++;
      }
      //todo 这边要用 < ,为什么不是 >= . 自己理解（还原一下条件就理解了）
      while (windowL <= mid && sums[windowL] < leftLower){
        windowL++;
      }
      ans += windowR - windowL;
      rightIndex++;
    }

    // 将两个数组进行合并

    long[] helpArr = new long[r - l + 1];
    int helpIndex = 0;
    int leftIndex = l;
    rightIndex = mid + 1;
    while (leftIndex <= mid && rightIndex <= r){
      helpArr[helpIndex++] = sums[leftIndex] <= sums[rightIndex] ? sums[leftIndex++] : sums[rightIndex++];
    }

    while (leftIndex <= mid){
      helpArr[helpIndex++] = sums[leftIndex++];
    }

    while (rightIndex <= r){
      helpArr[helpIndex++] = sums[rightIndex++];
    }

    for (int i = 0; i < helpArr.length; i++){
      sums[l + i] = helpArr[i];
    }

    return ans;
  }


}
