package com.piyush.psds.facebook.searching_and_sorting;

public class FindPeekElement {

    // O(nlogn)
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[mid + 1])
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }

    // O(n)
    public int findPeakElement_1(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1])
                return i;
        }
        return nums.length - 1;
    }

    // O(n)
    public int findPeakElement_2(int[] nums) {
        for(int i=0; i < nums.length; i++) {
            boolean check = ((i == 0 || nums[i - 1] < nums[i])  &&
                    (i == nums.length - 1 || nums[i + 1] < nums[i]));
            if(check) {
                return i;
            }
        }
        return -1;
    }

    // O(n)
    public int findPeakElement_3(int[] nums) {
        for(int i=0; i < nums.length; i++) {
            boolean check = ((i==0 ? Integer.MIN_VALUE <= nums[i] : nums[i-1] < nums[i])  &&
                    (i == nums.length-1 ? Integer.MIN_VALUE <= nums[i] : nums[i+1] < nums[i]));
            if(check) {
                return i;
            }
        }
        return -1;
    }

}
