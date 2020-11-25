package com.piyush.psds.aug_6_2020_streak.leetcode.random;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/longest-repeating-substring/
 * Binary Search + Rabin-Karp algorithm
 * Given a string S, find out the length of the longest repeating substring(s). Return 0 if no repeating substring exists.
 * <p>
 * Example 1:
 * <p>
 * Input: S = "abcd"
 * Output: 0
 * Explanation: There is no repeating substring.
 * <p>
 * Example 2:
 * <p>
 * Input: S = "abbaba"
 * Output: 2
 * Explanation: The longest repeating substrings are "ab" and "ba", each of which occurs twice.
 * <p>
 * Example 3:
 * <p>
 * Input: S = "aabcaabdaab"
 * Output: 3
 * Explanation: The longest repeating substring is "aab", which occurs 3 times.
 * <p>
 * Example 4:
 * <p>
 * Input: S = "aaaaa"
 * Output: 4
 * Explanation: The longest repeating substring is "aaaa", which occurs twice.
 */
public class LongestRepeatingSubstring {

    public int longestRepeatingSubstring(String s) {
        int n = s.length();
        int l = 0;
        int r = n;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (search(s, n, mid) == -1) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l - 1;
    }

    // HashMap
    private int search(String s, int n, int mid) {
        Set<String> mem = new HashSet<>();
        for (int i = 0; i < n - mid + 1; i++) {
            String sub = s.substring(i, i + mid);
            if (!mem.contains(sub)) {
                mem.add(sub);
            } else {
                return i;
            }
        }
        return -1;
    }

    // HashSet + HashCode
    private int search_1(String s, int n, int mid) {
        Set<Integer> mem = new HashSet<>();
        for (int i = 0; i < n - mid + 1; i++) {
            String sub = s.substring(i, i + mid);
            if (!mem.contains(sub.hashCode())) {
                mem.add(sub.hashCode());
            } else {
                return i;
            }
        }
        return -1;
    }


    // Rabin-Karp algorithm
    public int longestRepeatingSubstring_1(String S) {
        int n = S.length();
        // base value for the rolling hash function
        int a = 26;
        // modulus value for the rolling hash function to avoid overflow
        long modulus = (long) Math.pow(2, 24);

        // binary search, L = repeating string length
        int left = 1, right = n;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (search(S, mid, a, modulus, n) != -1) left = mid + 1;
            else right = mid - 1;
        }

        return left - 1;
    }

    public int search(String s, int mid, int a, long modulus, int n) {
        // compute the hash of string S[:L]
        long h = 0;
        for (int i = 0; i < mid; ++i) {
            h = (h * a + s.charAt(i)) % modulus;
        }

        // already seen hashes of strings of length L
        Set<Long> seen = new HashSet<>();
        seen.add(h);
        // const value to be used often : a**L % modulus
        long aL = 1;
        for (int i = 1; i <= mid; ++i) {
            aL = (aL * a) % modulus;
        }

        for (int start = 1; start < n - mid + 1; ++start) {
            // compute rolling hash in O(1) time
            h = (h * a - s.charAt(start - 1) * aL % modulus + modulus) % modulus;
            h = (h + s.charAt(start + mid - 1)) % modulus;
            if (seen.contains(h)) return start;
            seen.add(h);
        }
        return -1;
    }

}
