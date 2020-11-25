package com.piyush.psds.aug_6_2020_streak.dp;

import java.util.HashMap;
import java.util.Map;

public class DecodeWays {

    Map<Integer, Integer> mem = new HashMap<>();

    public static void main(String[] args) {
        DecodeWays d = new DecodeWays();
        System.out.println(d.numDecodingsIter("00"));
    }

    public int numDecodings(String s) {
        return recurse(s, 0);
    }

    private int recurse(String s, int start) {
        if (s.length() == start) {
            return 1;
        }
        int firstChar = s.charAt(start) - '0';
        int i = firstChar > 0 ? recurse(s, start + 1) : 0;
        int j = 0;
        if (s.length() > start + 1) {
            int secondChar = s.charAt(start + 1) - '0';
            if (firstChar == 1 || (firstChar == 2 && secondChar <= 6)) {
                j = recurse(s, start + 2);
            }
        }
        return i + j;
    }

    private int recurseWithMem(String s, int start) {
        if (s.length() == start) {
            return 1;
        }
        if (s.charAt(start) - '0' == 0) {
            return 0;
        }
        if (start == s.length() - 1) {
            return 1;
        }
        if (mem.containsKey(start)) {
            return mem.get(start);
        }
        int ans = recurseWithMem(s, start + 1);
        if (Integer.parseInt(s.substring(start, start + 2)) <= 26) {
            ans += recurseWithMem(s, start + 2);
        }
        mem.put(start, ans);
        return ans;
    }


    public int numDecodingsIter(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != '0') {
                dp[i + 1] += dp[i];
            }
            int twoDigits = Integer.parseInt(s.substring(i - 1, i + 1));
            if (twoDigits >= 10 && twoDigits <= 26) {
                dp[i + 1] += dp[i - 1];
            }
        }
        return dp[s.length()];
    }

    public int numDecodingsIter1(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int p0 = 1;
        int p1 = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 1; i < s.length(); i++) {
            int temp = 0;
            if (s.charAt(i) != '0') {
                temp += p1;
            }
            int twoDigits = Integer.parseInt(s.substring(i - 1, i + 1));
            if (twoDigits >= 10 && twoDigits <= 26) {
                temp += p0;
            }
            p0 = p1;
            p1 = temp;
        }
        return p1;
    }

}
