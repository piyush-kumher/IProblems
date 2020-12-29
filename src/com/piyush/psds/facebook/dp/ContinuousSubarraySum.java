package com.piyush.psds.facebook.dp;

import java.util.HashMap;

public class ContinuousSubarraySum {

    // 17 %
    public boolean checkSubarraySum(int[] nums, int k) {
        if(nums.length < 2) {
            return false;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        for(int i=0; i < nums.length; i++) {
            sum += nums[i];
            if(k != 0) {
                sum = sum % k;
            }
            if(map.containsKey(sum)) {
                if(i-map.get(sum) > 1) {
                    return true;
                }
            } else {
                map.put(sum, i);
            }
        }
        return false;
    }

    public boolean checkSubarraySum_1(int[] nums, int k) {
        if(nums.length < 2) {
            return false;
        }
        int[] dp = new int[nums.length+1];
        for(int i=0; i < nums.length; i++) {
            dp[i+1] = dp[i] + nums[i];
        }
        for(int i=2; i < dp.length; i++) {
            for(int j=0; j < i-1; j++) {
                if(dp[i] - dp[j] == k || (k !=0 && (dp[i]-dp[j]) % k == 0)) {
                    return true;
                }
            }
        }
        return false;
    }
}
