package com.piyush.psds.facebook.array_and_string;

// https://leetcode.com/problems/string-to-integer-atoi/submissions/
public class RemoveDuplicatesFromSortedArray {

    public int removeDuplicates(int[] nums) {
        int duplicates = 0;
        for(int i=1; i < nums.length; i++) {
            if(nums[i] == nums[i-1-duplicates]) {
                duplicates++;
            } else {
                nums[i-duplicates] = nums[i];
            }
        }
        return nums.length - duplicates;
    }

}
