package com.piyush.psds.facebook.array_and_string;

import java.util.HashMap;
import java.util.Map;

public class LongestStringWithAtMostKCharacters {

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int n = s.length();
        if(n == 0 || k == 0) {
            return 0;
        }
        int max = 1;
        Map<Character, Integer> map = new HashMap<>();
        map.put(s.charAt(0), 1);
        int start = 0;
        int curr = 1;
        while(curr < n) {
            char ch = s.charAt(curr);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            if(map.size() <= k) {
                max = Math.max(max, curr-start+1);
            }
            while(map.size() > k) {
                char rch = s.charAt(start++);
                map.put(rch, map.get(rch) - 1);
                if(map.get(rch) == 0) {
                    map.remove(rch);
                }
            }
            curr++;
        }
        return max;
    }

}
