package com.gzy.code.basic.class14;

import java.util.HashSet;

/**
 * description: Light date: 2022/6/28 17:39
 *
 * @author: guizhenyu
 */
public class Light {

  /**
   * 给定一个字符串str，只由‘X’和‘.’两种字符构成。 ‘X’表示墙，不能放灯，也不需要点亮 ‘.’表示居民点，可以放灯，需要点亮 如果灯放在i位置，可以让i-1，i和i+1三个位置被点亮
   * 返回如果点亮str中所有需要点亮的位置，至少需要几盏灯
   */


  public static void main(String[] args) {
    int len = 20;
    int testTime = 100000;
    for (int i = 0; i < testTime; i++) {
      String test = randomString(len);
      int ans1 = minLight1(test);
      int ans2 = minLight2(test);
      if (ans1 != ans2) {
        System.out.println("oops!");
      }
    }
    System.out.println("finish!");
  }

  private static int minLight2(String test) {
    /**
     * 思路：
     * 贪心算法
     * 遍历索引 index
     * 碰到 X，直接跳过 index = index + 1
     * 碰到 .  直接点上灯，
     *                  但是要根据下一个位置的情况，决定往后跳几个位置
     *                  如果 index + 1 位置是X， index = index + 2
     *                  如果 index + 1 位置是.   index = index + 3
     */
//    if (test == null || test.length() == 0) {
//      return Integer.MAX_VALUE;
//    }
    char[] str = test.toCharArray();

    int i = 0;
    int light = 0;
    while (i < str.length) {
      if (str[i] == 'X') {
        i++;
      } else {
        light++;
        if (i + 1 == str.length) {
          break;
        } else {
          if (str[i + 1] == 'X') {
            i = i + 2;
          } else {
            i = i + 3;
          }

        }
      }
    }

    return light;

  }

  private static int minLight1(String road) {

    if (road == null || road.length() == 0) {
      return 0;
    }
    return process(road.toCharArray(), 0, new HashSet<>());
  }

  /**
   * 暴力递归
   * 到最后一位时，把所以的排序遍历一下，如果发现连续3个位置点是'.' 的地方都没有灯的情况，就证明永远都点亮不了，
   * 否则直接返回当前HashSet的size
   *
   * @param arr
   * @param index
   * @param lights
   * @return
   */
  private static int process(char[] arr, int index, HashSet<Integer> lights) {
    if (index == arr.length){
      for (int i = 0; i < index; i++){
        if (arr[i] != 'X'){
          if (!lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)){
            return Integer.MAX_VALUE;
          }
        }

      }

      return lights.size();
    }else {

      int no = process(arr, index + 1, lights);
      int yes = Integer.MAX_VALUE;
      if (arr[index] == '.'){
        lights.add(index);
        yes = process(arr, index + 1, lights);
        // 恢复现场
        lights.remove(index);
      }

      return Math.min(no, yes);
    }

  }

  // for test
  public static String randomString(int len) {
    char[] res = new char[(int) (Math.random() * len) + 1];
    for (int i = 0; i < res.length; i++) {
      res[i] = Math.random() < 0.5 ? 'X' : '.';
    }
    return String.valueOf(res);
  }

}



