package com.gzy.code.basic.class01;

import static com.gzy.code.basic.class01.SelectionSort.*;

public class InsertSort {



    public static void insertSort(int[] arr){
        if(null == arr){
            return;
        }

        for (int i = 1; i < arr.length; i++){

            for(int j = i ; j > 0; j--){

                if (arr[j - 1] <= arr[j]){
                    break;
                }

                swap(arr, j - 1, j);
            }


        }
    }

    public static void swap(int[] arr, int i, int j){
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];

    }


    public static void main(String[] args) {

        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;

        boolean succeed = true;
        for(int i = 0; i < testTime; i++){
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArr(arr1);
            insertSort(arr1);
            comparator(arr2);

            if (!equals(arr1, arr2)){
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }

        System.out.println(succeed? "Nice" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        selectionSort(arr);
        printArray(arr);

    }

    public static boolean equals(int[] arr1, int[] arr2){
        if ((arr1 == null && arr2 !=null) || (arr1 != null && arr2 == null)){
            return false;
        }

        if (arr1 == null && arr2 == null){
            return true;
        }

        if (arr1.length != arr2.length){
            return false;
        }

        for (int i = 0; i < arr1.length; i ++){
            if (arr1[i] != arr2[i]){
                return false;
            }
        }

        return true;
    }
}
