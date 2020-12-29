package com.piyush.psds.facebook.others;

import java.util.Arrays;

public class PermutationInString {

    public boolean checkInclusion(String s1, String s2) {
        if(s1.length() == 0) {
            return true;
        }
        if(s1.length() > s2.length()) {
            return false;
        }
        int[] s1m = new int[26];
        int[] s2m = new int[26];
        for(char c : s1.toCharArray()) {
            s1m[c - 'a']++;
        }
        for(int i=0; i < s2.length(); i++) {
            char c = s2.charAt(i);
            s2m[c - 'a']++;
            if(i >= s1.length()) {
                s2m[s2.charAt(i-s1.length()) - 'a']--;
            }
            if(Arrays.equals(s1m, s2m)) {
                return true;
            }
        }
        return false;
    }

}
