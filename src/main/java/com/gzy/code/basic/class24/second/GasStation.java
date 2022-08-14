package com.gzy.code.basic.class24.second;

import java.util.LinkedList;

/**
 * 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 *
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 *
 * 给定两个整数数组 gas 和 cost ，如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。
 *
 *
 *
 */
public class GasStation {


    /**
     *
     *
     *
     * @param g 站点的加油量
     * @param c 去加油站耗费的油
     * @return
     */
    public static int canCompleteCircuit(int[] g, int[] c){

        boolean[] ans = goodArray(g, c);

        for (int i = 0; i < ans.length; i++){
            if (ans[i]){
                return i;
            }
        }

        return -1;
    }

    /**
     * 思路：
     *     两个技巧： 1. 原始数组增大一倍，为了可以尝试从每一个点出发的结果
     *              2. 用双端队列记录，最小值。
     *
     * @param g
     * @param c
     * @return
     */
    private static boolean[] goodArray(int[] g, int[] c) {
        int N = g.length;
        int M = N << 1;
        int[] arr = new int[M];
        for(int i = 0; i < N; i++){
            arr[i] = g[i] - c[i];
            arr[i + N] = arr[i];
        }

        LinkedList<Integer> minIndex = new LinkedList<>();
        for (int i = 0; i < N; i++){
            while (!minIndex.isEmpty() && arr[minIndex.peekLast()] >= arr[i]){
                minIndex.pollLast();
            }
            minIndex.addLast(i);
        }

        boolean[] ans = new boolean[N];
        // 此时的minIndex中保存的是 arr中 0 ～ （N-1）的顺序最小值， 最少有一个
        // 这里的有个隐藏条件： arr[minIndex.peekFirst] - arr[i]代表从某个加油站出发再回到自己，路途中的最小值，如果小于0， 代表是失败的
        for (int i = 0, floor = 0, j = N; j < M; floor = arr[i], i++, j++){
            // 这边i 代表的是出发的位置， 而floor代表，arr中 i之前的值，

            while (!minIndex.isEmpty() && arr[minIndex.peekFirst()] - floor >= 0){
                // 代表当前i号加油站出发，路经中的最小值 减去 floor, 大于0，代表i号加油站是可以走完的
                ans[i] = true;
            }

            if (minIndex.peekFirst() == i){
                minIndex.pollFirst();
            }
            while (!minIndex.isEmpty() && arr[minIndex.peekLast()] >= arr[j]){
                // 继续弹出大的值
                minIndex.pollLast();
            }
            minIndex.addLast(j);
        }

        return ans;
    }
}
