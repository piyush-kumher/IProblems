package com.piyush.psds.facebook.array_and_string;

public class ProductOfArrayButSelf {

        public int[] productExceptSelf_1(int[] nums) {
            int[] res = new int[nums.length];
            int mul = 1;
            int zeroCount = 0;
            for(int i=0; i < nums.length; i++) {
                zeroCount += (nums[i] == 0 ? 1 : 0);
                mul *= (nums[i] == 0 ? 1 : nums[i]);
            }
            if(zeroCount <= 1) {
                for(int i=0; i < nums.length; i++) {
                    res[i] = (nums[i] != 0 && zeroCount > 0) ? 0 : (mul / (nums[i] == 0 ? 1 : nums[i]));
                }
            }
            return res;
        }

    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        res[0] = 1;
        for(int i=1; i < nums.length; i++) {
            res[i] = res[i-1] * nums[i-1];
        }
        int mul = nums[nums.length - 1];
        for(int i=nums.length-2; i >= 0; i--) {
            res[i] = res[i] * mul;
            mul = mul * nums[i];
        }
        return res;
    }


}
