package com.piyush.psds.facebook.random;

public class ValidPalindrome2 {

    public boolean validPalindrome(String s) {
        return validPalindrome(s, 0, s.length()-1, false);
    }

    private boolean validPalindrome(String s, int i, int j, boolean lifelineUsed) {
        while(i < j) {
            if(s.charAt(i) != s.charAt(j)) {
                if(!lifelineUsed) {
                    return validPalindrome(s, i+1, j, true) || validPalindrome(s, i, j-1, true);
                }
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

}
