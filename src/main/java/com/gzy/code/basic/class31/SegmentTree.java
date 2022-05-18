package com.gzy.code.basic.class31;

/**
 * description: SegmentTree date: 2022/5/11 15:25
 *
 * @author: guizhenyu
 */
public class SegmentTree {


  /**
   * todo 线段树总结
   *      本质上是利用二叉树+懒更新方式实现了查询货更新的高效，时间复杂度是 O(logN)
   *
   *
   *
   */
  // 代表数组的长度 len + 1, 因为是从下标1开始的
  private int MAXN;
  // 从下标1开始存储原始数组中的值
  private int[] arr;
  // 代表已下标为父节点，所有子节点的和
  private int[] sum;
  // 代表懒加载的数值
  private int[] lazy;
  // 代表懒加载的 变化值
  private int[] change;
  // 代表该索引对应的该表值状态是否有效
  private boolean[] update;


  public SegmentTree(int[] origin){
    MAXN = origin.length + 1;

    arr = new int[MAXN];

    for (int i = 1; i < MAXN; i++){
      arr[i] = origin[i - 1];
    }
    sum = new int[MAXN << 2];
    lazy = new int[MAXN << 2];
    change = new int[MAXN << 2];
    update = new boolean[MAXN << 2];

  }

  private void pushUp(int rt) {
    sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
  }

  /**
   * 父节点往子节点分发
   *
   * @param rt 父节点
   * @param lLen 左子节点分发的长度 也就是默认为左节点代表的区间范围
   * @param rLen 右子节点分发的长度 也就是默认为右节点代表的区间范围
   */
  private void pushDown(int rt, int lLen, int rLen) {
    if (update[rt]){
      update[rt << 1] = true;
      update[rt << 1 | 1] = true;
      change[rt << 1] = change[rt];
      change[rt << 1 | 1] = change[rt];
      sum[rt << 1] = change[rt] * lLen;
      lazy[rt << 1] = 0;
      sum[rt << 1 | 1] = change[rt] * rLen;
      lazy[rt << 1 | 1] = 0;
//      lazy[rt] = 0;  只处理下一层的
      update[rt] = false;

    }

    // 由于 在执行update的时候，会覆盖之前的add缓存， 所以update执行完后，再执行add的lazy操作。
    // 注意：这个情况是由于，本次操作之前 已经先执行了update操作，然后又进行了add,而之前的两个操作在同一个区间都触发了lazy操作，
    // 而后面的操作，又需要在改区间触发lazy和change的下发
    if (lazy[rt] != 0){
      sum[rt << 1] += lLen * lazy[rt];
      lazy[rt << 1] += lazy[rt];
      sum[rt << 1 | 1] += rLen * lazy[rt];
      lazy[rt << 1 | 1] += lazy[rt];
      lazy[rt] = 0;
    }

  }

  public void build(int l, int r, int rt){
    if (l == r){
      sum[rt] = arr[l];
      return;
    }

    int mid = (l + r) >> 1;
    build(l, mid, rt << 1);
    build(mid + 1, r, rt << 1 | 1);
    pushUp(rt);
  }


  public void update(int L, int R, int C, int l, int r, int rt){

    if (L <= l && r <= R){
      update[rt] = true;
      change[rt] = C;
      sum[rt] = C * (r - l + 1);
      lazy[rt] = 0;
      return;
    }

    int mid = (l + r) >> 1;
    pushDown(rt, mid - l + 1, r - mid);
    if (L <= mid){
      update(L, R, C, l, mid, rt << 1);
    }

    if (R > mid){
      update(L, R, C, mid + 1, r, rt << 1 | 1);
    }

    pushUp(rt);
  }

  /**
   * 以rt为根父节点， 从下标L到R,每个位置增加C
   *
   * @param L 需要add的左边界
   * @param R 需要add的右边界
   * @param C 需要add的值
   * @param l 第一次调用默认是1， 后面会根据情况更新， 其实可以看成不变的，因为不是人为传参控制的
   * @param r 第一次调用默认MAXN - 1，同上
   * @param rt 第一次调用传的是1，同上
   */
  public void add(int L, int R, int C, int l, int r, int rt){
    if (L <= l && r <= R){
      // 完全被包裹，直接懒加载
      sum[rt] += C * (r - l + 1);
      lazy[rt] += C;
      return;
    }
    // 不完全包括，就得下发
    int mid = (l + r) >> 1;
    // 下发的具体操作，也就是触发下一层的懒加载
    pushDown(rt, mid - l + 1, r - mid);

    if (L <= mid){
      add(L, R, C, l, mid, rt << 1);
    }
    if (R > mid){
      add(L, R, C, mid  + 1, r, rt << 1 |1 );
    }
    pushUp(rt);
  }





  public int query(int L, int R, int l, int r, int rt){
    if (L <= l && r <= R){
      return sum[rt];
    }
    int ans = 0;
    int mid = (l + r) >> 1;
    pushDown(rt, mid - l + 1, r - mid);
    if (L <= mid){
      ans += query(L, R, l, mid, rt << 1);
    }

    if (R > mid){
      ans += query(L, R, mid + 1, r, rt << 1 | 1);
    }

    return ans;
  }

//  public static void main(String[] args) {
//    boolean flag = true;
//    if (flag){
////      test();
//
//      int len = 100;
//      int max = 1000;
//      int testTimes = 5000;
//      int addOrUpdateTimes = 1000;
//      int queryTimes = 500;
//      for (int i = 0; i < testTimes; i++) {
//        int[] origin = generateRandomArr(len, max);
//        SegmentTree seg = new SegmentTree(origin);
//        int S = 1;
//        int N = origin.length;
//        int root = 1;
//        seg.build(S, N, root);
//        RightClass rig = new RightClass(origin);
//        for (int j = 0; j < addOrUpdateTimes; j++) {
//          int num1 = (int) (Math.random() * N) + 1;
//          int num2 = (int) (Math.random() * N) + 1;
//          int L = Math.min(num1, num2);
//          int R = Math.max(num1, num2);
//          int C = (int) (Math.random() * max) - (int) (Math.random() * max);
//          int ans1 = seg.query(L, R, S, N, root);
//          int ans2 = rig.query(L, R);
//          if (ans2 != ans1) {
//            System.out.println("ans1: " + ans1);
//            System.out.println("ans2: " + ans2);
//            System.out.println("oops!");
//          }
//        }
//
////        for (int k = 0; k < queryTimes; k++) {
//////        int L = (int)(Math.random() * N) + 1;
//////        int R = (int)(Math.random() * (N - L)) + L;
////          int num1 = (int) (Math.random() * N) + 1;
////          int num2 = (int) (Math.random() * N) + 1;
////          int L = Math.min(num1, num2);
////          int R = Math.max(num1, num2);
////          int ans = seg.query(L, R, S, N, root);
////          int ans1 = rig.query(L, R);
////          if (ans != ans1) {
////            System.out.println("ans1: " + ans);
////            System.out.println("ans2: " + ans1);
////            System.out.println("oops!");
////          }
////        }
//      }
//    }
//
//
//
//  }
  public static void main(String[] args) {
    boolean flag = true;
    if (flag){
      test();

      int len = 100;
      int max = 1000;
      int testTimes = 5000;
      int addOrUpdateTimes = 1000;
      int queryTimes = 500;
      for (int i = 0; i < testTimes; i++) {
        int[] origin = generateRandomArr(len, max);
        SegmentTree seg = new SegmentTree(origin);
        int S = 1;
        int N = origin.length;
        int root = 1;
        seg.build(S, N, root);
        RightClass rig = new RightClass(origin);
        for (int j = 0; j < addOrUpdateTimes; j++) {
//        int L = (int)(Math.random() * N) + 1;
//        int R = (int)(Math.random() * (N - L)) + L;
          int num1 = (int) (Math.random() * N) + 1;
          int num2 = (int) (Math.random() * N) + 1;
          int L = Math.min(num1, num2);
          int R = Math.max(num1, num2);
          int C = (int) (Math.random() * max) - (int) (Math.random() * max);

        if (Math.random() <= 0.5){
          seg.update(L, R, C, S, N, root);
          rig.update(L, R, C);
        }else {
          seg.add(L, R, C, S, N, root);
          rig.add(L, R, C);
        }

          int ans1 = seg.query(L, R, S, N, root);
          int ans2 = rig.query(L, R);
          if (ans2 != ans1) {
            System.out.println("ans1: " + ans1);
            System.out.println("ans2: " + ans2);
            System.out.println("oops!");
          }
        }

//        for (int k = 0; k < queryTimes; k++) {
////        int L = (int)(Math.random() * N) + 1;
////        int R = (int)(Math.random() * (N - L)) + L;
//          int num1 = (int) (Math.random() * N) + 1;
//          int num2 = (int) (Math.random() * N) + 1;
//          int L = Math.min(num1, num2);
//          int R = Math.max(num1, num2);
//          int ans = seg.query(L, R, S, N, root);
//          int ans1 = rig.query(L, R);
//          if (ans != ans1) {
//            System.out.println("ans1: " + ans);
//            System.out.println("ans2: " + ans1);
//            System.out.println("oops!");
//          }
//        }
      }
    }



  }

  private static int[] generateRandomArr(int len, int max) {
    int n = (int)(Math.random() * len) + 1;
    int[] arr = new int[n];
    for (int i = 0; i < n; i++){
      arr[i] = (int)(Math.random() * max) - (int)(Math.random() * max);
    }
    return arr;
  }
  public static boolean test() {
    int len = 100;
    int max = 1000;
    int testTimes = 5000;
    int addOrUpdateTimes = 1000;
    int queryTimes = 500;
    for (int i = 0; i < testTimes; i++) {
      int[] origin = generateRandomArr(len, max);
      SegmentTree seg = new SegmentTree(origin);
      int S = 1;
      int N = origin.length;
      int root = 1;
      seg.build(S, N, root);
      RightClass rig = new RightClass(origin);
      for (int j = 0; j < addOrUpdateTimes; j++) {
        int num1 = (int) (Math.random() * N) + 1;
        int num2 = (int) (Math.random() * N) + 1;
        int L = Math.min(num1, num2);
        int R = Math.max(num1, num2);
        int C = (int) (Math.random() * max) - (int) (Math.random() * max);
        long ans1 = seg.query(L, R, S, N, root);
        long ans2 = rig.query(L, R);
        if (ans1 != ans2){
          System.out.println("ans1: " + ans1);
          System.out.println("ans2: " + ans2);
          System.out.println("oops!");
        }
      }
    }
    return true;
  }
//  public static boolean test() {
//    int len = 100;
//    int max = 1000;
//    int testTimes = 5000;
//    int addOrUpdateTimes = 1000;
//    int queryTimes = 500;
//    for (int i = 0; i < testTimes; i++) {
//      int[] origin = generateRandomArr(len, max);
//      SegmentTree seg = new SegmentTree(origin);
//      int S = 1;
//      int N = origin.length;
//      int root = 1;
//      seg.build(S, N, root);
//      RightClass rig = new RightClass(origin);
//      for (int j = 0; j < addOrUpdateTimes; j++) {
//        int num1 = (int) (Math.random() * N) + 1;
//        int num2 = (int) (Math.random() * N) + 1;
//        int L = Math.min(num1, num2);
//        int R = Math.max(num1, num2);
////				int L = (int)(Math.random() * N) + 1;
////				int R = (int)(Math.random() * (N - L)) + L;
//        int C = (int) (Math.random() * max) - (int) (Math.random() * max);
////        if (Math.random() < 0.5) {
////          seg.add(L, R, C, S, N, root);
////          rig.add(L, R, C);
////        } else {
////          seg.update(L, R, C, S, N, root);
////          rig.update(L, R, C);
////        }
//        long ans1 = seg.query(L, R, S, N, root);
//        long ans2 = rig.query(L, R);
//        if (ans1 != ans1){
//          System.out.println("ans1: " + ans1);
//          System.out.println("ans2: " + ans2);
//          System.out.println("oops!");
//        }
//      }
////      for (int k = 0; k < queryTimes; k++) {
////        int num1 = (int) (Math.random() * N) + 1;
////        int num2 = (int) (Math.random() * N) + 1;
////        int L = Math.min(num1, num2);
////        int R = Math.max(num1, num2);
//////				int L = (int)(Math.random() * N) + 1;
//////				int R = (int)(Math.random() * (N - L)) + L;
////        long ans1 = seg.query(L, R, S, N, root);
////        long ans2 = rig.query(L, R);
////        if (ans1 != ans2) {
////          return false;
////        }
////      }
//    }
//    return true;
//  }

}

class RightClass{
  int[] arr;

  public RightClass(int[] origin){
    arr = new int[origin.length + 1];
    for (int i = 1; i < arr.length; i++){
      arr[i] = origin[i - 1];
    }
  }

  public void update(int l, int r, int c){
    for (int i = l;i <= r; i++){
      arr[i] = c;
    }
  }

  public void add(int l, int r, int c){
    for (int i = l;i <= r; i++){
      arr[i] += c;
    }
  }

  public int query(int l, int r){
    int ans = 0;
    for (int i = l; i <= r; i++){
      ans += arr[i];
    }
    return ans;
  }
}
