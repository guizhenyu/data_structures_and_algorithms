package com.gzy.code.basic.class05Quick;


import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.BlockingQueue;

/**
 * description: QuickSortImpl date: 2022/6/9 17:22
 *
 * @author: guizhenyu
 */
public class QuickSortImpl {


  public static void quickSort1(int[] arr){
    if (arr == null || arr.length < 2){
      return;
    }

    process1(arr, 0, arr.length - 1);
  }

  /**
   * 快速排序的递归函数
   *
   * @param arr
   * @param l
   * @param r
   */
  private static void process1(int[] arr, int l, int r) {
    if (l >= r){
      return;
    }

    int partitionIndex = partitionAndSort(arr, l, r);
    process1(arr, l, partitionIndex - 1);
    process1(arr, partitionIndex + 1, r);
  }

  public static void swap(int[] arr, int from, int to){
    int temp = arr[from];
    arr[from] = arr[to];
    arr[to] = temp;
  }

  /**
   * 用下标为r的数做中间数，将数组分成两部分分， 左边是小于等于arr[r],右边是大于arr[r]
   * 思路： 1. 用 lessEqual 表示左边的边界，由于没有开始比较，所以组边界是不存在的，从 l - 1开始
   *       2.
   *
   * @param arr
   * @param l
   * @param r
   * @return
   */
  private static int partitionAndSort(int[] arr, int l, int r) {

    int index = l;
    int lessEqual = l - 1;
    while(index < r){
      if (arr[index] <= arr[r]){
        swap(arr, index, ++lessEqual);
      }
      index++;
    }
    swap(arr, r, ++lessEqual);

    return lessEqual;
  }

  public static void main(String[] args) {
    int testTime = 100000;
    int maxArrSize = 100;
    int maxValue = 200;
    
    for (int i = 0; i < testTime; i++){
      int arrSize = (int)(maxArrSize * Math.random());
      int[] arr = generateRandomArr(arrSize, maxValue);
      int[] copyArr1 = copyArr(arr);
      int[] copyArr2 = copyArr(arr);
      int[] copyArr3 = copyArr(arr);
      
      quickSort1(copyArr1);
      quickSort2(copyArr2);
      quickSort3(copyArr3);
      if (!arrEquals(copyArr1, copyArr2) ||
          !arrEquals(copyArr3, copyArr2)){
        System.out.println("Oops!");
      }
      
      
      
    }
  }

  private static boolean arrEquals(int[] arr1, int[] arr2) {

    if ((arr1 == null || arr1.length == 0) && (arr2 == null || arr2.length == 0)){
      return true;
    }

    int size1 = arr1 == null? 0 : arr1.length;
    int size2 = arr2 == null? 0 : arr1.length;
    if (size1 != size2){
      return false;
    }

    for (int i = 0; i < size1; i++){
      if (arr1[i] != arr2[i]){
        return false;
      }
    }

    return true;
  }

  /**
   * 荷兰国旗问题，非递归版本
   *
   * @param arr
   */
  private static void quickSort3(int[] arr) {
    if(arr == null || arr.length < 2){
      return;
    }
    process3(arr, 0, arr.length - 1);
  }

  private static void process3(int[] arr, int l, int r) {
    if (l >= r){
      return;
    }

    swap(arr, (int)(Math.random() * (r - l) + l), r);
    int[] area = netherlandFlag(arr, l, r);
    Stack<OP> stack = new Stack<>();
    stack.push(new OP(l, area[0] - 1));
    stack.push(new OP(area[1] + 1, r));

    while (!stack.isEmpty()){
      OP pop = stack.pop();

      if (pop.end > pop.start){
        swap(arr, (int)(Math.random() * (pop.end - pop.start) + pop.start), pop.end);
        area = netherlandFlag(arr, pop.start, pop.end);
        stack.push(new OP(pop.start, area[0] - 1));
        stack.push(new OP(area[1] + 1, pop.end));

      }
    }

  }

  /**
   * 荷兰国旗解决快速排序的问题，随机获取中间数
   * 递归版本
   * @param arr
   */
  private static void quickSort2(int[] arr) {
    if(arr == null || arr.length < 2){
      return;
    }
    process2(arr, 0, arr.length - 1);
  }

  private static void process2(int[] arr, int l, int r) {
    if (l >= r){
      return;
    }
    // 随机选取一个数作为中间数
    swap(arr, (int)(Math.random() * (r - l) + l), r);
    int[] whiteEnds= netherlandFlag(arr, l, r);
    process2(arr, l, whiteEnds[0] - 1);
    process2(arr, whiteEnds[1] + 1, r);
  }

  private static int[] netherlandFlag(int[] arr, int l, int r) {

    if (l > r){
      return new int[]{-1, -1};
    }

    if (l == r){
      return new int[]{l, l};
    }

    int[] ans = new int[2];
    // 这边是假设l~r范围上都是跟r - 1相等的数
    int lessIndex = l - 1;
    int greaterIndex = r;
    int index = l;
    while (index < greaterIndex){

      if (arr[index] < arr[r]){
        // index 跟 (lessIndex + 1) 交换
        swap(arr, index++, ++lessIndex);

      }else if (arr[index] == arr[r]){
        //只需要移动index
        index++;
      }else {
        // index 跟 greaterIndex - 1交换， index不变，应为greaterIndex - 1 还没有跟r比较
        swap(arr, index, --greaterIndex);
      }
    }
    
    // r是相等的，还得交换一次
    swap(arr, r, greaterIndex);
    return new int[]{lessIndex + 1, greaterIndex};
  }

  private static int[] copyArr(int[] arr) {
    
    if (arr == null || arr.length == 0){
      return null;
    }

    int[] ans = new int[arr.length];
    for (int i = 0; i < ans.length; i++){
      ans[i] = arr[i];
    }
    
    return ans;
  }

  private static int[] generateRandomArr(int n, int maxValue) {
    if (n == 0){
      return null;
    }

    int[] ans = new int[n];
    for (int i = 0; i < n; i++){
      ans[i] = (int)(Math.random() * maxValue);
    }
    
    return ans;
  }

  public static class OP{
    int start;
    int end;

    public OP(int start, int end){
      this.start = start;
      this.end = end;
    }
  }

}
