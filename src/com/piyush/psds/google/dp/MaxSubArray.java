package com.piyush.psds.google.dp;

public class MaxSubArray {

    public int maxSubArray(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        int sum = nums[0];
        int maxSum = nums[0];
        for(int i=1; i < nums.length; i++) {
            if(sum + nums[i] < nums[i]) {
                sum = nums[i];
            } else {
                sum = nums[i] + sum;
            }
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }

}
