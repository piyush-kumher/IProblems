package com.piyush.psds.facebook.array_and_string;

public class OneEditDistance {

    public boolean isOneEditDistance(String s, String t) {
        int m = s.length();
        int n = t.length();
        if (m < n - 1 || m > n + 1) {
            return false;
        }
        int i = 0;
        int j = 0;
        int edit = 0;
        while (i < m && j < n) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            } else {
                edit++;
                if (edit > 1) {
                    return false;
                }
                if (m > n) {
                    i++;
                } else if (m < n) {
                    j++;
                } else {
                    i++;
                    j++;
                }
            }
        }
        if (i < m || j < n) {
            edit++;
        }
        return edit == 1;
    }

}
