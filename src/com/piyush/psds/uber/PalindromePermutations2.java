package com.piyush.psds.uber;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/palindrome-permutation-ii/

/**
 * Given a string s, return all the palindromic permutations (without duplicates) of it.
 * Return an empty list if no palindromic permutation could be form.
 *
 * Example 1:
 *
 * Input: "aabb"
 * Output: ["abba", "baab"]
 *
 * Example 2:
 *
 * Input: "abc"
 * Output: []
 */
public class PalindromePermutations2 {

        public List<String> generatePalindromes(String s) {
        List<String> res = new ArrayList<>();
        int[] letters = new int[128];
        int count = 0;
        for(char c : s.toCharArray()) {
            letters[c]++;
            count = (letters[c] % 2 == 0) ? count-1 : count+1;
        }
        if(s.equals("") || count > 1) {
            return res;
        }
        if(s.length()%2 == 0 && count > 0) {
            return res;
        }
        String st = "";
        for(int i=0; i < 128; i++) {
            if(letters[i] % 2 == 1) {
                st = st + (char) i;
            }
        }
        backtrack(letters, st, s.length(), res);
        return res;
    }

    private void backtrack(int[] letters, String st, int length, List<String> res) {
        if(st.length() == length) {
            res.add(st);
        } else {
            for(int i=0; i < letters.length; i++) {
                if(letters[i] > 1) {
                    letters[i] = letters[i]-2;
                    String temp = ((char) i) + st + ((char) i);
                    backtrack(letters, temp, length, res);
                    letters[i] = letters[i]+2;
                }
            }
        }
    }

}
