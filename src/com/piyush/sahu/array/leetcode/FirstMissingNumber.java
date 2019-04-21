package com.piyush.sahu.array.leetcode;

/**
 * https://leetcode.com/problems/first-missing-positive/
 */
public class FirstMissingNumber {
    public int firstMissingPositive(int[] nums) {
        for(int i=0; i < nums.length; i++){
            for(int j=nums[i]; j > 0 && j <= nums.length && nums[j-1] != j; j=nums[i]){
                int temp = nums[j-1];
                nums[j-1] = nums[i];
                nums[i] = temp;
            }
        }
        for(int i = 0; i < nums.length; ++ i) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length+1;
    }

    public static void main(String[] args) {
        FirstMissingNumber fmn = new FirstMissingNumber();
        //int[] arr = {1, 2, 0};
        int[] arr = {3,4,-1,1};
        //int[] arr = {7,8,9,11,12};
        System.out.println(fmn.firstMissingPositive(arr));
    }
}
