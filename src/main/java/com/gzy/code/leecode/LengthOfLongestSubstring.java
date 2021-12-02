package com.gzy.code.leecode;

import java.util.HashMap;
import java.util.Map;

/**
 * description: LengthOfLongestSubstring date: 2021/9/2 10:29 下午
 *
 * @author: guizhenyu
 */
public class LengthOfLongestSubstring {

  public static void main(String[] args) {
    System.out.println(lengthOfLongestSubstring("  "));
  }
  public static int lengthOfLongestSubstring(String s) {
//    char[] chars =s.toCharArray();
    int index_start = 0;
    int index_end = 0;
    int longest = 0;
    Map<Character, Integer> map = new HashMap();
    for(int i = 0; i < s.length(); i ++){
      if(!map.containsKey(s.charAt(i))){

        index_end ++;
      }else{
        int longest_new = index_end - index_start;
        longest = longest_new > longest?longest_new:longest;
        map = new HashMap();
        index_start = i ;
        index_end = i + 1;
      }
      map.put(s.charAt(i), i);
    }

    return longest;

  }
}
