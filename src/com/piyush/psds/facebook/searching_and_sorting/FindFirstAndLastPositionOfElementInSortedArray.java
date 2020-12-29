package com.piyush.psds.facebook.searching_and_sorting;

public class FindFirstAndLastPositionOfElementInSortedArray {

    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        res[0] = bs(nums, target, 0, nums.length-1, true);
        res[1] = bs(nums, target, 0, nums.length-1, false);
        return res;
    }

    private int bs(int[] nums, int target, int left, int right, boolean lm) {
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) {
                if(lm) {
                    if(mid - 1 < 0 || nums[mid] != nums[mid-1]) {
                        return mid;
                    } else {
                        right = mid-1;
                    }
                } else {
                    if(mid+1 >= nums.length || nums[mid] != nums[mid+1]) {
                        return mid;
                    } else {
                        left = mid + 1;
                    }
                }
            } else if(nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
