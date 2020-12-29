package com.piyush.psds.facebook.dp;

public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        LongestPalindromicSubstring l = new LongestPalindromicSubstring();
        System.out.println(l.longestPalindrome("bab"));
    }

    // n : 8ms
    public String longestPalindrome(String s) {
        if(s.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('#');
        for(char ch : s.toCharArray()) {
            sb.append(ch).append('#');
        }
        return manacher(sb.toString(), s);
    }

    // Using manacher's algo
    public String manacher(String s, String original) {
        int n = s.length();
        // palindrome center
        int c = 0;
        // right boundary of plaindrome centered around
        int r = 0;
        // p[i] = x : palindrome centered around i, expanding x elements eeach direction
        int[] p = new int[n];

        for(int i=0; i < n; i++) {
            // finding the mirror of i, centered around c.
            int mirr = 2 * c - i;
            // if i is within the boundaries of palindrome centered around c.
            if(i < r) {
                // copy the palindrome length of mirr but if it is expanding beyond the boudharies of palindrome centered around c, limit it till there.
                p[i] = Math.min(p[mirr], r - i);
            }
            // start expannding beyond the size of p[i]
            while((i - (1+p[i])) >= 0 && (i + (1+p[i])) < s.length() && (s.charAt(i - (1+p[i])) == s.charAt(i + (1+p[i])))) {
                p[i]++;
            }
            // if i has expanded beyong the r, then change this to new center
            if(i+p[i] > r) {
                c = i;
                r = i+p[i];
            }
        }

        int index = 0;
        int len = 0;
        for(int i=0; i < p.length; i++) {
            if(p[i] > len) {
                len = p[i];
                index = i;
            }
        }
        int start = (index/2) - (len/2);
        int end = index % 2 == 0 ? (index / 2) + (len / 2) - 1 : (index / 2) + (len / 2);
        return original.substring(start, end+1);
    }

    // n * n : 214 ms
    public String longestPalindrome_2(String s) {
        int n = s.length();
        if(n == 0) {
            return "";
        }
        int start = 0;
        int maxLength = 1;
        int[][] mem = new int[n][n];
        // one len palindrome
        for(int i=0; i < n; i++) {
            mem[i][i] = 1;
        }
        // two length palindrome
        for(int i=0; i < n-1; i++) {
            if(s.charAt(i) == s.charAt(i+1)) {
                mem[i][i+1] = 1;
                start = i;
                maxLength = 2;
            }
        }
        // more than length of 3.
        for(int len=3; len <= s.length(); len++) {
            for(int i=0; i < n-len+1; i++) {
                int j = i + len - 1;
                if(s.charAt(i) == s.charAt(j) && mem[i + 1][j - 1] == 1) {
                    mem[i][j] = 1;
                    start = i;
                    maxLength = len;
                }
            }
        }
        return s.substring(start, start+maxLength);
    }

    // n * n : 23 ms
    public String longestPalindrome_1(String s) {
        if(s.length() == 0) {
            return "";
        }
        int start = 0;
        int end = 0;
        for(int i=0; i < s.length(); i++) {
            int size1 = getPalindromeSize(s, i, i);
            int size2 = getPalindromeSize(s, i, i+1);
            int max = Math.max(size1, size2);
            if(max > end - start) {
                start = i - (max-1) / 2;
                end = i + (max) / 2;
            }
        }
        return s.substring(start, end+1);
    }

    private int getPalindromeSize(String s, int i, int j) {
        while(i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }
        return j-i-1;
    }


}
