package com.gzy.code.basic.class07HeapShow;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * description: CoverMax date: 2021/12/31 8:53 上午
 *
 *
 * 给定很多线段，每个线段都有两个数[start, end]，
 * 表示线段开始位置和结束位置，左右都是闭区间
 * 规定：
 * 1）线段的开始和结束位置一定都是整数值
 * 2）线段重合区域的长度必须>=1
 * 返回线段最多重合区域中，包含了几条线段
 * @author: guizhenyu
 */
public class CoverMax {



  /**
   * 思路：
   * todo:
   *      1.现将线段按照start进行排序
   *        思考：这样排完序后的好处
   *      2.准备一个小根堆结，用于存放线段的 end 数
   *      3.遍历排好序的线段
   *        3.1 线段的start跟小根堆的堆顶数进行比较：
   *            3.1.1 如果是小根堆是空的，直接把该线段end放进去
   *            3.1.2 start < 小根堆的堆顶数
   *                  说明当前线段跟小根堆中的end结尾的线段还有重合，并且重合长度 > 1
   *                  所以直接把当前线段的end放入小根堆当中
   *            3.1.3 start >= 小根堆的堆顶数
   *                  说明当前线段跟小根堆的堆顶线段没有重合点，
   *                  所以要弹出堆顶的数，继续比较下一个堆顶的数，直到找到小于的，或者全部弹出
   *        3.2 每次比较完后，获取堆的大小，就是当前线段跟其他线段重合的线段数
   *
   *
   */

  /**
   * 定义一个线段对象
   * int start
   * int end
   *
   */

  public static class Line{
    private int start;

    private int end;

    public Line(int start, int end) {
      this.start = start;
      this.end = end;
    }
  }


  /**
   * 头结点的比较
   *
   */
  public static class StartComparator implements Comparator<Line> {

    @Override
    public int compare(Line o1, Line o2) {
      return o1.start - o2.start;
    }
  }

  /**
   *  heap function
   *
   *
   * @param lines
   * @return
   */
  public static int maxCover2(int[][] lines){
    int ans = 0;
    Line[] lineList = new Line[lines.length];
    for (int i = 0; i < lines.length; i++){
      lineList[i] = new Line(lines[i][0], lines[i][1]);
    }
    Arrays.sort(lineList, new StartComparator());

    PriorityQueue<Integer> heap = new PriorityQueue<>();

    for(int i = 0; i < lineList.length; i++){

      Line line = lineList[i];
      while(!heap.isEmpty() && heap.peek() <= line.start) {
        heap.poll();
      }
      heap.add(line.end);
      ans = ans > heap.size()? ans:heap.size();
    }

    return ans;
  }

  /**
   * nature wisdom function
   *
   * @param lines
   * @return
   */
  public static int maxCover1(int[][] lines){

    int min = Integer.MAX_VALUE;

    int max = Integer.MIN_VALUE;

    for (int i = 0; i < lines.length; i++){
      min = Math.min(min, lines[i][0]);
      max = Math.max(max, lines[i][1]);
    }

    int ans = 0;
    for (double p = min + 0.5; p < max; p++){
      int count = 0;
      for (int j = 0; j < lines.length; j++) {
        if (p > lines[j][0] && p < lines[j][1]){
          count++;
        }
      }
      ans = Math.max(ans, count);
    }
    return ans;
  }

  /**
   * 随机生成线段
   *
   * @param maxSize
   * @param L
   * @param R
   * @return
   */
  public static int[][] generateLines(int maxSize, int L, int R){
    int size = (int)(Math.random() * (maxSize)) + 1;

    int[][] lines = new int[size][2];

    for (int i = 0; i < size; i++){
      int a = L + (int)(Math.random() * (R - L)) + 1;
      int b = L + (int)(Math.random() * (R - L)) + 1;
      if (a == b){
        a = a + 1;
      }
      lines[i][0] = Math.min(a, b);
      lines[i][1] = Math.max(a, b);
    }

    return lines;

  }

  public static void main(String[] args) {
    int testTime = 20000;
    int maxSize = 100;
    int l = 0;
    int r = 200;

    System.out.println("test started!");
    for (int i = 0; i < testTime; i++){
      int[][] lines = generateLines(maxSize, l, r);
      int ans1 = maxCover1(lines);
      int ans2 = maxCover2(lines);

      if (ans1 != ans2){
        System.out.println("ops");
        return;
      }

    }

    System.out.println("success!");


  }





















}
