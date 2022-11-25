package com.gzy.code.leecode.sort;

import java.util.HashMap;
import java.util.Map;

public class LengthLongestOfSubString {

    public static int lengthOfLongestSubstring(String s) {
        char[] chars =s.toCharArray();
        int index_start = 0;

        int longest = 0;
        Map<String, Integer> map = new HashMap();
        for(int i = 0; i < chars.length; i++){
            Integer record = map.get(String.valueOf(chars[i]));
            if(record != null){
                int longest_new = i - index_start;
                // todo: 在确定开始边界的时候，得比较下
                index_start = index_start > record? index_start : (record + 1);
                longest = longest_new > longest? longest_new : longest;

            }

            map.put(String.valueOf(chars[i]), i);
        }
        int index_end = chars.length;
        return index_end - index_start > longest? index_end - index_start : longest;
    }

    public static void main(String[] args) {
        int ans = lengthOfLongestSubstring("tmmzuxt");
        System.out.println(ans);
    }
}
