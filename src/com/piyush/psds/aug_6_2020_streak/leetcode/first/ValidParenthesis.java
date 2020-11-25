package com.piyush.psds.aug_6_2020_streak.leetcode.first;

import java.util.*;

public class ValidParenthesis {

    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        Map<Character, Character> closeToOpenMap = new HashMap<>();
        closeToOpenMap.put(')', '(');
        closeToOpenMap.put('}', '{');
        closeToOpenMap.put(']', '[');
        for (char c : s.toCharArray()) {
            if (c == ')' || c == '}' || c == ']') {
                if (st.isEmpty() || !closeToOpenMap.get(c).equals(st.pop())) {
                    return false;
                }
            } else {
                st.push(c);
            }
        }
        if(!st.isEmpty()) {
            return false;
        }
        return true;
    }

}
