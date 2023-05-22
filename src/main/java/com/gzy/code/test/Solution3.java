package com.gzy.code.test;

import java.util.HashSet;
import java.util.Set;

public class Solution3 {
    public int solution(String S) {
        // Implement your solution here
        char[] chars = new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        Set<Integer> set = new HashSet<>();
        dfs(S, 0, 1, set, chars);

        return set.size();
    }


    public void  dfs(String s, int index, int leftChange, Set<Integer> ans, char[] chars ){
        if (leftChange == 0 || index == s.length()) {
            int curNum = Integer.valueOf(s);
            if(curNum % 3 == 0 ){
                ans.add(curNum);
            }
            return;
        }

        dfs(s, index + 1, leftChange,  ans, chars);

        char[] charsOld = s.toCharArray();
        for (int i = 0; i < chars.length; i++){
            char preChar = charsOld[index];
            if(chars[i] != preChar){
                charsOld[index] = chars[i];

                dfs(String.valueOf(charsOld), index + 1, leftChange - 1, ans, chars);
                charsOld[index] = preChar;

            }
        }

    }

    public static void main(String[] args) {
        String s = "781292217";
//        System.out.println(s);
//        char[] chars = s.toCharArray();
//        System.out.println(chars[0]);
//        String s1 = chars.toString();
        Solution3 solution3 = new Solution3();
        solution3.solution(s);
//        System.out.println(String.valueOf(chars));
    }
}
