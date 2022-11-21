package com.gzy.code.leecode.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {



    public List<List<Integer>> fourSum(int[] nums, int target) {
        long targetLong = target;
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length < 4){
            return ans;
        }
        Arrays.sort(nums);
        int len = nums.length;
        for (int first = 0; first < len - 3; first++){
            if (first > 0 && nums[first] == nums[first - 1]){
                continue;
            }
            if ((long)nums[first] + nums[first + 1] + nums[first + 2] + nums[first + 3] > targetLong){
                return ans;

            }
            if ((long)nums[first] + nums[len - 3] + nums[len - 2] + nums[len - 1] < targetLong){
                continue;
            }


            for (int second = first + 1; second < len - 2; second++){
                if (second > first + 1 && nums[second] == nums[second - 1]){
                    continue;
                }
                if ((long)nums[first] + nums[second] + nums[second + 1] + nums[second + 2] > targetLong){
                    //todo 直接返回会,少了解 ，因为这边有负数和正数？？？？？

                    // return ans;
                    break;
                }
                if ((long)nums[first] + nums[second] + nums[len - 2] + nums[len - 1] < targetLong){
                    continue;
                }


                int third = second + 1;
                int fourth = len - 1;
                while (third < fourth){
                    long sum = (long)nums[first] + nums[second] + nums[third] + nums[fourth];
                    if (sum == targetLong){
                        List<Integer> result = new ArrayList<>();
                        result.add(nums[first]);
                        result.add(nums[second]);
                        result.add(nums[third]);
                        result.add(nums[fourth]);
                        ans.add(result);
                        while (third < fourth && nums[third] == nums[third + 1]){
                            third++;
                        }
                        third++;
                        while (third < fourth && nums[fourth] == nums[fourth - 1]){
                            fourth--;
                        }

                        fourth--;
                    }else if(sum > targetLong){
                        fourth--;
                    }else {
                        third++;
                    }
                }
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int i = -294967296;

        System.out.println((long)i);
    }
}
