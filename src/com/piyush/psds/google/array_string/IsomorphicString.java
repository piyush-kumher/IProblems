package com.piyush.psds.google.array_string;

import java.util.HashMap;
import java.util.Map;

public class IsomorphicString {

    public boolean isIsomorphic_1(String s, String t) {
        if(s.length() != t.length()) {
            return false;
        }
        Map<Character, Character> map = new HashMap<>();
        Map<Character, Character> reverse = new HashMap<>();
        for(int i=0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            if(map.containsKey(c1)) {
                if(map.get(c1) != c2) {
                    return false;
                }
            }
            if(reverse.containsKey(c2)) {
                if(reverse.get(c2) != c1) {
                    return false;
                }
            }
            map.putIfAbsent(c1, c2);
            reverse.putIfAbsent(c2, c1);
        }
        return true;
    }

    public boolean isIsomorphic(String s, String t) {
        int size = 256;
        int len1 = s.length();
        int len2 = t.length();
        if(len1 != len2){
            return false;
        }
        boolean[] charInSec = new boolean[256];
        int[] array = new int[size];
        for (int i = 0; i< size; i++){
            array[i] = -1;
        }
        for(int i=0; i<len1; i++){
            if(array[s.charAt(i)] == -1){
                if(charInSec[t.charAt(i)]){
                    return false;
                }
                array[s.charAt(i)] = t.charAt(i);
                charInSec[t.charAt(i)] = true;
            }else if(array[s.charAt(i)] != t.charAt(i)){
                return false;
            }
        }
        return true;
    }


}
