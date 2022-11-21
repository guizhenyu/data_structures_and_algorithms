package com.gzy.code.basic.class08.second;

import java.util.Arrays;

public class RadixSort {


    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }


    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100000;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            radixSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                System.out.println(i);
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        radixSort(arr);
        printArray(arr);

    }

    /**
     * 并排序
     * 思路：
     *     1.
     * @param arr1
     */
    public static void radixSort(int[] arr1) {
        if (null == arr1 || arr1.length < 2){
            return;
        }

        // 1.求数组是几位数


        // 2. 根据几位数，就遍历几次，完成排序
        radixSort(arr1, digit(arr1));

    }


    /**
     * arr[L..R]排序  ,  最大值的十进制位数digit
     *
     * @param arr
     * @param digit
     */
    private static void radixSort(int[] arr, int digit) {

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


    public static int getDigit(int x, int d){
        return (x / (int)(Math.pow(10, d))) % 10;
    }

    /**
     * 获取几位数
     *
     * @param arr
     * @return
     */
    public static int digit(int[] arr){

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
