package com.gzy.code.leecode.sort;

import java.util.ArrayList;
import java.util.List;

public class GroupAnagrams {

    /**
     * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
     *
     * 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。
     * @param strs
     * @return
     */

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();

        if (strs == null || strs.length == 0){
            return ans;
        }




        return ans;
    }


    private  void radixSort(int[] arr, int digit) {

        final int radix = 10;
        int[] help = new int[arr.length];
        for (int i = 0; i < digit; i++){
            int[] count = new int[radix];
            for (int index = 0; index < arr.length; index++){
                count[getDigit(arr[index], i)]++;
            }

            for (int j = 1; j < radix; j++){
                count[j]= count[j] + count[j - 1];
            }

            for (int index = arr.length - 1; index >= 0; index--){
                int j = getDigit(arr[index], i);
                help[count[j] - 1] = arr[index];
                count[j]--;
            }

            for (int index = 0; index < arr.length; index++){
                arr[index] = help[index];
            }

        }

    }


    public  int getDigit(int x, int d){
        return (x / (int)(Math.pow(10, d))) % 10;
    }

    /**
     * 获取几位数
     *
     * @param arr
     * @return
     */
    public  int digit(int[] arr){

        int maxVal = arr[0];
        for (int i = 1; i < arr.length; i++){
            maxVal = Math.max(arr[i], maxVal);
        }

        int ans = 0;

        while (maxVal > 0){
            ans++;
            maxVal = maxVal / 10;
        }

        return ans;

    }
}
