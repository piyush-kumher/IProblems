package com.piyush.psds.google.others;

public class MaximumWindowSubSequence {

    public static void main(String[] args) {
        String s = "abcdebdde";
        String t = "bde";
        MaximumWindowSubSequence m = new MaximumWindowSubSequence();
        System.out.println(m.minWindow(s, t));
    }

    public String minWindow(String S, String T) {
        int j = 0;
        int len = S.length();
        int start = 0;
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == T.charAt(j)) {
                j++;
                if (j >= T.length()) {
                    j--;
                    int end = i + 1;
                    while (j >= 0) {
                        if (S.charAt(i) == T.charAt(j)) {
                            j--;
                        }
                        i--;
                    }
                    i++;
                    if (len > end - i) {
                        len = end - i;
                        start = i;
                    }
                    j++;
                }
            }
        }
        return len == S.length() ? "" : S.substring(start, start + len);
    }


    public String minWindow1(String S, String T) {
        int n = S.length();
        int m = T.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= m; i++) {
            dp[0][i] = -1; // no valid start
        }
        for (int i = 1; i <= n; i++) {
            dp[i][0] = i;
        }
        int minLen = Integer.MAX_VALUE;
        int startPos = -1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = -1;
                if (S.charAt(i - 1) == T.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
            if (dp[i][m] != -1) {
                int len = i - dp[i][m];
                if (len < minLen) {
                    minLen = len;
                    startPos = dp[i][m];
                }
            }
        }
        return startPos == -1 ? "" : S.substring(startPos, startPos + minLen);
    }

}
