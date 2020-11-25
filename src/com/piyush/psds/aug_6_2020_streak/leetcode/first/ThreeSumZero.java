package com.piyush.psds.aug_6_2020_streak.leetcode.first;

import java.util.*;

public class ThreeSumZero {

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        ThreeSumZero t = new ThreeSumZero();
        System.out.println(t.threeSum(nums));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        Set<Integer> dups = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (dups.add(i)) {
                Map<Integer, Integer> seen = new HashMap<>();
                for (int j = i + 1; j < nums.length; j++) {
                    int comp = -nums[i] - nums[j];
                    if (seen.containsKey(comp)) {
                        List<Integer> triplet = Arrays.asList(nums[i], nums[j], comp);
                        Collections.sort(triplet);
                        res.add(triplet);
                    }
                    seen.put(nums[j], i);
                }
            }
        }
        return new ArrayList<>(res);
    }

    public List<List<Integer>> threeSum_1(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                twoSumSorted(nums, i, res);
            }
        }
        return res;
    }

    private void twoSumSorted(int[] nums, int pivot, List<List<Integer>> res) {
        int i = pivot + 1;
        int j = nums.length - 1;
        while (i < j) {
            int sum = nums[pivot] + nums[i] + nums[j];
            if (sum > 0) {
                j--;
            } else if (sum < 0) {
                i++;
            } else {
                res.add(Arrays.asList(nums[pivot], nums[i++], nums[j--]));
                while (i < j && nums[i - 1] == nums[i]) {
                    i++;
                }
            }
        }
    }


}
