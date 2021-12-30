package com.gzy.code.basic.class05Quick;


import java.util.Stack;

/**
 * description: QuickSortRecursiveAndUnrecursive date: 2021/12/27 7:35 下午
 *
 * @author: guizhenyu
 */
public class QuickSortRecursiveAndUnrecursive {

  /**
   * 随机生成数组，实现对数器
   */
  public static int[] generateRandomArray(int maxSize, int maxValue){

    int size = (int)(Math.random() * (maxSize + 1));
    int[] randomArr = new int[size];

    for (int i = 0; i < size; i++){
      randomArr[i] = (int)(Math.random()*(maxValue + 1)) - (int)(Math.random() * maxValue);
    }
    return randomArr;
  }


  public static int[] copyArray(int[] arr){
    if (null == arr){
      return null;
    }

    int[] copyArr = new int[arr.length];
    for (int i = 0; i < arr.length; i++){
      copyArr[i] = arr[i];
    }

    return copyArr;
  }

  /**
   * 比较两个数组是否相等
   * @param arr1
   * @param arr2
   * @return
   */
  public static boolean equal(int[] arr1, int[] arr2){

    if ((null == arr1 && null != arr2) || (null == arr2 && null != arr1)){
      return false;
    }

    if (null == arr1 && null == arr2){
      return true;
    }

    if (arr1.length != arr2.length){
      return false;
    }
    // 下面都不是空了
    for (int i = 0; i < arr1.length; i++){
      if (arr1[i] != arr2[i]){
        return false;
      }
    }
    return true;
  }

  public static void printArray(int[] arr){
    if (null == arr){
      return;
    }
    for (int i = 0; i < arr.length; i++){
      System.out.print(arr[i] + " ");
    }

    System.out.println();
  }


  public static void swap(int[] arr, int index1, int index2){
    int i = arr[index1];
    arr[index1] = arr[index2];
    arr[index2] = i;
  }

  // 对数器
  public static void main(String[] args) {
    int testTime = 500000;
    int maxSize = 100;
    int maxValue = 100;
    boolean succeed = true;
    System.out.println("test started!");
    for (int i = 0; i < testTime; i++){
      int[] arr1 = generateRandomArray(maxSize, maxValue);
      int[] arr2 = copyArray(arr1);
      quickSort1(arr1);
      quickSort2(arr2);

      if (!equal(arr1, arr2)){
        succeed = false;
        printArray(arr1);
        printArray(arr2);
        break;
      }
    }

    System.out.println(succeed? "Nice!" : "Fucking fucked!");
  }

  // 快速排序3.0
  public static void quickSort1(int[] arr){
    if (null == arr || arr.length == 1){
      return;
    }
    process(arr, 0, arr.length - 1);

  }

  public static void process(int[] arr, int L, int R){
    if (L >= R){
      return;
    }
    // 随机方式去选择中间的数
    swap(arr, L + (int)(Math.random() * (R - L + 1)), R);

    int[] midArea = netherlandsFlag(arr, L, R);
    process(arr, L, midArea[0] - 1);
    process(arr, midArea[1] + 1, R);

  }

  /**
   * 荷兰国旗问题，将数组下标L~R之间分割成三份
   *
   * @param arr
   * @param L
   * @param R
   * @return
   */
  private static int[] netherlandsFlag(int[] arr, int L, int R) {
    // 首先确定左边界，也就是小于arr[R]的最右边的数
    // 然后就是右边界，也就是大于arr[R]的最左边的数


    // 因为还没有比较，所以初始是不存在的
    int lessL = L - 1;
    // 也是因为还没有比较，所以初始也是不存在的
    int moreR = R;
    int index = L;
    while(index < moreR){
      // 当索引碰到右边界的时候，就没必要比较下去了，因为moreR后面的数都比R大
      // 比较时，分三种情况 < = >
      if (arr[index] < arr[R]) {
        // 小于
        // 左边界往右移一位
        // 先交换 arr[++lessL], index++, 这边为什么是 ++lessL, 因为初始是不存在的，假定发现第一个比arr[R]小的数时，必须要先前自增，才能完成交换
        swap(arr, ++lessL, index++);
      } else if (arr[index] == arr[R]) {
        // 等于
        // 左右边界都不变，索引自增
        index ++;
      }else {
        // 大于
        // 右边界往左移一位
        // 把arr[index]的数先和arr[--moreR]交换，同理也是由于初始右边界不存在，得先前自减，再交换
        // 这边index不变，应为 arr[--moreR]数挪到arr[index]后，还没有跟arr[R]比较
        swap(arr, index, --moreR);
      }
    }

    // 此时； L ~ lessL 是小于 arr[R],  moreR~R-1是大于arr[R]的
    // 所以arr[R] 要跟 arr[moreR]的数进行交换
    swap(arr, moreR, R);
    return new int[]{lessL + 1, moreR};


  }


  /**
   * 快速排序3.0，非递归方式实现
   *
   * todo:
   *      递归的本质，其实是通过一层一层的栈结构实现的。
   *      所以非递归去实现，就得自己去模拟栈的结构是达到递归方法的调用
   *
   *
   * @param arr
   */
  public static void quickSort2(int[] arr){
    if (null == arr || arr.length < 2){
      return;
    }
//    if (null == arr || arr.length == 1){
//      return;
//    }
    int N = arr.length;
    try{
      swap(arr, (int)(Math.random() * N), N - 1);
    }catch (Exception e){

    }

    // 这边要自己定义一个实体类保存 荷兰国旗的非中间的区间
    // 下面定义的NetherlandsWhite类,
    // 定义一个栈
    Stack<NetherlandsNoWhite> stack = new Stack<>();

    int[] whiteArea = netherlandsFlag(arr, 0, N - 1);
    NetherlandsNoWhite netherlandsNoWhiteLeft = new NetherlandsNoWhite(0, whiteArea[0] - 1);
    NetherlandsNoWhite netherlandsNoWhiteRight = new NetherlandsNoWhite(whiteArea[1] + 1, N - 1);
    // 这边推入栈的顺序随意，因为，只需要关注每个区间数的顺序就行，他们是完全隔离的
    stack.push(netherlandsNoWhiteLeft);
    stack.push(netherlandsNoWhiteRight);
    while(!stack.isEmpty()){
      NetherlandsNoWhite nnp = stack.pop();
      if (nnp.left < nnp.right){
        int left = nnp.left;
        int right = nnp.right;
        // 跟上面的操作一样，先随机替换，再调用分区方法，然后再将分割下来的数据压栈
        swap(arr, left + (int)(Math.random() * (right - left + 1)), right);
        whiteArea = netherlandsFlag(arr, left, right);
        NetherlandsNoWhite netherlandsNoWhite1 = new NetherlandsNoWhite(left, whiteArea[0] - 1);
        NetherlandsNoWhite netherlandsNoWhite2 = new NetherlandsNoWhite(whiteArea[1] + 1, right);
        stack.push(netherlandsNoWhite1);
        stack.push(netherlandsNoWhite2);
      }
    }


  }

  /**
   * 代表荷兰国旗中间的白色，因为每次比较，都确定了中间的相等的数
   * 所以只需要记录白色左边或者右边的区域
   *
   */
  public static class NetherlandsNoWhite{

    public int left;

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

    public NetherlandsNoWhite(int left, int right) {
      this.left = left;
      this.right = right;
    }
  }

}
