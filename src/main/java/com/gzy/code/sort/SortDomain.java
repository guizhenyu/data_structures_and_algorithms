//package com.gzy.code.sort;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.List;
//import java.util.concurrent.ArrayBlockingQueue;
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;
//
///**
// * description: SortDomain date: 2021/9/14 5:41 下午
// *
// * @author: guizhenyu
// */
//
//public class SortDomain {
//
//  public static void main(String[] args) {
//
//    ArrayList<String> strs = new ArrayList<>();
//    strs.add("002-003-002");
//    strs.add("001-003-002");
//    strs.add("003-003-002");
//    strs.add("004-003-002");
//    strs.add("002-003-001");
//    strs.add("001-002-002");
//    strs.add("003-001-002");
//    strs.add("004-002-002");
//
//    // 排序规则
//    List<Integer> sorts = new ArrayList<>();
//    sorts.add(1);// 升
//    sorts.add(-1); // 降
//    sorts.add(1);
//    Collections.sort(strs, new Comparator<String>() {
//      @Override
//      public int compare(String o1, String o2) {
//        if (o1.equals(o2)){
//          return 0;
//        }
//        String[] o1s = o1.split("-");
//        String[] o2s = o2.split("-");
//        int length = o1s.length;
//        for (int i = 0; i < length; i++) {
//          if (!o1s[i].equals(o2s[i])){
//            return o1s[i].compareTo(o2s[i]) * sorts.get(i);
//          }
//        }
//        return 0;
//      }
//    });
//
//    for (String str:
//    strs) {
//      System.out.println(str);
//    }
//
//    ThreadPoolExecutor executer = new ThreadPoolExecutor(
//        2,
//        4,
//        10,
//        TimeUnit.SECONDS,
//        new ArrayBlockingQueue<>()
//    );
//    executer.allowCoreThreadTimeOut(true);
//  }
//}
