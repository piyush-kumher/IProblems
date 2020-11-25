package com.piyush.psds.array.leetcode;

/**
 * https://leetcode.com/problems/missing-number/description/
 */
public class MissingNumber {

    /**
     * Runtime: 1 ms, faster than 41.19% of Java online submissions for Missing Number.
     * Memory Usage: 41 MB, less than 61.27% of Java online submissions for Missing Number.
     */
    public int missingNumber(int[] nums) {
        for(int i=0; i < nums.length; i++){
            for(int j=nums[i]; i!=j && j < nums.length; j=nums[i]){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        for(int i=0; i < nums.length; i++){
            if(nums[i] >= nums.length || nums[i] < 0){
                return i;
            }
        }
        return nums.length;
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Missing Number.
     * Memory Usage: 41.1 MB, less than 60.41% of Java online submissions for Missing Number.
     */
    public int missingNumberNew(int[] nums) {

        int xor = 0, i = 0;
        for (i = 0; i < nums.length; i++) {
            xor = xor ^ i ^ nums[i];
        }

        return xor ^ i;
    }

    public int missingNumberNew1(int[] nums) { //sum
        int len = nums.length;
        int sum = (len)*(len+1)/2;
        for(int i=0; i<len; i++)
            sum-=nums[i];
        return sum;
    }


    public static void main(String[] args) {
        MissingNumber mn = new MissingNumber();
        int[] arr = {2, 0, 3};
        System.out.println(mn.missingNumber(arr));
    }


}
