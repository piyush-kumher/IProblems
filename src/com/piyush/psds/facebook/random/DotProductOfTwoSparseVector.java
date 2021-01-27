package com.piyush.psds.facebook.random;

import java.util.HashMap;
import java.util.Map;

public class DotProductOfTwoSparseVector {

    public Map<Integer, Integer> map= new HashMap<>();

    DotProductOfTwoSparseVector(int[] nums) {
        for(int i=0; i < nums.length; i++) {
            if(nums[i] != 0) {
                map.put(i, nums[i]);
            }
        }
    }

    // Return the dotProduct of two sparse vectors
    public int dotProduct(DotProductOfTwoSparseVector vec) {
        int sum = 0;
        for(Map.Entry<Integer, Integer> e : vec.map.entrySet()) {
            int i = e.getKey();
            if(map.containsKey(i)) {
                sum += e.getValue() * map.get(i);
            }
        }
        return sum;
    }
}
