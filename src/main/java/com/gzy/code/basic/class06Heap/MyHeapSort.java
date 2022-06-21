package com.gzy.code.basic.class06Heap;



/**
 * description: MyHeapSort date: 2021/12/30 7:52 下午
 *
 * @author: guizhenyu
 */
public class MyHeapSort {

  /**
   * todo:
   *      思路： 1. 数组先实现堆结构化
   *            2. 下标0和堆最后一个数交换，然后将堆继续结构化
   */

  private static void myHeapSort(int[] array) {

    if (null == array || array.length < 2){
      return;
    }

//    // 这种从从往后遍历的方式，时间复杂度是 N * logN
    for (int i = 0; i < array.length; i++) {
      heapInsert(array, i);
    }
    // 这边还有一种方法时间复杂度是N
    // 从后往前遍历，但是不是用的insert,用的是heapify
//    for (int i = array.length - 1; i >= 0; i--){
//      heapify(array, i, array.length);
//    }

    // 上面已经完成了大根堆的结构
    // 将大根堆从头开始取出，并且放到arr的末尾
    int heapSize = array.length;
    while (heapSize > 0){
      swap(array, 0, --heapSize);
      heapify(array, 0, heapSize);
    }
//    swap(array, 0, --heapSize);
//    // O(N*logN)
//    while (heapSize > 0) { // O(N)
//      heapify(array, 0, heapSize); // O(logN)
//      swap(array, 0, --heapSize); // O(1)
//    }
  }


  /**
   * 这边的由于数据已经放入数组中了
   * 所以先把大的数往父节点排
   * 就能得出大根堆的最大数
   *
   * @param arr
   * @param index
   */
  public static void heapInsert(int[] arr, int index){
    while(arr[index] > arr[(index - 1) / 2]){
      swap(arr, index, (index - 1) / 2);
      index = (index - 1) /2;
    }
  }

  /**
   * 这边是从头节点往下排，小的数往下排
   * 方便从大根堆中取数据
   *
   * @param arr
   * @param index
   */
  public static void heapify(int[] arr, int index, int heapSize){
    int left = index * 2 + 1;
    while(left < heapSize){

      // 取子节点 left 和 right 中最大的数
      // ，而且这样写太复杂了，有两个三元表达
      int largest = left + 1 >= heapSize ? left : arr[left] > arr[left + 1] ? left : left+1;
//      int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
      largest = arr[index] > arr[largest] ? index : largest;

      if (index == largest){
        // 结束了，不需要下移了
        break;
      }
      swap(arr, index, largest);
      index = largest;
      left = index * 2 + 1;

    }

  }

  public static void swap(int[] heap, int index1, int index2){
    int num1 = heap[index1];
    int num2 = heap[index2];
    heap[index1] = num2;
    heap[index2] = num1;
  }



  public static void main(String[] args) {
    int testTime = 500000;
    int maxSize = 100;
    int maxValue = 100;

    boolean flag = true;
    System.out.println("test started!");
    for (int i = 0; i < testTime; i++) {
      int[] array = generateRandomArray(maxSize, maxValue);
      int[] array1 = copy(array);
      int[] array2 = copy(array);

      myHeapSort(array);
      mergeSort(array1);
      quickSort(array2);
      if (!equals(array, array1) || !equals(array1, array2)){
        flag = false;
        printArray(array);
        printArray(array1);
        printArray(array2);
        break;
      }
    }
    System.out.println("test end!");
    System.out.println(flag?"success!":"Fucking fucked!");

  }


  /**
   * 归并排序
   *
   * @param arr
   */
  private static void mergeSort(int[] arr) {
    if (arr == null | arr.length < 2){
      return;
    }
    process(arr, 0, arr.length - 1);
  }

  /**
   * 快速排序
   *
   * @param arr
   */
  private static void quickSort(int[] arr){
    if (null == arr || arr.length < 2){
      return;
    }

    quickProcess(arr, 0, arr.length - 1);
  }

  /**
   * 快速排序的具体过程
   *
   * @param arr
   * @param l
   * @param r
   */
  private static void quickProcess(int[] arr, int l, int r) {
    if (l >= r){
      return;
    }

    int[] whiteArea = netherlandsFlags(arr, l, r, (int)(Math.random() * (r - l) + l));
    quickProcess(arr, l, whiteArea[0] - 1);
    quickProcess(arr, whiteArea[1] + 1, r);

  }


  /**
   * 将数组在 l ~ r 数分成  < arr[mid] , arr[mid],   >arr[mid] 三部
   *
   * @param arr
   * @param l
   * @param r
   * @param mid
   * @return
   */
  private static int[] netherlandsFlags(int[] arr, int l, int r, int mid) {

    swap(arr, r, mid);
    // 跟 arr[mid] 相等的左边界索引，刚开始默认所有的数据都是与arr[r]相等的，所以左边界不存在，因为所有的数都与arr[r]相等
    // 跟 arr[mid] 相等的右边界索引，同上
    // 此时 arr[mid] 和arr[r] 已经发生交换
    int leftEqualIndex = l - 1;
    int rightEqualIndex = r;
    // 下面开始 从遍历 l ~  (r-1)的数跟arr[r]比较，从而确定左右边界
    int index = l;
    while(index < rightEqualIndex){
      if (arr[index] == arr[r]){
        // do nothing
        index++;
      }else if(arr[index] < arr[r]){
        swap(arr, index++, ++leftEqualIndex);
      }else {
        swap(arr, index, --rightEqualIndex);
      }
    }
    // 数据已经排好了，~ arr[leftEqualIndex] ~ arr[rightEqualIndex] ~ arr[r]
    // 很明显arr[r]跟arr[rightEqualIndex]交换
    //todo:
    //    midValue = arr[r]
    //    由于 arr[leftEqualIndex] < midValue, 所以arr[leftEqualIndex + 1] = midValue
    //    arr[rightEqualIndex] > midValue, arr[rightEqualIndex - 1] = midValue
    //
    swap(arr, rightEqualIndex, r);
    //todo:
    //     由于 arr[rightEqualIndex] 和 arr[r] 发生了交换, 即 arr[rightEqualIndex] = midValue, 其实右边界已经往右移动了一位
    //     所以下面计算跟 midValue 的数相等的下标边界的，最左侧的是 leftEqualIndex + 1， 最右侧的就是 rightEqualIndex
    int[] areaEqual = new int[2];
    // 此时可能存在很多情况，leftEqualIndex < l 或者 rightEqualIndex >= r

    // 这边左边界+1，是由于arr[leftEqualIndex] 肯定是小于arr[r]的，他的初始值是界外的，默认不存在
    areaEqual[0] = leftEqualIndex + 1;
    // 这边直接用rightEqualIndex

    areaEqual[1] = rightEqualIndex;
    return areaEqual;
  }

  /**
   * 归并排序的具体过程
   *
   * @param arr
   * @param l
   * @param r
   */
  private static void process(int[] arr, int l, int r) {
    if (l == r){
      return;
    }

    int mid = l + (r - l) / 2;
    process(arr, l, mid);
    process(arr, mid + 1, r);

    merge(arr, l, mid, r);

  }

  /**
   * 合并
   *
   * @param arr
   * @param l
   * @param mid
   * @param r
   */
  private static void merge(int[] arr, int l, int mid, int r) {
    int[] helpArr = new int[r - l + 1];
    int left = l;
    int right = mid + 1;
    int helpIndex = 0;
    while(left <= mid && right <= r){
      helpArr[helpIndex++] = arr[left] < arr[right] ? arr[left++] : arr[right++];
    }

    while (left <= mid){
      helpArr[helpIndex++] = arr[left++];
    }

    while (right <= r){
      helpArr[helpIndex++] = arr[right++];
    }

    for (int i = 0; i < helpArr.length; i++){
      arr[l + i] = helpArr[i];
    }

  }


  /**
   * 比较两个数组是否相等
   *
   * @param array
   * @param array1
   * @return
   */
  private static boolean equals(int[] array, int[] array1) {
    // 首先如果两个都是null的情况
    if (null == array && null == array1){
      return true;
    }
    // 一个为null, 一个不为null
    if ((null == array && null != array1) ||
    null != array && null == array1){
      return false;
    }

    // 两个都不为null
    if (array.length != array1.length){
      return true;
    }

    for (int i = 0; i < array.length; i++){
      if (array[i] != array1[i]){
        return false;
      }
    }
    return true;
  }

  private static void printArray(int[] array) {

    for (int num : array) {
      System.out.print(num + " ");
    }
    System.out.println();
  }

  private static int[] copy(int[] array) {
    if (array == null){
      return null;
    }

    int[] res = new int[array.length];
    for (int i = 0; i < array.length; i++) {
      res[i] = array[i];
    }
    return res;
  }

  public static int[] generateRandomArray(int size, int value){

    int[] arr = new int[(int) (Math.random() * (size + 1))];

    for (int i = 0; i < arr.length; i++){
      arr[i] = (int)((value + 1) * Math.random()) - (int) (value * Math.random());
    }

    return arr;
  }



}
