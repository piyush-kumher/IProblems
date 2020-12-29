package com.piyush.psds.facebook.recursion;

public class RegularExpressionMatch {

    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;
        for(int i=1; i < p.length(); i++) {
            if(p.charAt(i) == '*') {
                dp[0][i+1] = dp[0][i-1];
            }
        }
        for(int i=0; i < s.length(); i++) {
            for(int j=0; j < p.length(); j++) {
                if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
                    dp[i+1][j+1] = dp[i][j];
                } else if(p.charAt(j) == '*') {
                    dp[i+1][j+1] = dp[i+1][j-1];
                    if(s.charAt(i) == p.charAt(j-1) || p.charAt(j-1) == '.') {
                        dp[i+1][j+1] = dp[i+1][j+1] || dp[i][j+1];
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }

}
