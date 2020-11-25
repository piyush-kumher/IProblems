package com.piyush.psds.aug_6_2020_streak.leetcode.first;

/**
 * https://leetcode.com/problems/verifying-an-alien-dictionary/
 */
public class VerifyingAnAlienDictionary {

    public boolean isAlienSorted(String[] words, String order) {
        int[] dict = new int[26];
        for (int i = 0; i < order.length(); i++) {
            dict[order.charAt(i) - 'a'] = i;
        }
        for (int i = 0; i < words.length - 1; i++) {
            int j = 0;
            for (; j < Math.min(words[i].length(), words[i + 1].length()); j++) {
                if (words[i].charAt(j) != words[i + 1].charAt(j)) {
                    if (dict[words[i].charAt(j) - 'a'] > dict[words[i + 1].charAt(j) - 'a']) {
                        return false;
                    }
                    break;
                }
            }
            if (j == Math.min(words[i].length(), words[i + 1].length())
                    && words[i].length() > words[i + 1].length()) {
                return false;
            }
        }
        return true;
    }

}
