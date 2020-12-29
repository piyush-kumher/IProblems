package com.piyush.psds.facebook.array_and_string;

public class ValidPalindrome {

    public static void main(String[] args) {
        ValidPalindrome vp = new ValidPalindrome();
        System.out.println(vp.isPalindrome("0P"));
        System.out.println(vp.isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(vp.isPalindrome("race a car"));
    }

    public boolean isPalindrome(String s) {
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
