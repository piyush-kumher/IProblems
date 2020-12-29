package com.piyush.psds.facebook.dp;

import java.util.Stack;

public class LongestValidParathesis {

    public int longestValidParentheses(String s) {
        Stack<Integer> stk = new Stack<>();
        stk.push(-1);
        int max = 0;
        for(int i=0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                stk.push(i);
            } else {
                stk.pop();
                if(stk.isEmpty()) {
                    stk.push(i);
                } else {
                    max = Math.max(max, i-stk.peek());
                }
            }
        }
        return max;
    }

    public int longestValidParentheses_1(String s) {
        int lc = 0;
        int rc = 0;
        int max = 0;
        for(int i=0; i < s.length(); i++) {
            if(s.charAt(i) == '(') lc++;
            else rc++;
            if(lc == rc) max=Math.max(max, lc*2);
            else if(rc > lc) lc = rc = 0;
        }

        lc = rc = 0;
        for(int i=s.length()-1; i >= 0; i--) {
            if(s.charAt(i) == ')') rc++;
            else lc++;
            if(lc == rc) max=Math.max(max, lc*2);
            else if(lc > rc) lc = rc = 0;
        }
        return max;
    }

}
