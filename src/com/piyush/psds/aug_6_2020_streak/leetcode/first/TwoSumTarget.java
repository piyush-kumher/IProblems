package com.piyush.psds.aug_6_2020_streak.leetcode.first;

import java.util.*;

public class TwoSumTarget {

    public static void main(String[] args) {
        TwoSumTarget t = new TwoSumTarget();
        int[] nums = {2, 7, 11, 15};
        System.out.println(Arrays.toString(t.twoSum(nums, 9)));
    }

    public int[] twoSum(int[] nums, int target) {
        int[] ans = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int remainder = target - nums[i];
            if (map.containsKey(remainder)) {
                ans[0] = i;
                ans[1] = map.get(remainder);
                return ans;
            }
            map.put(nums[i], i);
        }
        return ans;
    }

}
