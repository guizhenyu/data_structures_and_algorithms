package com.gzy.code.test;

import java.util.HashSet;
import java.util.Set;

public class Solution31 {
    public int solution(String S) {
        // Implement your solution here
//        char[] chars = new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
//        Set<Integer> set = new HashSet<>();
//        dfs(S, 0, 1, set, chars);
//
//        return set.size();

        int n = S.length();
        int[] cache = new int[10];

        cache[0] = 0;
        cache[1] = 2;
        cache[2] = 1;
        cache[3] = 0;
        cache[4] = 2;
        cache[5] = 1;
        cache[6] = 0;
        cache[7] = 2;
        cache[8] = 1;
        cache[9] = 0;

        char[] chars = S.toCharArray();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = chars[i] - '0';
        }

        return process(nums, n - 1, false, 0, cache);
    }


    public int process(int[] nums, int index, boolean isChange, int nextNum, int[] cache) {
        int num = nums[index];

        if (index == 0) {
            if (isChange) {
                if ((num * 10 + nextNum) % 3 == 0) {
                    return 1;
                } else {

                    return 0;
                }
            } else {
                return (9 - cache[nextNum]) / 3 + 1;
            }
        }
        int ans = 0;
        if (num >= cache[nextNum]) {
            ans += process(nums, index - 1, isChange, num - cache[nextNum], cache);
        }
        if (!isChange) {
            for (int i = 0; i <= 9 - cache[nextNum]; i++) {
                ans += process(nums, index - 1, true, i, cache);
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        String s = "781292217";
        Solution31 solution31 = new Solution31();

        System.out.println(solution31.solution(s));
    }
}
