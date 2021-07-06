package com.gzy.code.sort;

public class MergeSort {

    private static int[] arr = new int[]{8,7,3,7,2,9,4,6,12,11};

    public static void main(String[] args) {
        mergeSort(arr, 0, arr.length -1);
        for(int i = 0; i< arr.length; i++){
            System.out.println(arr[i]);
        }
    }


//    public static void mergeSort(int[] arr){
//
//    }
//

    // 请把arr[L..R]排有序
    // l...r N
    // T(N) = 2 * T(N / 2) + O(N)
    // O(N * logN)
    public static void process(int[] arr, int L, int R) {
        if (L == R) { // base case
            return;
        }
        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }
    public static void mergeSort(int[] arr, int l, int r){
        if (l == r){
            return;
        }
        int mid = l + ((r -l) >> 1);

        mergeSort(arr, l, mid );
        mergeSort(arr, mid+ 1, r);

        merge(arr, l, mid, r);

    }


    public static void merge(int[] arr, int l, int mid, int r){
        // l -->> mid <<-- r
        // 比较l和r的对应的数组值的大小, 进行交换排序换
        // 直到l 和 r相遇

        // 用来存放合并后的数组
        int[] help = new int[r - l + 1];
        int index = 0;
        int p1 = l;
        int p2 = mid + 1;
        while(p1 <= mid && p2 <= r){
            help[index++] = arr[p1] >= arr[p2]? arr[p2++]:arr[p1++];
        }

        while(p1 <= mid){
            help[index++] = arr[p1++];
        }

        while (p2 <= r){
            help[index++] = arr[p2++];
        }

        int length = help.length;
        for (int i = 0; i < length; i ++){
            arr[i+l] = help[i];
        }

    }
}
