package com.piyush.psds.aug_6_2020_streak.leetcode.first;

import java.util.Arrays;

public class FirstMissingNumber {

    public static void main(String[] args) {
        FirstMissingNumber f = new FirstMissingNumber();
        int[] nums = {7, 8, 9, 10};
        System.out.println(f.firstMissingPositive(nums));
    }

    // By sorting
    public int firstMissingPositive_1(int[] nums) {
        Arrays.sort(nums);
        int minPos = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == minPos) {
                minPos++;
            }
        }
        return minPos;
    }

    // By putting correct number in its position
    public int firstMissingPositive_2(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = nums[i]; j > 0 && j <= nums.length && nums[j - 1] != j; j = nums[i]) {
                swap(nums, j - 1, i);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != (i + 1)) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    public int firstMissingPositive(int[] nums) {
        boolean onePresent = false;
        // check if 1 present
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                onePresent = true;
                break;
            }
        }
        if (!onePresent) {
            return 1;
        }
        // anything less than 0 or greater than the length of array is not needed
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0 || nums[i] > nums.length) {
                nums[i] = 1;
            }
        }
        // make its own position multiplied by -1
        for (int i = 0; i < nums.length; i++) {
            int origPos = Math.abs(nums[i]) - 1;
            if(nums[origPos] > 0) {
                nums[origPos] *= -1;
            }
        }
        int i = 0;
        for (; i < nums.length; i++) {
            if (nums[i] >= 0) {
                break;
            }
        }
        return i+1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
