package com.piyush.psds.google.recursion;

import java.util.*;

public class LetterCombinationOfAPhoneNumber {

    static Map<Character, List<Character>> map = new HashMap<>();
    static {
        map.put('2', Arrays.asList('a', 'b', 'c'));
        map.put('3', Arrays.asList('d', 'e', 'f'));
        map.put('4', Arrays.asList('g', 'h', 'i'));
        map.put('5', Arrays.asList('j', 'k', 'l'));
        map.put('6', Arrays.asList('m', 'n', 'o'));
        map.put('7', Arrays.asList('p', 'q', 'r', 's'));
        map.put('8', Arrays.asList('t', 'u', 'v'));
        map.put('9', Arrays.asList('w', 'x', 'y', 'z'));
    }

    public List<String> letterCombinations(String digits) {
        if(digits.length() == 0) {
            return new ArrayList<>();
        }
        return recurse(digits, 0);
    }

    private List<String> recurse(String digits, int index) {
        if(index >= digits.length()) {
            return Arrays.asList("");
        }
        char ch = digits.charAt(index);
        List<String> result = new ArrayList<>();
        List<String> list = recurse(digits, index + 1);
        for(char letter : map.get(ch)) {
            for(String res : list) {
                result.add(letter + res);
            }
        }
        return result;
    }

}
