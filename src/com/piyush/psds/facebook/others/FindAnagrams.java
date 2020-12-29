package com.piyush.psds.facebook.others;

import java.util.*;

public class FindAnagrams {
    public List<Integer> findAnagrams(String s, String p) {
        int[] pcount = new int[26];
        int[] scount = new int[26];
        for(char c : p.toCharArray()) {
            pcount[c - 'a']++;
        }
        List<Integer> result = new ArrayList<>();
        for(int i=0; i < s.length(); i++) {
            char c = s.charAt(i);
            scount[c - 'a']++;
            if(i >= p.length()) {
                scount[s.charAt(i-p.length()) - 'a']--;
            }
            if(Arrays.equals(pcount, scount)) {
                result.add(i-p.length()+1);
            }
        }
        return result;
    }
}
