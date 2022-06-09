package com.gzy.code.basic.class05Quick;

/**
 * description: PartitionAndQuickSort date: 2021/12/24 5:18 下午
 *
 * @author: guizhenyu
 */
public class PartitionAndQuickSort {
  public static void swap(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }



  public static void quickSort1(int[] arr){

    if(null == arr || arr.length == 0){
      return;
    }

    process1(arr, 0, arr.length - 1);
  }

  private static void process1(int[] arr, int L, int R) {

    if (L >= R){
      return;
    }

//    int M = partition(arr, L, R);
    int M = partitionMyselfError1(arr, L, R);
    process1(arr, L, M - 1);
    process1(arr, M + 1, R);

  }



  /**
   * 取最右边的一个数，然后把arr中的数分成两部分，并返回
   *
   * @param arr
   * @param l
   * @param r
   * @return
   */
  private static int partitionMyself(int[] arr, int l, int r) {
    /**
     * todo: 这边是算的话，左右和右边最好只用一边
     * 定义 边界时一定要清楚
     *
     */
    int leftEnd = l;
    int rightStart = r;
    while (leftEnd < rightStart){
      if (arr[leftEnd] >= arr[r]){
        swap(arr, leftEnd, --rightStart);
        continue;
      }
      leftEnd++;
    }

    swap(arr, rightStart, r);

    return rightStart;
  }


  /**
   * 取最右边的一个数，然后把arr中的数分成两部分，并返回
   *
   * @param arr
   * @param l
   * @param r
   * @return
   */
  private static int partitionMyselfError(int[] arr, int l, int r) {

    int leftEnd = l;
    /**
     * todo: 错误分析，这边rightStart不应该定义成 r - 1
     *
     *
     */
    int rightStart = r - 1;
    while (leftEnd < rightStart){
      if (arr[leftEnd] >= arr[r]){
        swap(arr, leftEnd, rightStart--);
        continue;
      }
      leftEnd++;
    }

    if (arr[leftEnd] >= arr[r]){
      swap(arr, leftEnd, r);
    }

    return leftEnd;
  }
  private static int partitionMyselfError1(int[] arr, int l, int r) {

    int leftEnd = l;
    /**
     * todo: 错误分析，这边rightStart不应该定义成 r - 1，因为不一定有比arr[r]大的数
     * 只能定义成  r
     *
     */
    int rightStart = r;
    while (leftEnd < rightStart){
      if (arr[leftEnd] >= arr[r]){
        swap(arr, leftEnd, --rightStart);
        continue;
      }
      leftEnd++;
    }

//    if (arr[rightStart] >= arr[r]){
      swap(arr, rightStart, r);
//    }

    return rightStart;
  }
  private static int partition(int[] arr, int l, int r) {

    int lessEqual = l - 1;
    int index = l;

    while(index < r){
      // 这边理解的时候，自然会想到，为啥大于的时候就只增index
      // lessEqual代表左边界， 刚开始还没有比较是没有的，所以定义成 L - 1，
      //
      /**
       * todo:
       * 这边理解的时候，自然会想到，为啥大于的时候就只增index
       * lessEqual代表左边界， 刚开始还没有比较是没有的，
       * 所以定义成 L - 1， 假设L ~ R - 1的数都比R大（因为还没比较）
       * 然后一旦有小的数，就直接跟左边界的数交换，并且左边界右移一位
       *
       */
      if (arr[index] <= arr[r]){
        swap(arr, index, ++lessEqual);
      }
      index++;
    }

    // 这边为啥要用 ++lessEqual 和 r 交换
    // 由于要返回一个中间数对应的索引，所以这边要将 ++lessEqual 的数进行交换
    // 最终返回 lessEqual
    swap(arr, ++lessEqual, r);
    return lessEqual;

  }

  // for test
  public static int[] generateRandomArray(int maxSize, int maxValue) {
    int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
    }
    return arr;
  }

  // for test
  public static int[] copyArray(int[] arr) {
    if (arr == null) {
      return null;
    }
    int[] res = new int[arr.length];
    for (int i = 0; i < arr.length; i++) {
      res[i] = arr[i];
    }
    return res;
  }

  // for test
  public static boolean isEqual(int[] arr1, int[] arr2) {
    if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
      return false;
    }
    if (arr1 == null && arr2 == null) {
      return true;
    }
    if (arr1.length != arr2.length) {
      return false;
    }
    for (int i = 0; i < arr1.length; i++) {
      if (arr1[i] != arr2[i]) {
        return false;
      }
    }
    return true;
  }

  // for test
  public static void printArray(int[] arr) {
    if (arr == null) {
      return;
    }
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }

  // for test
  public static void main(String[] args) {
    int testTime = 500000;
    int maxSize = 100;
    int maxValue = 100;
    boolean succeed = true;
    for (int i = 0; i < testTime; i++) {
      int[] arr1 = generateRandomArray(maxSize, maxValue);
      int[] arr2 = copyArray(arr1);
      int[] arr3 = copyArray(arr1);
      quickSort1(arr1);
      quickSort2(arr2);
//      quickSort3(arr3);
//      if (!isEqual(arr1, arr2) || !isEqual(arr2, arr3)) {
      if (!isEqual(arr1, arr2)) {
        succeed = false;
        break;
      }
    }
    System.out.println(succeed ? "Nice!" : "Oops!");

  }


  public static void quickSort2(int[] arr) {
    if (arr == null || arr.length < 2) {
      return;
    }
    process2(arr, 0, arr.length - 1);
  }

  // arr[L...R] 排有序，快排2.0方式
  // 荷兰国旗问题，就是把相等的数放在一起，左边是小于，右边是大于，并返回一个等值的区间；
  public static void process2(int[] arr, int L, int R) {
    if (L >= R) {
      return;
    }

    int[] midArea = netherlandsFlag(arr, L, R);
    process2(arr, L,midArea[0] - 1);
    process2(arr, midArea[1] + 1, R);

  }
  // arr[L...R] 玩荷兰国旗问题的划分，以arr[R]做划分值
  // <arr[R] ==arr[R] > arr[R]
  public static int[] netherlandsFlag(int[] arr, int L, int R) {

    // 左边界, 刚开始的时候不存在
    int less = L - 1;
    // 右边界, 刚开始的时候不存在
    int more = R;
    int index = L;
    while(index < more){
      // arr[index] 左边的数的情况
      // 1. 没有，第一次循环
      // 2. 小于，左边界还没有找到，需要往右移动
      // 3. 等于。 左边界不变

      // 比较arr[index] 跟 arr[R]
      // 等于, 左边界不变， 然后index++
      if (arr[index] == arr[R]){
        index++;
      }else if(arr[index] < arr[R]){
        swap(arr, index++, ++less);
      }else {
        swap(arr, index, --more);
      }
      /**
       * todo:  平时代码中的尽量用if判断代替else if 的习惯不严谨
       * 由于index 和more都是变化的，所以我用这种三个if取代一个判断条件
       * 可能会导致进两次if,导致排序失败
       * 而刚开始就是由于这样写导致排序失败
       */
//      if (arr[index] < arr[R]){
//        // < 数和左边界的数交换，然后index ++, ++less
//        swap(arr, index++, ++less);
//      }
//      // > 数和右边界的数交换，然后index ++, --more
//      if (arr[index] > arr[R]){
//        swap(arr, index, --more);
//      }
    }
    // 此时 L..less~more...R;
    // more代表的数肯定比R小
    swap(arr, more, R);
    return new int[]{less + 1, more};
  }

  public static int[] netherlandsFlag1(int[] arr, int L, int R) {
//    if (L > R) { // L...R L>R
//      return new int[] { -1, -1 };
//    }
//    if (L == R) {
//      return new int[] { L, R };
//    }
    int less = L - 1; // < 区 右边界
    int more = R; // > 区 左边界
    int index = L;
    while (index < more) { // 当前位置，不能和 >区的左边界撞上
      if (arr[index] == arr[R]) {
        index++;
      } else if (arr[index] < arr[R]) {
//				swap(arr, less + 1, index);
//				less++;
//				index++;
        swap(arr, index++, ++less);
      } else { // >
        swap(arr, index, --more);
      }
    }
    swap(arr, more, R); // <[R]   =[R]   >[R]
    return new int[] { less + 1, more };
  }
}
