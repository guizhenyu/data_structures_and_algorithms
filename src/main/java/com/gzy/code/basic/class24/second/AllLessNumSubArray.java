package com.gzy.code.basic.class24.second;


import java.util.LinkedList;

/**
 * description: AllLessNumSubArray date: 2022/8/11 17:28
 * <p>
 * 给定一个整型数组arr，和一个整数num
 * 某个arr中的子数组sub，如果想达标，必须满足：sub中最大值 – sub中最小值 <= num，
 * 返回arr中达标子数组的数量
 *
 * @author: guizhenyu
 */
public class AllLessNumSubArray {


    public static void main(String[] args) {
        int testTime = 10000;
        int maxValue = 200;
        int maxLen = 100;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateArr(maxValue, maxLen);
            int sum = (int) (Math.random() * maxValue);
            int ans1 = violenceFunction(arr, sum);
            int ans2 = right(arr, sum);
            int ans3 = num(arr, sum);
            if (ans1 != ans2 || ans3 != ans1) {
                System.out.printf("Oops!");
                return;
            }
        }


    }

    private static int num(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) {
            return 0;
        }

        LinkedList<Integer> maxWindow = new LinkedList<>();
        LinkedList<Integer> minWindow = new LinkedList<>();

        int R = 0;
        int ans = 0;
        for (int L = 0; L < arr.length; L++){
            while (R < arr.length){
                while (!minWindow.isEmpty() && arr[minWindow.peekLast()] >= arr[R]){
                    minWindow.pollLast();
                }
                minWindow.addLast(R);
                while (!maxWindow.isEmpty() && arr[maxWindow.peekLast()] <= arr[R]){
                    maxWindow.pollLast();
                }
                maxWindow.addLast(R);
                if (arr[maxWindow.peekFirst()] - arr[minWindow.peekFirst()] > sum) {
                    break;
                }else {
                    R++;
                }

            }
            ans += R - L;
            if (minWindow.peekFirst() == L){
                minWindow.pollFirst();
            }
            if (maxWindow.peekFirst() == L){
                maxWindow.pollFirst();
            }

        }

        return ans;


    }

    // 暴力的对数器方法
    public static int right(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) {
            return 0;
        }
        int N = arr.length;
        int count = 0;
        for (int L = 0; L < N; L++) {
            for (int R = L; R < N; R++) {
                int max = arr[L];
                int min = arr[L];
                for (int i = L + 1; i <= R; i++) {
                    max = Math.max(max, arr[i]);
                    min = Math.min(min, arr[i]);
                }
                if (max - min <= sum) {
                    count++;
                }
            }
        }
        return count;
    }


    private static int violenceFunction(int[] arr, int sum) {
        if (arr == null || arr.length < 1 || sum < 0) {
            return 0;
        }
        int ans = 0;
//        int L = 0;
        int N = arr.length;

        for (int L = 0; L < N; L++) {
            for (int R = L; R < N; R++) {
                int max = arr[L];
                int min = arr[L];
                for (int i = L + 1; i <= R; i++) {
                    max = Math.max(max, arr[i]);
                    min = Math.min(min, arr[i]);
                }
                if (max - min <= sum) {
                    ans++;
                }
            }
        }
        return ans;
    }

    private static int[] generateArr(int maxValue, int maxLen) {

        int len = (int) (Math.random() * (maxLen + 1));
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

}
