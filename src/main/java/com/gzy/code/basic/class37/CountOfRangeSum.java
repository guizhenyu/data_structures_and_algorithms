package com.gzy.code.basic.class37;

import java.util.HashSet;
import java.util.Set;

/**
 * description: CountofRangeSum date: 2022/5/25 12:01
 *
 * @author: guizhenyu
 */
public class CountOfRangeSum {


  public static void main(String[] args) {
    int maxLen = 500;
    int maxValue = 50;
    int testTime = 10000;
    for (int i = 0; i < testTime; i++){
      int[] arr = generateRandomArray(maxLen, maxValue);
      int lower = (int)(Math.random() * maxValue - Math.random() * maxValue);
      int upper = lower + (int)(Math.random() * maxValue);
      int ans1 = countOfRangeSum1(arr, lower, upper);
      int ans2 = countOfRangeSum2(arr, lower, upper);
      if (ans1 != ans2){
        int i1 = countRangeSum0(arr, lower, upper);
        System.out.println(ans1);
        System.out.println(ans2);
        System.out.println(lower);
        System.out.println(upper);
        break;
      }
    }

  }
  public static int countRangeSum0(int[] nums, int lower, int upper) {
    int n = nums.length;
    long[] sums = new long[n + 1];
    for (int i = 0; i < n; ++i){
      sums[i + 1] = sums[i] + nums[i];}
    return countWhileMergeSort0(sums, 0, n + 1, lower, upper);
  }

  private static int countWhileMergeSort0(long[] sums, int start, int end, int lower, int upper) {
    if (end - start <= 1){
      return 0;}
    int mid = (start + end) / 2;
    int count = countWhileMergeSort0(sums, start, mid, lower, upper)
        + countWhileMergeSort0(sums, mid, end, lower, upper);
    int j = mid, k = mid, t = mid;
    long[] cache = new long[end - start];
    for (int i = start, r = 0; i < mid; ++i, ++r) {
      while (k < end && sums[k] - sums[i] < lower){
        k++;}
      while (j < end && sums[j] - sums[i] <= upper){
        j++;}
      while (t < end && sums[t] < sums[i]){
        cache[r++] = sums[t++];}
      cache[r] = sums[i];
      count += j - k;
    }
    System.arraycopy(cache, 0, sums, start, t - start);
    return count;
  }


  private static int countOfRangeSum2(int[] arr, int lower, int upper) {

    SBTListMap sbt = new SBTListMap();
    long sum = 0;
    sbt.add(0);
    long ans = 0;
    for (int i = 0; i < arr.length; i++){
      sum += arr[i];
      long countUpper = sbt.lessKeySize(sum - lower + 1);
      long countLower = sbt.lessKeySize(sum - upper);

      ans += countUpper - countLower;
      sbt.add(sum);
    }
    return (int)ans;
  }


  private static int countOfRangeSum1(int[] arr, int lower, int upper) {
    if (arr == null || arr.length == 0){
      return 0;
    }

    long[] sums = new long[arr.length];
    sums[0] = arr[0];
    for(int i = 1; i < arr.length; i++){
      sums[i] = sums[i - 1] + arr[i];
    }

    return countWhileMergeSort(sums, 0, arr.length - 1, lower, upper);

  }

  private static int countWhileMergeSort(long[] sums, int l, int r, int lower, int upper) {
    if (l == r){
      return (sums[l] >= lower && sums[l] <= upper)? 1 : 0;
    }
    int mid = (l + r) >> 1;
    return countWhileMergeSort(sums, l, mid, lower, upper) + countWhileMergeSort(sums, mid + 1, r, lower, upper)
        + merge(sums, l, mid, r, lower, upper);
  }

  private static int merge(long[] sums, int l, int mid, int r, int lower, int upper) {
    int ans = 0;
    int rightStart = mid + 1;
    int windowL = l;
    int windowR = l;
    // 虽然是循环嵌套循环，但是他是个窗口不回退的逻辑，总计的遍历次数是 r - l, 时间复杂度: O(N)
    while (rightStart <= r){
      long leftMax = sums[rightStart] - lower;
      long leftMin = sums[rightStart] - upper;

      while (windowR <= mid && sums[windowR] <= leftMax){
        windowR++;
      }

      while (windowL <= mid && sums[windowL] < leftMin){
        windowL++;
      }
      ans += windowR - windowL;
      rightStart++;
    }

    long[] helpArr = new long[r - l + 1];
    int left = l;
    int right = mid + 1;
    int helpIndex = 0;
    while (left <= mid && right <= r){
      if (sums[left] <= sums[right]){
        helpArr[helpIndex++] = sums[left++];
      }else {
        helpArr[helpIndex++] = sums[right++];
      }
    }

    while (left <= mid){
      helpArr[helpIndex++] = sums[left++];
    }
    while (right <= r){
      helpArr[helpIndex++] = sums[right++];
    }

    for (int i = 0; i < helpArr.length; i++){
      sums[l + i] = helpArr[i];
    }

    return ans;
  }

  public static int[] generateRandomArray(int maxLen, int maxValue){
    int[] ans = new int[maxLen];

    for (int i = 0; i < maxLen; i++){
      ans[i] = (int)(maxValue * Math.random()) - (int)(maxValue * Math.random());;
    }
    return ans;
  }


  public static class SBTNode{
    private long key;
    private SBTNode l;
    private SBTNode r;
    private int keySize;
    private int totalSize;

    public SBTNode(long key){
      this.key = key;
      keySize = 1;
      totalSize = 1;
    }
  }

  public static class SBTListMap{
    private SBTNode root;

    private Set<Long> keySet = new HashSet<>();

    public SBTNode rightRotate(SBTNode cur){
      int curSameKey = cur.totalSize -
          (cur.r == null? 0 : cur.r.totalSize) -
          (cur.l == null? 0 : cur.l.totalSize);

      SBTNode left = cur.l;
      cur.l = left.r;
      left.r = cur;

      left.totalSize = cur.totalSize;
      left.keySize = cur.keySize;
      cur.totalSize = curSameKey +
          (cur.l == null? 0 : cur.l.totalSize) +
          (cur.r == null? 0 : cur.r.totalSize);
      cur.keySize = (cur.l == null? 0 : cur.l.keySize) +
          (cur.r == null? 0 : cur.r.keySize) + 1;
      return left;
    }



    public SBTNode leftRotate(SBTNode cur){
      int curSameKey = cur.totalSize -
          (cur.r == null? 0 : cur.r.totalSize) -
          (cur.l == null? 0 : cur.l.totalSize);

      SBTNode right = cur.r;
      cur.r = right.l;
      right.l = cur;
      right.totalSize = cur.totalSize;
      right.keySize = cur.keySize;
      cur.totalSize = curSameKey +
          (cur.l == null? 0 : cur.l.totalSize) +
          (cur.r == null? 0 : cur.r.totalSize);
      cur.keySize = (cur.l == null? 0 : cur.l.keySize) +
          (cur.r == null? 0 : cur.r.keySize) + 1;

      return right;
    }

    public void add(long key){
      boolean contains = keySet.contains(key);
      root = add(root, key,  contains);
      keySet.add(key);
    }




    public SBTNode add(SBTNode cur, long key, boolean contains){
      if (null == cur){
        return new SBTNode(key);
      }

      cur.totalSize++;

      if (key == cur.key){
        return cur;
      }else{
        if (!contains){
          cur.keySize++;
        }
        if (key - cur.key > 0){
          cur.r = add(cur.r, key, contains);
        }else {
          cur.l = add(cur.l, key,contains);
        }
      }

      return maintain(cur);
    }

    private SBTNode maintain(SBTNode cur) {
      if (cur == null){
        return null;
      }
      int lc = cur.l == null? 0 : cur.l.keySize;
      int rc = cur.r == null? 0 : cur.r.keySize;
      int llc = cur.l == null? 0 : cur.l.l == null? 0 : cur.l.l.keySize;
      int lrc = cur.l == null? 0 : cur.l.r == null? 0 : cur.l.r.keySize;
      int rlc = cur.r == null? 0 : cur.r.l == null? 0 : cur.r.l.keySize;
      int rrc = cur.r == null? 0 : cur.r.r == null? 0 : cur.r.r.keySize;

      if (llc > rc){
        // 头节点右旋
        cur = rightRotate(cur);
        cur.r = maintain(cur.r);
        cur = maintain(cur);
      }else if(lrc > rc){
        // 先左旋再右旋
        cur.l = leftRotate(cur.l);
        cur = rightRotate(cur);
        cur.l = maintain(cur.l);
        cur.r = maintain(cur.r);
        cur = maintain(cur);
      }else if(rrc > lc){
        cur = leftRotate(cur);
        cur.l = maintain(cur.l);
        cur = maintain(cur);
      }else if(rlc > lc){
        cur.r = rightRotate(cur.r);
        cur = leftRotate(cur);
        cur.r = maintain(cur.r);
        cur.l = maintain(cur.l);
        cur = maintain(cur);
      }
      return cur;
    }

    public long lessKeySize(long key) {

      int ans = 0;

      SBTNode next = root;

      while (next != null){

        if (next.key < key){

          ans += next.totalSize - (next.r == null? 0 : next.r.totalSize);
          next = next.r;
        }else if (next.key > key){
          next = next.l;
        }else {
          ans += next.l == null? 0 : next.l.totalSize;
          break;
        }

      }

      return ans;
    }

//    public SBTNode delete(SBTNode cur, K key){
//      if (key.compareTo(cur.key) > 0){
//        cur.keySize--;
//        cur.totalSize--;
//        cur = delete(cur.r, key);
//      }else if(key.compareTo(cur.key) < 0){
//        cur.keySize--;
//        cur.totalSize--;
//        cur = delete(cur.l, key);
//      }else {
//        if (cur.l == null && cur.r == null){
//          cur = null;
//        }else if(cur.l != null && cur.r == null){
//          cur = cur.l;
//        }else if(cur.l == null && cur.r != null){
//          cur = cur.r;
//        }else {
//          // 两个节点都有，取右节点的最左节点替换当前节点
//          //todo: 这边由于节点的key有重复，不能直接这么处理
//
//          SBTNode mostRightLeft = cur.r;
//          SBTNode des = mostRightLeft;
//          while (mostRightLeft != null){
//            mostRightLeft.totalSize--;
//            mostRightLeft.keySize--;
//            des = mostRightLeft;
//            mostRightLeft = mostRightLeft.l;
//          }
//          int sameKeyOfCur = cur.totalSize - cur.l.totalSize - cur.r.totalSize;
//          des.l = cur.l;
//          des.r = cur.r;
//          des.keySize = cur.keySize - 1;
//          des.totalSize = cur.totalSize - sameKeyOfCur;
//        }
//      }
//
//      return cur;
//    }

  }


}
