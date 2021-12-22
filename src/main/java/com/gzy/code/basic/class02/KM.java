package com.gzy.code.basic.class02;

import java.util.HashMap;
import java.util.HashSet;

/**
 * description: KM date: 2021/12/22 2:13 下午
 * 数组中一种数出现k次，其他数出现M次， k < m次
 * @author: guizhenyu
 */
public class KM {

  public static int onlyTimes(int[] arr, int k, int m){


    createMap();


    int[] rightOnes = new int[32];

    for(int i = 0; i < arr.length; i++){

      while(arr[i] != 0){
        int rightOne = arr[i] & (-arr[i]);
        rightOnes[map.get(rightOne)]++;
        arr[i] ^= rightOne;
      }

    }

    int ans = 0;
    for (int j = 0;j< rightOnes.length;j++){

      if (rightOnes[j] % m != 0){
        if(rightOnes[j] % m == k){
          ans |= (1 << j);
        }else{
          return -1;
        }

      }
    }

    if (ans == 0){
      int count = 0;
      for (int num : arr){
        if (num == 0){
          count++;
        }

      }
      if (count != k){
        return -1;
      }
    }
    return ans;
  }


  // 这个map的，key是二进制的1代表的数，value代表的是第几位1
  public static HashMap<Integer, Integer> map = new HashMap<>();

  public static void createMap(){
    int num = 1;
    for (int i = 0; i < 32; i++){
      map.put(num, i);
      num = num << 1;
    }
  }

  public static int test11(int[] arr, int k, int m) {
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int num : arr) {
      if (map.containsKey(num)) {
        map.put(num, map.get(num) + 1);
      } else {
        map.put(num, 1);
      }
    }
    for (int num : map.keySet()) {
      if (map.get(num) == k) {
        return num;
      }
    }
    return -1;
  }


  public static int[] generateRandomArray(int maxKinds, int range, int k, int m){

    int ktimeNum = randomNumber(range);

    int times = Math.random() < 0.5?k:((int) (Math.random() * (m-1)) + 1);

    int numkinds = (int) (Math.random() * maxKinds) + 2;

    int[] arr = new int[times + (numkinds - 1) *m];

    int index = 0;

    for (;index < times; index++){
      arr[index] = ktimeNum;
    }
    numkinds --;

    HashSet<Integer> set = new HashSet<>();

    set.add(ktimeNum);
    while(numkinds > 0){
      int curnum = 0;
      do{
        curnum = randomNumber(range);
      }while (set.contains(curnum));

      set.add(curnum);
      numkinds --;

      for (int i =0; i < m; i++){
        arr[index++] = curnum;
      }
    }

    for (int i= 0; i < arr.length; i++){
      int j = (int)(Math.random() * arr.length);
      int tmp = arr[i];
      arr[i] = arr[j];
      arr[j] = tmp;
    }

    return arr;
  }

  public static int randomNumber(int range){
    return (int)(Math.random() * range + 1) - (int)(Math.random()*range + 1);
  }


  public static void main(String[] args) {

    int testTime = 500000;
    int maxKinds = 10;
    int range = 100;
    int k =(int)Math.random() * 10 + 1;
    int m = (int)Math.random()* 10 + k + 1;
    System.out.println("测试开始");
    for (int i = 0; i < testTime; i++){

      int[] arr = randomArray(maxKinds, range, k, m);
      int ans1 = test11(arr, k, m);
      int ans2 = onlyTimes(arr, k, m);
//      int[] arr = {0,0,0,0,0,0,0};
//      int ans1 = test(arr, 1, 2);
//      int ans2 = onlyTimes(arr, 1, 2);
      if (ans1 != ans2) {
        System.out.println(ans1);
        int ans3 = test11(arr, k, m);
        System.out.println(ans2);
        System.out.println("出错了！");
      }

    }
    System.out.println("测试结束");
  }

  public static int[] randomArray(int maxKinds, int range, int k, int m) {
    int ktimeNum = randomNumber(range);
    // 真命天子出现的次数
    int times = Math.random() < 0.5 ? k : ((int) (Math.random() * (m - 1)) + 1);
    // 2
    int numKinds = (int) (Math.random() * maxKinds) + 2;
    // k * 1 + (numKinds - 1) * m
    int[] arr = new int[times + (numKinds - 1) * m];
    int index = 0;
    for (; index < times; index++) {
      arr[index] = ktimeNum;
    }
    numKinds--;
    HashSet<Integer> set = new HashSet<>();
    set.add(ktimeNum);
    while (numKinds != 0) {
      int curNum = 0;
      do {
        curNum = randomNumber(range);
      } while (set.contains(curNum));
      set.add(curNum);
      numKinds--;
      for (int i = 0; i < m; i++) {
        arr[index++] = curNum;
      }
    }
    // arr 填好了
    for (int i = 0; i < arr.length; i++) {
      // i 位置的数，我想随机和j位置的数做交换
      int j = (int) (Math.random() * arr.length);// 0 ~ N-1
      int tmp = arr[i];
      arr[i] = arr[j];
      arr[j] = tmp;
    }
    return arr;
  }
}
