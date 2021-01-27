package com.piyush.psds.facebook.random;

import java.util.HashMap;
import java.util.Map;

public class SubArrayOfSumK {

    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        int sum = 0;
        map.put(0, 1);
        for(int i=0; i < nums.length; i++) {
            sum += nums[i];
            if(map.containsKey(sum-k)) {
                count += map.get(sum-k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

}
