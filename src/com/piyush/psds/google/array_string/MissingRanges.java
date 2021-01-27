package com.piyush.psds.google.array_string;

import java.util.ArrayList;
import java.util.List;

public class MissingRanges {

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<>();
        for(int i=0; i < nums.length; i++) {
            if(nums[i] > lower) {
                if(lower == nums[i]-1) {
                    result.add(Integer.toString(lower));
                } else {
                    result.add(lower + "->" + (nums[i]-1));
                }
            }
            lower = nums[i]+1;
        }
        if(nums.length > 0 && nums[nums.length-1] < upper) {
            int last = nums[nums.length-1];
            if(last+1 == upper) {
                result.add(Integer.toString(upper));
            } else {
                result.add((last+1) + "->" + upper);
            }
        }
        if(nums.length == 0) {
            if(lower == upper) {
                result.add(Integer.toString(upper));
            } else {
                result.add(lower + "->" + upper);
            }
        }
        return result;
    }

}
