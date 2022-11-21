package com.gzy.code.leecode.sort;

public class MedianSortedArrays {

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
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        if ((nums1 == null || nums1.length == 0) && (nums1 == null || nums1.length == 0)){
            return 0.0;
        }

        int m = nums1 == null? 0 : nums1.length;
        int n = nums2 == null? 0 : nums2.length;
        int sum = m + n;
        boolean isEven = sum % 2 == 0? true : false;
        // 中位数的索引
        int midIndex = sum / 2 + (!isEven? 1 : 0);






    }
}
