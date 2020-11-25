package com.piyush.psds.aug_6_2020_streak.leetcode.random;

import java.util.*;

public class LetterCombinationOfAPhoneNumber {

    Map<String, String> phone = new HashMap<String, String>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if(digits.length() != 0) {
            backtrack("", digits, result);
        }
        return result;
    }

    public void backtrack(String comb, String digits, List<String> result) {
        if(digits.length() == 0) {
            result.add(comb);
        } else {
            String digit = digits.substring(0, 1);
            String letters = phone.get(digit);
            for(char c : letters.toCharArray()) {
                backtrack(comb + c, digits.substring(1), result);
            }
        }
    }

}
