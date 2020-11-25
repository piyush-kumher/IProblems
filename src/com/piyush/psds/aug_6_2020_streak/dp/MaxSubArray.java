package com.piyush.psds.aug_6_2020_streak.dp;

public class MaxSubArray {

    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        MaxSubArray m = new MaxSubArray();
        System.out.println(m.maxSubArray(arr));
        System.out.println(m.maxSubArray_1(arr, 0, arr.length - 1));
    }

    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum = Math.max(nums[i], nums[i] + sum);
            max = Math.max(max, sum);
        }
        return max;
    }

    private int maxSubArray_1(int[] nums, int left, int right) {
        if(left == right) {
            return nums[left];
        }

        int mid = left + (right - left) / 2;

        int leftMaxSum = maxSubArray_1(nums, left, mid);
        int rightMaxSum = maxSubArray_1(nums, mid + 1, right);
        int crossSum = crossSum(nums, left, right, mid);

        return Math.max(Math.max(leftMaxSum, rightMaxSum), crossSum);
    }

    private int crossSum(int[] nums, int left, int right, int mid) {
        if(left == right) {
            return nums[left];
        }

        int leftSum = Integer.MIN_VALUE;
        int currSum = 0;
        for(int i = mid; i >= left; i--) {
            currSum += nums[i];
            leftSum = Math.max(leftSum, currSum);
        }

        int rightSum = Integer.MIN_VALUE;
        currSum = 0;
        for(int i = mid + 1; i <= right; i++) {
            currSum += nums[i];
            rightSum = Math.max(rightSum, currSum);
        }
        return leftSum + rightSum;
    }

}
