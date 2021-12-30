package com.gzy.code.basic.class06Heap;

/**
 * description: MyHeap date: 2021/12/29 5:47 下午
 *
 * @author: guizhenyu
 */
public class MyHeap {

  /**
   * todo：
   *      1. 堆是一种完全二叉树结构
   *      2. 堆只要大根堆和小根堆两种结构
   *
   *
   *
   *
   *
   */


  public static class MaxHeap{
    private static int[] heap;
    private final int limit;
    private static int size;


    public MaxHeap(int limit) {
      heap = new int[limit];
      size = 0;
      this.limit = limit;
    }

    /**
     * 新增节点
     * 思路： 我们这边是大根堆结构
     * 新增的节点，首先放到最后一个，
     * 然后依次把最后一个节点跟他的父节点比较，如果比父节点大就跟父节点交换
     *
     *
     */
    public void push(int value) throws Exception {

      // 先看堆是否已经满了
      if (isFull()){
        throw new Exception("heap is full");
      }

      heap[size] = value;

      heapInsert(size++);

    }

    /**
     * 子节点的坐标等于  父节点 * 2 + 1 或者 父节点 * 2 + 2
     * 父节点坐标 = （子节点坐标 - 1）/ 2
     * @param index
     */
    private void heapInsert(int index) {
      // 第一个数
//      if (index == 0){
//        return;
//      }

      while (index > 0){
        //计算父节点坐标
        int parentIndex = (index - 1) / 2;

        if (heap[parentIndex] < heap[index]){
         swap(heap, parentIndex, index);
         index = parentIndex;
        }
      }
    }


    /**
     * 当前节点往下沉
     * 用于从堆中取出堆根的数
     *
     * @param index
     * @param size
     */
    private  void heapify(int index, int size){

      int left = index * 2 + 1;

      while (left < size){
        int largest = left;
        // 如果子节点有右节点，就取一个比较大的节点跟index比较
        if (left + 1 <= size){
          largest = heap[left] > heap[left + 1]? left : left+1;
        }
        if (heap[index] >= heap[largest]){
          break; //这边判断一下，如果已经比子节点最大的数大了，就没必要继续往下比较了，直接停止循环
        }
        swap(heap, index, largest);
        index = largest;
        left = index * 2 + 1;
      }

    }

    private  boolean isFull() {
      return size < limit;
    }

    /**
     * 弹出堆顶数据
     *
     */

    public  int pop() throws Exception {
      if (isEmpty()){
        throw new Exception("堆中数据为空");
      }
      // 将堆顶数据和最后一个数据进行交换，然后把最后一个数据返回
      // 然后从堆顶开始 heapify
      swap(heap, 0, size - 1);
      int ans = heap[--size];
      heapify(0, size);
      return ans;
    }

    private  boolean isEmpty() {
      return size == 0;
    }
    public  void swap(int[] arr, int index1, int index2){
      int i = arr[index1];
      arr[index1] = arr[index2];
      arr[index2] = i;
    }


  }



}
