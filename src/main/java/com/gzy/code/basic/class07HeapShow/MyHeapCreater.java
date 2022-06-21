package com.gzy.code.basic.class07HeapShow;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * description: MyHeapCreater date: 2022/6/21 13:42
 *
 * @author: guizhenyu
 */
public class MyHeapCreater<T> {

  private ArrayList<T> heap;
  private Map<T, Integer> indexMap;
  private int heapSize;

  private Comparator<? super T> cmp;

  public MyHeapCreater(Comparator<T> c){
    heap = new ArrayList<>();
    indexMap = new HashMap<>();
    heapSize = 0;
    cmp = c;
  }

  public boolean isEmpty(){
    return heapSize == 0;
  }


  public int size(){
    return heapSize;
  }

  public boolean contains(T obj){
    return indexMap.containsKey(obj);
  }

  public void push(T t){
    if (contains(t)){
      return;
    }






  }

  public void add(T t){
    indexMap.put(t,++heapSize);
    heap.add(t);
  }

  public void heapInsert(){
    int index = heapSize;
    while (index > 0 && cmp.compare(heap.get(index), heap.get((index - 1) / 2)) < 0){

      swap(index, (index - 1) / 2);

      index = (index - 1) / 2;

    }
  }

  private void swap(int index, int index1) {

    T t = heap.get(index);
    heap.set(index, heap.get(index1));
    heap.set(index1, t);
    indexMap.put(t, index1);
    indexMap.put(heap.get(index), index);

  }

  private void heapify(int index){
    int left = index * 2 + 1;
    while (left < heapSize){
      int largest = cmp.compare(heap.get(left), heap.get(left + 1)) < 0? left : left + 1;
      int swapIndex = cmp.compare(heap.get(left), heap.get(largest)) < 0? left : largest;

      if (swapIndex == index){
        break;
      }

      swap(index, largest);

      index = largest;
      left = index * 2 + 1;
    }
  }

}
