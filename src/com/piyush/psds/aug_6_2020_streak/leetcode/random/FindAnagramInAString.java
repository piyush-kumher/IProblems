package com.piyush.psds.aug_6_2020_streak.leetcode.random;

import java.util.*;

public class FindAnagramInAString {

    public List<Integer> findAnagrams_1(String s, String p) {
        int lenP = p.length();
        List<Integer> l = new ArrayList<>();
        String sortedP = sort(p);
        for(int i=0; i < s.length() - lenP + 1; i++) {
            String sub = s.substring(i, i+lenP);
            if(sortedP.equals(sort(sub))) {
                l.add(i);
            }
        }
        return l;
    }

    public List<Integer> findAnagrams(String s, String p) {
        int[] sCount = new int[26];
        int[] pCount = new int[26];
        List<Integer> list = new ArrayList<>();
        for(int i=0; i < p.length(); i++) {
            pCount[p.charAt(i) - 'a']++;
        }
        for(int i=0; i < s.length(); i++) {
            sCount[s.charAt(i) - 'a'] ++;
            if(i >= p.length()) {
                sCount[s.charAt(i-p.length()) - 'a']--;
            }
            if(Arrays.equals(pCount, sCount)) {
                list.add(i-p.length()+1);
            }
        }
        return list;
    }


    private String sort(String s) {
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }

}
