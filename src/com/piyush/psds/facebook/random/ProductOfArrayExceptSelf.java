package com.piyush.psds.facebook.random;

public class ProductOfArrayExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        int[] ans = new int[nums.length];
        if(nums.length == 0) {
            return ans;
        }
        ans[0] = 1;
        int mul = nums[0];
        for(int i=1; i < nums.length; i++) {
            ans[i] = mul;
            mul *= nums[i];
        }
        mul = nums[nums.length-1];
        for(int i=nums.length-2; i>=0; i--) {
            ans[i] = ans[i] * mul;
            mul *= nums[i];
        }
        return ans;
    }

}
