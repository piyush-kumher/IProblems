
package com.piyush.psds.aug_6_2020_streak.dp;

import java.util.Arrays;

public class LongestIncreasingSubsequence {

    // binary search with dp
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] mem = new int[nums.length];
        int lastIndex = 0;
        mem[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > mem[lastIndex]) {
                mem[++lastIndex] = nums[i];
            } else {
                int indexToBeReplaced = binarySearch(mem, 0, lastIndex, nums[i]);
                mem[indexToBeReplaced] = nums[i];
            }
        }
        return lastIndex + 1;
    }

    private int binarySearch(int[] mem, int start, int end, int num) {
        while (start <= end) {
            int mid = (start + end) / 2;
            if (mem[mid] == num) {
                return mid;
            }
            if (mem[mid] < num) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }

    // dp : O(n*n)
    public int lengthOfLIS_1(int[] nums) {
        if (nums.length == 0) {
        }
        int[] mem = new int[nums.length];
        Arrays.fill(mem, 1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && mem[j] >= mem[i]) {
                    mem[i] = mem[j] + 1;
                }
            }
        }
        int max = 0;
        for (int i = 0; i < mem.length; i++) {
            if (max < mem[i]) {
                max = mem[i];
            }
        }
        return max;
    }


}
