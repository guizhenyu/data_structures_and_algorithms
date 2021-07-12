package com.gzy.code.sort;

public class QuickSort {


    private static int[] arr = new int[]{8,7,3,7,2,9,4,6,12,11};

    public static void main(String[] args) {
        quickSort(arr, 0, arr.length - 1);
        for(int i = 0; i< arr.length; i++){
            System.out.println(arr[i]);
        }
    }

    private static void quickSort(int[] arr, int l, int r) {

        if (l >= r){
            return;
        }

        int mid = partition(arr, l, r);

        quickSort(arr, l, mid - 1);
        quickSort(arr, mid + 1, r);

    }

    private static int partition(int[] arr, int l, int r) {

        if (l > r) {
            return -1;
        }

        if (l == r){
            return l;
        }

        // pivot 枢纽, 作为分区的比较对象(在 l --> r 之间比较)
        int pivot = arr[r];
        // pivot 左边数的下标索引
        int pivot_left_index = l - 1;
        int compare_index = l;
        while(compare_index <= r){
            if (arr[compare_index] < pivot){
                swap(arr, compare_index, ++pivot_left_index);
            }

            compare_index ++;
        }
        swap(arr, ++pivot_left_index, r);

        return pivot_left_index;
    }

    public static void swap(int[] arr, int i, int j){

        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


}
