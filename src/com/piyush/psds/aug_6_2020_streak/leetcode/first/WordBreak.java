package com.piyush.psds.aug_6_2020_streak.leetcode.first;

import java.util.*;

/**
 * https://leetcode.com/problems/word-break/
 */
public class WordBreak {

    public static void main(String[] args) {
        String s = "leetcode";
        List<String> l = new ArrayList<String>() {{
            add("leet");
            add("code");
        }};
        WordBreak wb = new WordBreak();
        System.out.println(wb.wordBreak(s, l));
    }

    public boolean wordBreak_1(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        boolean[] explored = new boolean[s.length()];
        return recurse(s, 0, s.length(), dict, explored);
    }

    private boolean recurse(String s, int start, int end, Set<String> dict, boolean[] explored) {
        if (start >= end) {
            return true;
        }
        if (explored[start]) {
            return false;
        }
        for (int i = start + 1; i <= end; i++) {
            if (dict.contains(s.substring(start, i))) {
                boolean found = recurse(s, i, end, dict, explored);
                if (found) {
                    return found;
                }
                explored[start] = true;
            }
        }
        return false;
    }


    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

}
