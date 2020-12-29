package com.piyush.psds.facebook.recursion;

import java.util.*;

public class LetterCombinationOfAPhoneNumber {

    Map<Character, List<String>> phone = new HashMap<Character, List<String>>() {{
        put('2', Arrays.asList("a","b","c"));
        put('3', Arrays.asList("d","e","f"));
        put('4', Arrays.asList("g","h","i"));
        put('5', Arrays.asList("j","k","l"));
        put('6', Arrays.asList("m","n","o"));
        put('7', Arrays.asList("p","q","r", "s"));
        put('8', Arrays.asList("t","u","v"));
        put('9', Arrays.asList("w","x","y", "z"));
    }};


    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if(digits != null && digits.trim().length() > 0) {
            doIt("", digits, 0, res);
        }
        return res;
    }

    private void doIt(String comb, String digits, int index, List<String> res) {
        if(comb.length() == digits.length()) {
            res.add(comb);
            return;
        }
        for(String s : phone.get(digits.charAt(index))) {
            doIt(comb + s, digits, index+1, res);
        }
    }

    public List<String> letterCombinations_1(String digits) {
        if(digits != null && digits.trim().length() > 0) {
            return populate(digits, 0);
        }
        return new ArrayList<>();
    }

    private List<String> populate(String digits, int i) {
        if(i == digits.length()-1) {
            return phone.get(digits.charAt(i));
        }
        List<String> res = populate(digits, i+1);
        List<String> response = new ArrayList<>();
        for(String p : phone.get(digits.charAt(i))) {
            for(String r : res) {
                response.add(p + r);
            }
        }
        return response;
    }

}
