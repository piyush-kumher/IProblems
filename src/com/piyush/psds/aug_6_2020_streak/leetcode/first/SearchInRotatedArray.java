package com.piyush.psds.aug_6_2020_streak.leetcode.first;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array/solution/
 */
public class SearchInRotatedArray {

    public static void main(String[] args) {
        int[] arr = {5,1,3};
        SearchInRotatedArray s = new SearchInRotatedArray();
        System.out.println(s.search(arr, 3));
    }

    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while(start <= end) {
            int mid = start + (end - start) / 2;
            if(nums[mid] == target) {
                return mid;
            }
            if(nums[mid] >= nums[start]) {
                if(target >= nums[start] && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if(target > nums[mid] && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid -1;
                }
            }
        }
        return -1;
    }

}
