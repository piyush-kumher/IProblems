package com.piyush.psds.google.array_string;

import java.util.*;

public class LengthOfLongestSubstringNonRepeating {

    public int lengthOfLongestSubstring(String s) {
        int j=0;
        int max = 0;
        Map<Character, Integer> map = new HashMap<>();
        for(int i=0; i < s.length(); i++) {
            if(map.containsKey(s.charAt(i)) && map.get(s.charAt(i)) >= j) {
                if(max < (i-j)) {
                    max = (i-j);
                }
                j = map.get(s.charAt(i)) + 1;
            }
            map.put(s.charAt(i), i);
        }
        if(max < (s.length()-j)) {
            max = (s.length()-j);
        }
        return max;
    }

    public static void main(String[] args) {
        LengthOfLongestSubstringNonRepeating l = new LengthOfLongestSubstringNonRepeating();
        String s = "dvdf";
        System.out.println(l.lengthOfLongestSubstring(s));
    }
}
