package com.piyush.sahu.array.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestConsecutiveSequence {

    /**
     * Runtime: 6 ms, faster than 78.96% of Java online submissions for Longest Consecutive Sequence.
     * Memory Usage: 37.5 MB, less than 47.13% of Java online submissions for Longest Consecutive Sequence.
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int longestSequence = 0;
        for (int num : set) {
            if (!set.contains(num - 1)) {
                int currentSeq = 1;
                int currentNum = num;
                while (set.contains(currentNum + 1)) {
                    currentNum = num + 1;
                    currentSeq = currentSeq + 1;
                }
                longestSequence = Math.max(longestSequence, currentSeq);
            }
        }
        return longestSequence;
    }

    /**
     * Runtime: 9 ms, faster than 29.97% of Java online submissions for Longest Consecutive Sequence.
     * Memory Usage: 37.5 MB, less than 47.13% of Java online submissions for Longest Consecutive Sequence.
     */
    public int longestConsecutive_1(int[] nums) {
        int response = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums){
            if(!map.containsKey(num)){
                int left = map.getOrDefault(num - 1, 0);
                int right = map.getOrDefault(num + 1, 0);
                int sum = left + right + 1;
                map.put(num, sum);
                map.put(num-left, sum);
                map.put(num + right, sum);
                response = Math.max(response, sum);
            }
        }
        return response;
    }
}
