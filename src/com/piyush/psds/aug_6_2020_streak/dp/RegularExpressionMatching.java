package com.piyush.psds.aug_6_2020_streak.dp;

// https://leetcode.com/problems/regular-expression-matching/
public class RegularExpressionMatching {

    public static void main(String[] args) {
        RegularExpressionMatching rem = new RegularExpressionMatching();
        //System.out.println(rem.matchRegex("aa".toCharArray(), ".*".toCharArray()));
        System.out.println(rem.isMatch("aa", ".*"));
    }

    public boolean isMatch_1(String s, String p) {
        if (s.length() == 0 && p.length() == 0) {
            return true;
        }
        boolean firstMatch = !s.isEmpty() && !p.isEmpty() && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.');
        if (p.length() >= 2 && p.charAt(1) == '*') {
            return isMatch(s, p.substring(2)) || (firstMatch && isMatch(s.substring(1), p));
        } else {
            return firstMatch && isMatch(s.substring(1), p.substring(1));
        }
    }

    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*') {
                dp[0][i+1] = dp[0][i - 1];
            }
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
                    dp[i + 1][j + 1] = dp[i][j];
                } else if (p.charAt(j) == '*') {
                    dp[i + 1][j + 1] = dp[i + 1][j - 1];
                    if (s.charAt(i) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                        dp[i + 1][j + 1] = dp[i + 1][j + 1] || dp[i][j + 1];
                    }
                } else {
                    dp[i + 1][j + 1] = false;
                }
            }
        }
        return dp[s.length()][p.length()];
    }

}
