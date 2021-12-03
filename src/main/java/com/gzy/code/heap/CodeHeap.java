package com.gzy.code.heap;

/**
 * description: CodeHeap date: 2021/12/2 1:30 下午
 * 堆是完全二叉树结构，
 * 1 完全二叉树：
 *   左节点一定有的二叉树结构叫做完全二叉树
 *  （即 有左节点时，右节点不一定有，但是右节点存在时，左节点也一定存在）
 * 2 堆有且只有两种堆：
 *   2.1 大根堆
 *   2.2 小根堆
 *
 *
 * @author: guizhenyu
 */
public class CodeHeap {

  /**
   * 自定义一个大根堆
   * 1. 简单实现存放数据
   *    堆具体的数据: [ 8, 7, 6, 5, 4, 2, 1]
   *    索引：index    0  1  2  3  4  5  6
   *        父(8, 0)
   *      /      \
   *   左子(7,1)   右子(8,2)
   *
   *   index_左子 = 2 * index_父 + 1  ---》    1 = 2 * 0 +1
   *   index_右子 = index_左子 + 1    ---》    2 = 1 + 1
   *
   *
   * 2. sort(int value)
   * 3. pop() 弹出根堆
   * 4. heapfy()
   *
   */

  public static class MyMaxHeap {

    /**
     * 这边采用数组的方式存放堆数据
     */
    private int[] heap;

    /**
     * 堆的最大容量
     */
    private final int limit;

    /**
     * 当前堆的实际大小，也可以理解成下标索引
     */
    private int heapSize;


    public MyMaxHeap(int limit){
      heap = new int[limit];
      this.limit = limit;
      this.heapSize = 0;
    }


    /**
     * 堆是否为空
     * @return
     */
    public boolean isEmpty(){
      return heapSize == 0;
    }

    /**
     * 堆是否存满
     * @return
     */
    public boolean isFull(){
      return heapSize == limit;
    }


    /**
     * 放入值
     * @param value
     */
    public void push(int value){
      // 先判断容量是否已经满了
      if (isFull()){
        throw new RuntimeException("heap is full");
      }

      heap[heapSize] = value;
      heapInsert(heap, heapSize++);

    }

    /**
     * 弹出堆顶数据
     */
    public int pop(){

      if (isEmpty()){
        throw new RuntimeException("heap is empty");
      }

      int value = heap[0];
      swap(heap, 0, --heapSize);
      heapfy(heap, 0, heapSize);

      return value;
    }

    /**
     * 数据放进来后，进行堆的排序，从最后一位向上比较
     */
    public void heapInsert(int[] arr, int index){

      while(arr[index] > arr[(index - 1) / 2]){

        swap(arr, index, (index - 1) / 2);
        index = (index - 1) / 2;

      }
    }


    /**
     * 根堆顶数据弹出后，最后一个数据从根堆顶往下比较
     */
    public void heapfy(int[] arr, int index, int heapSize){
      int left = index * 2 + 1;
      while (left < heapSize){
        if (arr[left] > arr[index]){

        }
//        int largest = arr[left] > arr[index]? left :
//                                              arr[left + 1] > arr[index]?left + 1:index;

        int largest = left + 1 < heapSize && arr[left + 1] >arr[left]?
            left + 1:left;

        if (largest == index){
          break;
        }

        swap(arr, index, largest);

        index = largest;

        left = index * 2 + 1;

      }


    }


    /**
     * 交换数据
     * @param heap
     * @param index1
     * @param index2
     */
    public void swap(int[] heap, int index1, int index2){

      heap[index1] = heap[index1] ^ heap[index2];
      heap[index2] = heap[index1] ^ heap[index2];
      heap[index1] = heap[index1] ^ heap[index2];

    }

    public static void main(String[] args) {
      int i = 10;
      int j = 11;

      i = i ^ j;

      j = i ^ j;

      i = i ^ j;

      System.out.println(i);
      System.out.println(j);
    }
  }



}
