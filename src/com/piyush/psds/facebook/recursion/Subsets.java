package com.piyush.psds.facebook.recursion;

import java.util.*;

public class Subsets {

    public static void main(String[] args) {
        Subsets s = new Subsets();
        int[] nums = {1,2,3};
        System.out.println(s.subsets(nums));
    }

    public List<List<Integer>> subsets_1(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        for(int i= (int)Math.pow(2, n); i < (int)Math.pow(2, n+1); i++) {
            String bitMask = Integer.toBinaryString(i).substring(1);
            List<Integer> curr = new ArrayList<>();
            for(int j=0; j < bitMask.length(); j++) {
                if(bitMask.charAt(j) == '1') {
                    curr.add(nums[j]);
                }
            }
            ans.add(curr);
        }
        return ans;
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        doIt(nums, 0, new LinkedList<Integer>(), result);
        return result;
    }

    private void doIt(int[] nums, int i, LinkedList<Integer> comb, List<List<Integer>> result) {
        if(i == nums.length) {
            result.add(new ArrayList<>(comb));
            return;
        }
        doIt(nums, i+1, comb, result);
        comb.add(nums[i]);
        doIt(nums, i+1, comb, result);
        comb.removeLast();
    }

}
