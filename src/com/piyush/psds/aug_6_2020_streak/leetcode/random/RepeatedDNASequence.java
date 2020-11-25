package com.piyush.psds.aug_6_2020_streak.leetcode.random;

import java.util.*;

/**
 * https://leetcode.com/problems/repeated-dna-sequences/solution/
 */
public class RepeatedDNASequence {

    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> seen = new HashSet<>();
        Set<String> output = new HashSet<>();
        for (int i = 0; i < s.length() - 10 + 1; i++) {
            String dna = s.substring(i, i + 10);
            if (seen.contains(dna)) {
                output.add(dna);
            } else {
                seen.add(dna);
            }
        }
        return new ArrayList<>(output);
    }

    public List<String> findRepeatedDnaSequences_RubinKarp(String s) {
        int len = 10;
        if (s.length() < len) {
            return new ArrayList<String>();
        }
        int multiplier = 4;
        int aL = (int) Math.pow(multiplier, len);
        Map<Character, Integer> map = new HashMap<Character, Integer>() {{
            put('A', 0);
            put('C', 1);
            put('G', 2);
            put('T', 2);
        }};
        Set<Integer> seen = new HashSet<>();
        Set<String> set = new HashSet<>();
        int hash = 0;
        for (int start = 0; start < s.length() - len + 1; ++start) {
            if (start != 0) {
                hash = hash * multiplier - map.get(s.charAt(start - 1)) * aL + map.get(s.charAt(start + len - 1));
            } else {
                for (int i = 0; i < len; ++i) {
                    hash = hash * multiplier + map.get(s.charAt(i));
                }
            }
            if (seen.contains(hash)) {
                set.add(s.substring(start, start + len));
            }
            seen.add(hash);
        }
        return new ArrayList<>(set);
    }
}
