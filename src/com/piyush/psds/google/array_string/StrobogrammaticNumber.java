package com.piyush.psds.google.array_string;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/strobogrammatic-number/
//A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
//
//Write a function to determine if a number is strobogrammatic. The number is represented as a string.

public class StrobogrammaticNumber {

    Map<Character, Character> map = new HashMap<>();

    public StrobogrammaticNumber() {
        map.put('0', '0');
        map.put('8', '8');
        map.put('1', '1');
        map.put('6', '9');
        map.put('9', '6');
    }

    // 0, 1, 6 -> 9, 8, 9 -> 6,
    public boolean isStrobogrammatic(String num) {
        int start = 0;
        int end = num.length() - 1;
        while(start <= end) {
            char c1 = num.charAt(start);
            char c2 = num.charAt(end);
            if(!map.containsKey(c1) || !map.containsKey(c2)) {
                return false;
            }
            if(map.get(c1) != c2 || map.get(c2) != c1) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

}
