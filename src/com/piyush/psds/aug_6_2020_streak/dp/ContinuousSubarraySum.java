package com.piyush.psds.aug_6_2020_streak.dp;

import java.util.HashMap;
import java.util.Map;

public class ContinuousSubarraySum {

    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> dp = new HashMap<>();
        dp.put(0, -1);
        int sum = 0;
        for(int i=0; i < nums.length; i++) {
            sum += nums[i];
            if(k != 0) {
                sum = sum % k;
            }
            if(dp.containsKey(sum)) {
                if(i - dp.get(sum) > 1) {
                    return true;
                }
            } else {
                dp.put(sum, i);
            }
        }
        return false;
    }

    // n*n
    public boolean checkSubarraySum_1(int[] nums, int k) {
        if(nums.length < 2) {
            return false;
        }
        int[] dp = new int[nums.length+1];
        dp[0] = 0;
        for(int i=0; i < nums.length; i++) {
            dp[i+1] = dp[i] + nums[i];
        }
        for(int i=2; i < dp.length; i++) {
            for(int j=0; j < i-1; j++) {
                if((dp[i] - dp[j]) == k || (k != 0 && (dp[i] - dp[j]) % k == 0)) {
                    return true;
                }
            }
        }
        return false;
    }

}
