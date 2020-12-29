package com.piyush.psds.facebook.searching_and_sorting;

public class SearchInRotatedSortedArray {

    public int search(int[] nums, int target) {
        if(nums.length == 0) {
            return -1;
        }
        int start = 0;
        int end = nums.length-1;
        while(start <= end) {
            int mid = start + (end-start) / 2;
            if(nums[mid] == target) {
                return mid;
            } else if(nums[start] <= nums[mid]) {
                if(nums[start] <= target && nums[mid] >= target) {
                    end = mid-1;
                } else {
                    start = start+1;
                }
            } else if(nums[start] > nums[mid]) {
                if(nums[mid] < target && nums[end] >= target) {
                    start = start + 1;
                } else {
                    end = end-1;
                }
            }
        }
        return -1;
    }

}
