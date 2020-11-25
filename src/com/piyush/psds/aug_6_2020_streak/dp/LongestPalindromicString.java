package com.piyush.psds.aug_6_2020_streak.dp;

public class LongestPalindromicString {

    public static void main(String[] args) {
        LongestPalindromicString l = new LongestPalindromicString();
        String s = "bababd";
        System.out.println(l.longestPalindrome(s));
    }


    public String longestPalindrome(String s) {
        if(s == null || s.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("#");
        for(int i=0; i < s.length(); i++) {
            sb.append(s.charAt(i)).append("#");
        }
        return manichers(sb.toString(), s);
    }

    private String manichers(String s, String old) {
        int c = 0; int r = 0;
        int p[] = new int[s.length()];
        for(int i=0; i < s.length(); i++) {
            int mirr = 2 * c - i;
            if(i < r) {
                p[i] = Math.min(p[mirr], r-i);
            }
            while((i- (1+p[i])) >=0 && (i+(1+p[i])) < s.length() && s.charAt(i- (1+p[i])) == s.charAt(i+(1+p[i]))) {
                p[i]++;
            }
            if(i + p[i] > r) {
                r = i + p[i];
                c = i;
            }
        }
        int index=0; int len = 0;
        for(int i=0; i < s.length(); i++) {
            if(p[i] > len) {
                index = i;
                len = p[i];
            }
        }
        int start = (index / 2) - (len / 2);
        int end = index % 2 == 0 ? (index / 2) + (len / 2) - 1 : (index / 2) + (len / 2);
        return old.substring(start, end+1);
    }


    public String longestPalindrome_dp(String s) {
        int len = s.length();
        int maxLength = 1;
        int start = 0;
        int[][] mem = new int[len][len];
        for (int i = 0; i < len; i++) {
            mem[i][i] = 1;
        }
        for (int i = 0; i < len - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                mem[i][i + 1] = 1;
                start = i;
                maxLength = 2;
            }
        }
        for (int k = 3; k <= len; k++) {
            for (int i = 0; i < len - k + 1; i++) {
                int j = i + k - 1;
                if (s.charAt(i) == s.charAt(j) && mem[i + 1][j - 1] == 1) {
                    mem[i][j] = 1;
                    start = i;
                    maxLength = k;
                }
            }
        }
        return s.substring(start, start + maxLength);
    }


    public String longestPalindrome_best(String s) {
        int len = s.length();
        int start = 0, end = 0;
        for (int i = 0; i < len; i++) {
            final int max1 = getPalinLength(s, i, i);
            final int max2 = getPalinLength(s, i, i + 1);
            int max = Math.max(max1, max2);
            if(max > end - start) {
                start = i - (max - 1) / 2;
                end = i + max / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int getPalinLength(String s, int a, int b) {
        while (a >= 0 && b < s.length()) {
            if (s.charAt(a) != s.charAt(b)) {
                break;
            }
            a--;
            b++;
        }
        return b - a - 1;
    }

}
