package com.piyush.psds.aug_6_2020_streak.leetcode.random;

public class ValidAnagram {

    public static void main(String[] args) {

    }

    public boolean isAnagram(String s, String t) {
        int[] count = new int[26];
        if(s.length() != t.length()) {
            return false;
        }
        for(char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        for(char c: t.toCharArray()) {
            count[c-'a']--;
        }
        for(int i: count) {
            if(i != 0) {
                return false;
            }
        }
        return true;
    }

}
