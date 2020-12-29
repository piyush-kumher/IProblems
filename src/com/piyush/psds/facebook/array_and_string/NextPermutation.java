package com.piyush.psds.facebook.array_and_string;

import java.util.Arrays;

public class NextPermutation {


    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        int prev = nums[i+1];
        while(i >= 0) {
            if(nums[i] < prev) {
                break;
            }
            prev = nums[i--];
        }
        if(i >= 0) {
            int j=nums.length-1;
            for(; j > i; j--) {
                if(nums[j] > nums[i]) {
                    break;
                }
            }
            swap(nums, i, j);
        }
        Arrays.sort(nums, i+1, nums.length);
    }

    public void nextPermutation_1(int[] nums) {
        int i = nums.length - 2;
        int prev = nums[i+1];
        while(i > 0) {
            if(nums[i] < prev) {
                break;
            }
            prev = nums[i--];
        }
        if(i >= 0) {
            int j=nums.length-1;
            for(; j > i; j--) {
                if(nums[j] > nums[i]) {
                    break;
                }
            }
            swap(nums, i, j);
        }
        // reverse also works.. need to investigate ..
        reverse(nums, i+1);
    }

    private void reverse(int[] nums, int l) {
        int r = nums.length-1;
        while(l < r) {
            swap(nums, l++, r--);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
