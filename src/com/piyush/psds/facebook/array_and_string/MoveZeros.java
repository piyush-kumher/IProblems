package com.piyush.psds.facebook.array_and_string;

public class MoveZeros {

    public void moveZeroes(int[] nums) {
        int i = 0;
        int j = 0;
        while (i < nums.length && j < nums.length) {
            if (i < j && nums[j] != 0) {
                swap(nums, i, j);
                i++;
            } else if (nums[i] != 0) {
                i++;
            }
            j++;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }

}
