package com.piyush.psds.google.dp;

public class MaxProductSubArray {

    class Solution {
        public int maxProduct(int[] nums) {
            if(nums.length == 0) {
                return 0;
            }
            int maxSoFar = nums[0];
            int minSoFar = nums[0];
            int result = maxSoFar;
            for(int i=1; i<nums.length; i++) {
                int curr = nums[i];
                // calculating the max so far: If the curr is 0 then tempMax/maxSoFar becomes 0 : hard reset
                // if curr is negative, then minSoFar would decide what would be the value of it.
                int tempMax = Math.max(curr, Math.max(maxSoFar*curr, minSoFar*curr));
                minSoFar = Math.min(curr, Math.min(maxSoFar*curr, minSoFar*curr));
                maxSoFar = tempMax;
                result = Math.max(maxSoFar, result);
            }
            return result;
        }
    }

}
