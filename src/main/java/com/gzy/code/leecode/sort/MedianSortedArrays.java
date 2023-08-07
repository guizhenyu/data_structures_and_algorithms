package com.gzy.code.leecode.sort;

public class MedianSortedArrays {

    public static void main(String[] args) {
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    /**
     *
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
     *
     * 算法的时间复杂度应该为 O(log (m+n)) 。
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int m = nums1 == null? 0 : nums1.length;
        int n = nums2 == null? 0 : nums2.length;

        boolean isEven = (m + n) % 2 == 0? true : false;
        int k = (m + n) / 2 + 1;
        if (isEven){
            return (midNum(nums1, nums2, m, n, k) + midNum(nums1, nums2, m, n, k - 1 )) / 2;
        }else{
            return midNum(nums1, nums2, m, n, k);
        }


    }

    private static double midNum(int[] nums1, int[] nums2, int m, int n, int k) {

        int index1 = 0;
        int index2 = 0;

        while (true){
            if (index1 == m){
                return nums2[index2 + k - 1];
            }
            if (index2 == n){
                return nums1[index1 + k - 1];
            }
            if (k == 1){
                return Math.min(nums1[index1], nums2[index2]);
            }

            // 正常情况
            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, m) - 1;
            int newIndex2 = Math.min(index2 + half, n) - 1;

            int p1 = nums1[newIndex1];
            int p2 = nums2[newIndex2];

            if (p1 > p2){

                k -= newIndex2 + 1 - index2;
                index2 = newIndex2 + 1;
            }else {

                k -= newIndex1 + 1 - index1;
                index1 = newIndex1 + 1;
            }
        }





    }
}
