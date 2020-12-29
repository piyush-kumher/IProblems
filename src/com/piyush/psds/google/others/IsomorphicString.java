package com.piyush.psds.google.others;

import java.util.HashMap;
import java.util.Map;

public class IsomorphicString {

    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> mem = new HashMap<>();
        Map<Character, Character> rev = new HashMap<>();
        if (s.length() != t.length()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            if (mem.containsKey(c1) && mem.get(c1) != c2) {
                return false;
            }
            if (rev.containsKey(c2) && rev.get(c2) != c1) {
                return false;
            }
            mem.put(c1, c2);
            rev.put(c2, c1);
        }
        return true;
    }

}
