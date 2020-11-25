package com.piyush.psds.google.array_string;

import java.util.*;

public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        for(int i=0; i < nums.length; i++) {
            if(i == 0 || nums[i-1] != nums[i]) {
                threeSum(nums, i, list);
            }
        }
        return list;
    }

    private void threeSum(int[] nums, int pivot, List<List<Integer>> list) {
        int l = pivot + 1;
        int r = nums.length-1;
        while(l < r) {
            int sum = nums[pivot] + nums[l] + nums[r];
            if(sum > 0) {
                r--;
            } else if(sum < 0) {
                l++;
            } else {
                list.add(Arrays.asList(nums[pivot], nums[l++], nums[r--]));
                while(l < r && nums[l-1] == nums[l]) {
                    l++;
                }
            }
        }
    }

    public List<List<Integer>> threeSum_1(int[] nums) {
        Set<List<Integer>> list = new HashSet<>();
        Map<Integer, Integer> seen = new HashMap<>();
        Set<Integer> dups = new HashSet<>();
        for(int i=0; i < nums.length; i++) {
            if(!dups.contains(nums[i]))  {
                dups.add(nums[i]);
                for(int j= i+1; j < nums.length; j++) {
                    int complement = -nums[i] - nums[j];
                    if(seen.containsKey(complement) && seen.get(complement) == i) {
                        List<Integer> trip = Arrays.asList(nums[i], nums[j], complement);
                        Collections.sort(trip);
                        list.add(trip);
                    }
                    seen.put(nums[j], i);
                }
            }
        }
        return new ArrayList<>(list);
    }




}
