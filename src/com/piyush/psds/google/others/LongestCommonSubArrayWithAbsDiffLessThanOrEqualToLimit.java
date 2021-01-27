package com.piyush.psds.google.others;

import java.util.ArrayDeque;
import java.util.Deque;

public class LongestCommonSubArrayWithAbsDiffLessThanOrEqualToLimit {

    public int longestSubarray(int[] nums, int limit) {
        Deque<Integer> maxQ = new ArrayDeque<>();
        Deque<Integer> minQ = new ArrayDeque<>();
        int j = 0;
        int i = 0;
        for (; i < nums.length; i++) {
            while (!maxQ.isEmpty() && maxQ.peekLast() < nums[i]) {
                maxQ.removeLast();
            }
            while (!minQ.isEmpty() && minQ.peekLast() > nums[i]) {
                minQ.removeLast();
            }
            maxQ.addLast(nums[i]); // simple add() does addLast
            minQ.addLast(nums[i]);
            if (maxQ.getFirst() - minQ.getFirst() > limit) {
                if (maxQ.getFirst() == nums[j]) {
                    maxQ.removeFirst();
                }
                if (minQ.getFirst() == nums[j]) {
                    minQ.removeFirst();
                }
                j++;
            }
        }
        return i - j;
    }

}
