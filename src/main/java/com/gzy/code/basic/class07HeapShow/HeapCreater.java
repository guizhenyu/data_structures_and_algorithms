package com.gzy.code.basic.class07HeapShow;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * description: HeapCreater date: 2022/1/5 9:28 上午
 *
 * @author: guizhenyu
 */
public class HeapCreater<T> {

  /**
   * todo:
   *      堆的功能增加，记录节点位置，和删除功能
   *
   *
   *
   */

  private ArrayList<T> heap;

  private HashMap<T, Integer> indexMap;

  private int heapSize;

  /**
   * 定义排序规则
   */
  private Comparator<? super T> comp;


  public HeapCreater(Comparator<T> c){
    heap = new ArrayList<>();
    indexMap = new HashMap<>();
    heapSize = 0;
    comp = c;
  }

  /**
   * 待实现方法：
   * 1. 判断堆是否为空
   * 2. 新增
   * 3. 删除
   * 4. 堆的大小
   * 5. 取出堆顶数据
   * 6. 查询堆顶数据
   * 7. 返回堆内的所有数据
   * 8. 交换位置
   *
   */

  public boolean isEmpty(){
    return heapSize == 0;
  }

  public int size(){
    return heapSize;
  }

  public boolean contains(T obj){
    return indexMap.containsKey(obj);
  }

  /**
   * 查询堆顶数据
   * @return
   */
  public T peek(){
    return heapSize == 0 ? null : heap.get(0);
  }

  /**
   * 取出堆顶数据
   *
   * @return
   */
  public T pop(){
    T ans = heap.get(0);
    swap(0, heap.size() - 1);
    indexMap.remove(ans);
    heap.remove(--heapSize);
    heapify(0);
    return ans;
  }

  /**
   * 从当前的索引往下进行堆化
   *
   * @param index
   */
  private void heapify(int index) {

    int left = index * 2 + 1;

    while (index < heapSize){

      int best = left + 1 < heapSize && comp.compare(heap.get(left + 1), heap.get(left)) < 0 ? left + 1 : left;

      best = comp.compare(heap.get(index), heap.get(best)) < 0 ? index : best;

      if (index == best){
        break;
      }

      swap(best, index);
      index = best;
      left = index * 2 + 1;
    }

  }

  private void swap(int index1, int index2) {
    T obj1 = heap.get(index1);
    T obj2 = heap.get(index2);

    heap.set(index1, obj1);
    heap.set(index1, obj2);

    indexMap.put(obj1, index2);
    indexMap.put(obj2, index1);
  }

  public void push(T obj){
    heap.add(obj);
    indexMap.put(obj, heapSize);
    heapInsert(heapSize++);
  }

  public void remove(T obj){
    Integer index = indexMap.get(obj);
    if (index == null){
      return;
    }
    T replaceObj = heap.get(heapSize - 1);
    heap.remove(--heapSize);

    if (replaceObj != obj){
      heap.set(index, replaceObj);
      indexMap.put(replaceObj, index);
      heapInsert(index);
      heapify(index);
    }




  }

  private void heapInsert(int index) {

    while (comp.compare(heap.get(index), heap.get((index - 1) / 2)) < 0){
      swap(index, (index - 1) / 2);
      index = (index - 1) / 2;
    }
  }

  private void heapInsert_low(int index) {

    int parentIndex = (index - 1) >> 1;

    while(parentIndex >= 0){
      int swapIndex = index = comp.compare(heap.get(parentIndex), heap.get(index)) < 0 ? parentIndex : index;
      if (index == swapIndex){
        break;
      }
      swap(swapIndex, index);
      index = swapIndex;
      parentIndex = (index - 1) >> 1;
    }
  }

  // 请返回堆上的所有元素
  public List<T> getAllElements() {
    List<T> ans = new ArrayList<>();
    for (T c : heap) {
      ans.add(c);
    }
    return ans;
  }
}
