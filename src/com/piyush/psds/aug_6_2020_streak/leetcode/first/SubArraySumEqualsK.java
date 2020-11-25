package com.piyush.psds.aug_6_2020_streak.leetcode.first;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/subarray-sum-equals-k/submissions/
 */
public class SubArraySumEqualsK {

    public int subarraySum(int[] nums, int k) {
        int[] sum = new int[nums.length + 1];
        int count = 0;
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        for (int i = 0; i <= nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (sum[j + 1] - sum[i] == k) {
                    count++;
                }
            }
        }
        return count;
    }

    public int subarraySum_best(int[] nums, int k) {
        final HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        int sum = 0;
        map.put(0, 1);
        for (int num : nums) {
            sum += num;
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

}
