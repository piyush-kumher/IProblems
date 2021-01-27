package com.piyush.psds.facebook.random;

import java.util.*;

public class RemoveInvalidParanthesis {

    public List<String> removeInvalidParentheses(String s) {
        Stack<Character> stk = new Stack<>();
        int i = 0;
        int alb = 0;
        int arb = 0;
        for(char c : s.toCharArray()) {
            if(c == '(') {
                alb++;
            } else if(c == ')') {
                arb = (alb == 0) ? (arb+1) : arb;
                alb = (alb == 0) ? alb : (alb-1);
            }
        }
        StringBuilder sb = new StringBuilder();
        Set<String> set = new HashSet<>();
        this.recurse(s, 0, 0, 0, alb, arb, sb, set);
        return new ArrayList<>(set);
    }

    private void recurse(String s, int i, int lb, int rb, int alb, int arb, StringBuilder sb, Set<String> res) {
        if(i == s.length()) {
            if(alb == 0 && arb == 0) {
                res.add(sb.toString());
            }
        } else {
            int length = sb.length();
            char c = s.charAt(i);
            if((c == '(' && alb > 0) || (c == ')' && arb > 0)) {
                recurse(s, i+1, lb, rb, (c == '(' ? (alb-1) : alb), (c == ')' ? (arb-1) : arb), sb, res);
            }
            sb.append(c);
            if(c != '(' && c != ')') {
                recurse(s, i+1, lb, rb, alb, arb, sb, res);
            } else if(c == '(') {
                recurse(s, i+1, lb+1, rb, alb, arb, sb, res);
            } else if(rb < lb) {
                recurse(s, i+1, lb, rb+1, alb, arb, sb, res);
            }
            sb.deleteCharAt(length);
        }
    }

}
