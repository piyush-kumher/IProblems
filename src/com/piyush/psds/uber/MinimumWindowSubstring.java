package com.piyush.psds.uber;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {

    public static void main(String[] args) {
        MinimumWindowSubstring mws = new MinimumWindowSubstring();
        System.out.println(mws.minWindow("ADOBECODEBANC", "ABC"));
    }

    public String minWindow(String s, String t) {
        if (s.length() == 0 || t.length() == 0) {
            return "";
        }
        int start = 0;
        int end = 0;
        int length = -1;
        Map<Character, Integer> mapT = new HashMap<>();
        for (char c : t.toCharArray()) {
            mapT.put(c, mapT.getOrDefault(c, 0) + 1);
        }
        int required = mapT.size();
        int left = 0;
        int right = 0;
        Map<Character, Integer> mapS = new HashMap<>();
        int formed = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            mapS.put(c, mapS.getOrDefault(c, 0) + 1);
            if (mapT.containsKey(c) && mapT.get(c).intValue() == mapS.get(c).intValue()) {
                formed++;
            }
            while (left <= right && formed == required) {
                char temp = s.charAt(left);
                if (length == -1 || (right - left + 1) < length) {
                    start = left;
                    end = right;
                    length = right - left + 1;
                }
                mapS.put(temp, mapS.get(temp) - 1);
                if (mapT.containsKey(temp) && mapS.get(temp) < mapT.get(temp)) {
                    formed--;
                }
                left++;
            }
            right++;
        }
        return length == -1 ? "" : s.substring(start, end + 1);
    }

}
