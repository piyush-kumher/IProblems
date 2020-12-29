package com.piyush.psds.facebook.array_and_string;

import java.util.*;

public class ThreeSum {

    public List<List<Integer>> threeSum_1(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        Set<Integer> dups = new HashSet<>();
        Map<Integer, Integer> seen = new HashMap<>();
        for(int i=0; i < nums.length-1; i++) {
            if(dups.add(nums[i])) {
                for(int j=i+1; j < nums.length; j++) {
                    int c = - nums[i] - nums[j];
                    if(seen.containsKey(c) && seen.get(c) == i) {
                        List<Integer> r = Arrays.asList(c, nums[i], nums[j]);
                        Collections.sort(r);
                        res.add(r);
                    }
                    seen.put(nums[j], i);
                }
            }
        }
        return new ArrayList<>(res);
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        for(int i=0; i < nums.length; i++) {
            if(i == 0 || nums[i-1] != nums[i]) {
                findSum(nums, i, list);
            }
        }
        return list;
    }

    private void findSum(int[] nums, int pivot, List<List<Integer>> list) {
        int l = pivot + 1;
        int r = nums.length - 1;
        while(l < r) {
            int sum = nums[pivot] + nums[l] + nums[r];
            if(sum < 0) {
                l++;
            } else if(sum > 0) {
                r --;
            } else {
                list.add(Arrays.asList(nums[pivot], nums[l++], nums[r--]));
                while(l < nums.length && nums[l-1] == nums[l]) {
                    l++;
                }
            }
        }
    }

}
