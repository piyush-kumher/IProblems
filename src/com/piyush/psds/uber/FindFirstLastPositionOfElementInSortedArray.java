package com.piyush.psds.uber;

// https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/

public class FindFirstLastPositionOfElementInSortedArray {

    public int[] searchRange(int[] nums, int target) {
        int leftMost = customBinarySearch(nums, target, 0, nums.length-1, true, -1);
        int rightMost = customBinarySearch(nums, target, 0, nums.length-1, false, -1);
        return new int[]{leftMost, rightMost};
    }

    private int customBinarySearch(int[] nums, int target, int l, int r, boolean leftMost, int previous) {
        if(l > r) {
            return previous;
        }
        int mid = l + (r - l) / 2;
        if(nums[mid] == target) {
            if(leftMost) {
                return customBinarySearch(nums, target, l, mid-1, leftMost, mid);
            } else {
                return customBinarySearch(nums, target, mid+1, r, leftMost, mid);
            }
        } else if(nums[mid] > target) {
            return customBinarySearch(nums, target, l, mid-1, leftMost, previous);
        } else {
            return customBinarySearch(nums, target, mid+1, r, leftMost, previous);
        }
    }

}
