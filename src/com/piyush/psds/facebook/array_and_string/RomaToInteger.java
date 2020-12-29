package com.piyush.psds.facebook.array_and_string;

import java.util.HashMap;
import java.util.Map;

public class RomaToInteger {

    static Map<String, Integer> map = new HashMap<>();
    static {
        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);
        map.put("IV", 4);
        map.put("IX", 9);
        map.put("XL", 40);
        map.put("XC", 90);
        map.put("CD", 400);
        map.put("CM", 900);
    }
    public int romanToInt(String s) {
        int number = 0;
        for(int i=0; i < s.length(); i++) {
            if(i < s.length()-1 && map.containsKey(s.substring(i, i+2))) {
                number += map.get(s.substring(i, i+2));
                i++;
            } else {
                number += map.get(s.substring(i, i+1));
            }
        }
        return number;
    }

}
