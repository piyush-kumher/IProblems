package com.piyush.psds.google.others;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class StringTransformToAnotherString {

    public boolean canConvert(String str1, String str2) {
        if(str1.length() != str2.length()) {
            return false;
        }
        Map<Character, Character> map = new HashMap<>();
        for(int i=0; i < str1.length(); i++) {
            char c1 = str1.charAt(i);
            char c2 = str2.charAt(i);
            if(map.containsKey(c1)) {
                if(map.get(c1) != c2) {
                    return false;
                }
            } else {
                map.put(c1, c2);
            }
        }
        // below statement is to handle the case where there is a cycle. abc -> bca   if we start from end: c -> a,
        // we now have, aba -> bca , now there are two a's which should get tranformed to b and a, which is invalid.
        // to handle that, first convert a to a character which is not present in the string, say : x
        // now we have abx -> bca, now it's easy.
        // So, we have spare character then we should not worry about cycles.
        // But what if there is no spare character, then think about below statement.
        return new HashSet<Character>(map.values()).size() < 26;
    }

}
