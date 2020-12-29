package com.piyush.psds.facebook.dp;

public class DecodeWays {
    public int numDecodings(String s) {
        if(s.length() == 0) {
            return 0;
        }
        int[] dp = new int[s.length()+1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        for(int i=1; i < s.length(); i++) {
            int t = Integer.parseInt(s.substring(i-1, i+1));
            if(t >= 10 && t <= 26) {
                dp[i+1] += dp[i-1];
            }
            if(s.charAt(i) != '0') {
                dp[i+1] += dp[i];
            }
        }
        return dp[s.length()];
    }
}
