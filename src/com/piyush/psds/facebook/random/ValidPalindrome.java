package com.piyush.psds.facebook.random;

public class ValidPalindrome {

    public boolean isPalindrome(String s) {
        int i=0;
        int j=s.length()-1;
        while(i < j) {
            char c1 = s.charAt(i);
            char c2 = s.charAt(j);
            if(!Character.isLetterOrDigit(c1)) {
                i++;
            } else if(!Character.isLetterOrDigit(c2)) {
                j--;
            } else {
                c1 = (c1 >= 'A' && c1 <= 'Z') ? Character.toLowerCase(c1) : c1;
                c2 = (c2 >= 'A' && c2 <= 'Z') ? Character.toLowerCase(c2) : c2;
                if(c1 != c2) {
                    return false;
                }
                i++; j--;
            }
        }
        return true;
    }


    public boolean isPalindrome1(String s) {
        int l=0;
        int r=s.length()-1;
        while(l < r) {
            char ch1 = s.charAt(l);
            char ch2 = s.charAt(r);
            if(!('a' <= ch1 && 'z' >= ch1) && !('A' <= ch1 && 'Z' >= ch1) && !('0' <= ch1 && '9' >= ch1)) {
                l++;
            } else if(!('a' <= ch2 && 'z' >= ch2) && !('A' <= ch2 && 'Z' >= ch2) && !('0' <= ch2 && '9' >= ch2)) {
                r--;
            } else if((('A' <= ch1 && 'Z' >= ch1) && ('a' <= ch2 && 'z' >= ch2) && (ch1 + 32 == ch2))
                    ||(('a' <= ch1 && 'z' >= ch1) && ('A' <= ch2 && 'Z' >= ch2) && (ch1 - 32 == ch2)) ||
                    (ch1 == ch2)) {
                r--;
                l++;
            } else {
                return false;
            }
        }
        return true;
    }
}
