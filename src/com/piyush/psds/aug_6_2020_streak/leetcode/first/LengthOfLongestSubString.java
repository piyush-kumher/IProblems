package com.piyush.psds.aug_6_2020_streak.leetcode.first;

import java.util.*;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
public class LengthOfLongestSubString {

    public static void main(String[] args) {
        LengthOfLongestSubString l = new LengthOfLongestSubString();
        String s = "abcabcbb";
        System.out.println(l.lengthOfLongestSubstring_app1(s));
    }

    public int lengthOfLongestSubstring_app1(String s) {
        int i = 0;
        int j = 0;
        int ans = 0;
        Set<Character> set = new HashSet<>();
        while (i < s.length() && j < s.length()) {
            if (set.contains(s.charAt(j))) {
                set.remove(s.charAt(i++));
            } else {
                set.add(s.charAt(j++));
                ans = Math.max(j - i, ans);
            }
        }
        return ans;
    }

    public int lengthOfLongestSubstring_app2(String s) {
        int ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int j = 0, i = 0; j < s.length(); j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(j - i + 1, ans);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }


}
