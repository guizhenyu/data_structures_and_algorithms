package com.gzy.code.basic.class05Quick;

import java.util.Stack;

/**
 * description: QuickSortRecursiveAndUnrecursive1 date: 2022/3/11 9:56 上午
 *
 * @author: guizhenyu
 */
public class QuickSortRecursiveAndUnrecursive1 {


  public static void main(String[] args) {

    int testTime = 50000;
    int size = 100;
    int maxValue = 100;

    boolean succeed = true;

    System.out.println("test started!");

    for (int i = 0; i < testTime; i++){
      int[] arr1 = generateRandomArray(size, maxValue);
      int[] arr2 = copyArray(arr1);

      quickSort1(arr1);

      quickSort2(arr2);

      if (!arrayEquals(arr1, arr2)){
        succeed = false;
        printArr(arr1);
        printArr(arr2);
        break;
      }
    }

    System.out.println(succeed?"Nice!":"Fucking fucked!");



  }

  private static void printArr(int[] arr1) {

    if (null == arr1 || arr1.length == 0){
      return;
    }

    for (int val : arr1){
      System.out.print(arr1 + " ");
    }
    System.out.println();


  }

  private static boolean arrayEquals(int[] arr1, int[] arr2) {
    if ((arr1 == null && null != arr2) || (arr2 == null && arr1 == null )){
      return false;
    }

    if (arr1 == null && null == arr2 ){
      return true;
    }

    if (arr1.length != arr2.length) {
      return false;
    }

    for (int i = 0; i < arr1.length; i++){
      if (arr1[i] != arr2[i]){
        return false;
      }
    }
    return true;
  }

  /**
   * 快速排序 version = 3.0, 非递归版本
   *
   * @param arr
   */
  private static void quickSort2(int[] arr) {
    if (null == arr || arr.length <= 1){
      return;
    }
    try {
      swap(arr, (int) (Math.random() * (arr.length - 1)), arr.length - 1);
    }catch (Exception e){
      System.out.println("dsgfds");
    }
    Stack<NetherLandsSide > whileSides = new Stack<>();

    int[] whiteArea = netherLandFlag(arr, 0, arr.length - 1);
    whileSides.push(new NetherLandsSide (0, whiteArea[0] - 1));
    whileSides.push(new NetherLandsSide (whiteArea[1] + 1, arr.length - 1));

    while(!whileSides.isEmpty()){
      NetherLandsSide  netherLandsSide = whileSides.pop();

      if (netherLandsSide.left < netherLandsSide.right){
        int left = netherLandsSide.left;
        int right = netherLandsSide.right;
        swap(arr, (int)(Math.random() * (right - left + 1)) + left ,right);
        int[] netherLandFlag = netherLandFlag(arr, left, right);
        whileSides.push(new NetherLandsSide (left, netherLandFlag[0] - 1));
        whileSides.push(new NetherLandsSide (netherLandFlag[1] + 1, right));
      }


    }

  }

  /**
   * 快速排序 version = 3.0, 递归版本
   * @param arr1
   */
  private static void quickSort1(int[] arr1) {
    if (null == arr1 || arr1.length == 1){
      return;
    }
    process1(arr1, 0, arr1.length - 1);
  }

  private static void process1(int[] arr, int L, int R) {
    if (L >= R){
      return;
    }

    swap(arr, (int)(Math.random() *(R - L + 1)) + L, R);

    int[] midArea = netherLandFlag(arr, L, R);

    process1(arr, L, midArea[0] - 1);
    process1(arr, midArea[1] + 1, R);
  }

  /**
   * 荷兰国旗问题
   * 此时 arr[r] 作为中间的数，来区分数组
   * @param arr
   * @param l
   * @param r
   * @return 返回一个代表中间数的索引数组
   */
  private static int[] netherLandFlag(int[] arr, int l, int r) {
    int[] midArea = new int[2];
    // todo 我们默认认为， 数组下标 l ~ (r - 1) 的数 = arr[r]
    // 左边界
    int left = l;
    // 右边界
    int right = r - 1;

    int index = l;
    while (index <= right){
      if (arr[index] < arr[r]){
        // 左边界往右移动一位，并且交换数据
        swap(arr, index++, left++);

      }else if (arr[index] > arr[r]){
        // 右边界往左移动一位，并且交换数据，
        // todo: 这边的index没有自增，因为交换位置后，数据还没有跟 arr[r]比较
        swap(arr, index, right--);
      }else{
        // 相等，左右边界都不变
        index++;
      }
    }

    swap(arr, ++right, r);

    midArea[0] = left;
    midArea[1] = right;
    return midArea;
  }


  private static int[] copyArray(int[] arr1) {
    if (null == arr1){
      return null;
    }

    int[] copyArr = new int[arr1.length];

    for (int i = 0; i < arr1.length; i++){
      copyArr[i] = arr1[i];
    }

    return copyArr;
  }

  private static int[] generateRandomArray(int size, int maxValue) {
    int arraySize = (int)(Math.random()*  (size + 1));

    int[] randomArr = new int[arraySize];

    for (int i = 0; i < arraySize; i++){
      randomArr[i] = (int)(Math.random() * (maxValue + 1)) - (int)(Math.random() * maxValue);
    }

    return randomArr;

  }


  public static void swap(int[] arr, int from, int to){
    int v1 = arr[from];
    arr[from] = arr[to];
    arr[to] = v1;
  }
}

/**
 * 这个类代表荷兰国旗中间白色的左右边界
 *
 */
class NetherLandsSide {
  // 左边界
  public int left;
  // 右边界
  public int right;

  public int getLeft() {
    return left;
  }

  public void setLeft(int left) {
    this.left = left;
  }

  public int getRight() {
    return right;
  }

  public void setRight(int right) {
    this.right = right;
  }

  @Override
  public String toString() {
    return "NetherLandsWhileSide{" +
        "left=" + left +
        ", right=" + right +
        '}';
  }

  public NetherLandsSide (int left, int right) {
    this.left = left;
    this.right = right;
  }
}
