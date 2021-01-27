package com.piyush.psds.facebook.random;

import java.util.HashMap;
import java.util.Map;

public class VerifyingAnAlienDictionary {

    public boolean isAlienSorted(String[] words, String order) {
        Map<Character, Integer> map = new HashMap<>();
        for(int i=0; i < order.length(); i++) {
            map.put(order.charAt(i), i);
        }
        for(int i=0; i < words.length-1; i++) {
            String w1 = words[i];
            String w2 = words[i+1];
            int j=0;
            for(;j < Math.min(w1.length(), w2.length()); j++) {
                char c1 = w1.charAt(j);
                char c2 = w2.charAt(j);
                if(c1 != c2) {
                    if(map.get(c1) > map.get(c2)) {
                        return false;
                    }
                    break;
                }
            }
            if(j == Math.min(w1.length(), w2.length()) && w1.length() > w2.length()) {
                return false;
            }
        }
        return true;
    }

}
