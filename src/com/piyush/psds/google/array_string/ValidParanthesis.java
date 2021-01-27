package com.piyush.psds.google.array_string;

import java.util.Stack;

public class ValidParanthesis {

    public boolean isValid(String s) {
        Stack<Character> stk = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stk.push(c);
            } else {
                if (stk.isEmpty() || (c == ')' && stk.peek() != '(') || (c == '}' && stk.peek() != '{') || (c == ']' && stk.peek() != '[')) {
                    return false;
                }
                stk.pop();
            }
        }
        return stk.isEmpty();
    }

}
