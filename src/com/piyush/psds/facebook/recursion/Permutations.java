package com.piyush.psds.facebook.recursion;

import java.util.*;

public class Permutations {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> l = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();
        for (int num : nums) {
            numbers.add(num);
        }
        if (nums == null || nums.length == 0) {
            return l;
        }
        backtrack(numbers, l, 0);
        return l;
    }

    private void backtrack(List<Integer> nums, List<List<Integer>> res, int start) {
        if (start == nums.size()) {
            res.add(new ArrayList<>(nums));
            return;
        }
        for (int i = start; i < nums.size(); i++) {
            Collections.swap(nums, start, i);
            backtrack(nums, res, start + 1);
            Collections.swap(nums, i, start);
        }
    }

}
