package com.piyush.psds.google.others;

import java.util.HashMap;
import java.util.Map;

public class SplitArrayIntoConsecutiveSequence {

    public boolean isPossible(int[] nums) {
        //frequency map
        Map<Integer, Integer> fm = new HashMap<>();
        // sequence which has minimum 3 element and can add the key. How many such sequence are there, that is the value.
        Map<Integer, Integer> hm = new HashMap<>();
        for(int x : nums) {
            fm.put(x, fm.getOrDefault(x, 0)+1);
        }
        for(int x : nums) {
            if(fm.get(x) == 0) {
                continue;
            }
            if(hm.containsKey(x) && hm.get(x) > 0) {
                hm.put(x, hm.get(x) -1);
                hm.put(x+1, hm.getOrDefault(x+1, 0)+1);
            } else if(fm.getOrDefault(x+1, 0) > 0 && fm.getOrDefault(x+2, 0) > 0) {
                fm.put(x+1, fm.get(x+1)-1);
                fm.put(x+2, fm.get(x+2)-1);
                hm.put(x+3, hm.getOrDefault(x+3, 0)+1);
            } else {
                return false;
            }
            fm.put(x, fm.get(x)-1);
        }
        return true;
    }

}
