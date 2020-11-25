package com.piyush.psds.aug_6_2020_streak.leetcode.random;

import java.util.HashSet;
import java.util.Set;

public class LongestDuplicatedString {

    public String longestDupSubstring(String S) {
        int n = S.length();
        int multiplier = 26;
        long modulus = (long) Math.pow(2, 24);

        int left = 0;
        int right = n;
        String biggest = "";
        while (left <= right) {
            int mid = left + (right - left) / 2;
            String res = search(S, multiplier, modulus, n, mid);
            if (!"".equals(res)) {
                biggest = res;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return biggest;
    }

    private String search(String s, int multiplier, long modulus, int n, int mid) {
        long hash = 0;
        for (int i = 0; i < mid; i++) {
            hash = (hash * multiplier + (s.charAt(i) - 'a')) % modulus;
        }
        long aL = 1L;
        for (int i = 1; i <= mid; i++) {
            aL = (aL * multiplier) % modulus;
        }
        Set<Long> seen = new HashSet<>();
        seen.add(hash);
        for (int i = 1; i < n - mid + 1; i++) {
            hash = (hash * multiplier - (s.charAt(i - 1) - 'a') * aL % modulus + modulus) % modulus;
            hash = (hash + (s.charAt(i + mid - 1) - 'a')) % modulus;
            if (seen.contains(hash)) return s.substring(i, i + mid);
            seen.add(hash);
        }
        return "";
    }

}
