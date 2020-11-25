package com.piyush.psds.aug_6_2020_streak.leetcode.random;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        int globalStreak = 1;
        Set<Integer> set = new HashSet<>();
        for(int num : nums) {
            set.add(num);
        }
        for(int ele : set) {
            if(!set.contains(ele-1)) {
                int current = ele;
                int currentStreak = 1;
                while(set.contains(current+1)) {
                    current++;
                    currentStreak++;
                }
                globalStreak = Math.max(globalStreak, currentStreak);
            }
        }
        return globalStreak;
    }

}
