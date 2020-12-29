package com.piyush.psds.facebook.array_and_string;

public class ValidPalindrome2 {

    public boolean validPalindrome(String s) {
        return vp(s, 0, s.length() - 1, 0);
    }

    private boolean vp(String s, int i, int j, int edits) {
        while(i < j) {
            if(s.charAt(i) != s.charAt(j)) {
                if(edits <= 0) {
                    return vp(s, i+1, j, edits+1) || vp(s, i, j-1, edits+1);
                }
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

}
