package com.gzy.code.test;

import java.sql.Array;
import java.util.*;

public class Solution31 {






    public int solution(String str) {
        char[] chars = str.toCharArray();



        return 2;
    }

//    public static void main(String[] args) {
//        String s = "01232323450";
//
//        System.out.println(Integer.valueOf(s));
//        char[] chars = s.toCharArray();
//        System.out.println(chars[0]);
//        String s1 = chars.toString();
//
//        System.out.println(String.valueOf(chars));
//    }


    public static int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>(arr.length);

        for (int i = 0; i < arr.length; i++){
            Integer record = map.get(arr[i]);
            if(record == null){
                map.put(arr[i], 1);
            }else {
                map.put(arr[i], record + 1);
            }
        }
        List<Integer> list = new ArrayList<>();
        list.addAll(map.values());
        Collections.sort(list);
        int ans = 0;
        for (int i = 0; i < list.size(); i++){
            k = k - list.get(i);
            if(k <= 0){
                ans = list.size() - i - 1;
               break;
            }
        }

        return ans;


    }


    public static void main(String[] args) {

        int[] arr = {5, 5, 4};
        int ans = findLeastNumOfUniqueInts(arr, 1);
        System.out.println(ans);
    }
}
