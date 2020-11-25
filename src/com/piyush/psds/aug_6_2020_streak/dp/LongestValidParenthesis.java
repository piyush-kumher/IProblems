package com.piyush.psds.aug_6_2020_streak.dp;

import java.util.Stack;


//https://leetcode.com/problems/longest-valid-parentheses/
public class LongestValidParenthesis {

    // using left right pointer, and left right scan ..
    public int longestValidParentheses(String s) {
        int max = 0;
        int lc = 0;
        int rc = 0;
        for(int i=0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                lc++;
            } else {
                rc++;
            }
            if(lc == rc) {
                max = Math.max(max, lc * 2);
            } else if(rc > lc){
                lc = rc = 0;
            }
        }
        lc = rc = 0;
        for(int i=s.length()-1; i >= 0; i--) {
            if(s.charAt(i) == '(') {
                lc++;
            } else {
                rc++;
            }
            if(lc == rc) {
                max = Math.max(max, lc * 2);
            } else if(lc > rc) {
                lc = rc = 0;
            }
        }
        return max;
    }

    //using  stack : with index getting populated to stack
    public int longestValidParentheses_1(String s) {
        Stack<Integer> stk = new Stack<>();
        int max = 0;
        stk.push(-1);
        for(int i=0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '(') {
                stk.push(i);
            } else {
                stk.pop();
                if(stk.isEmpty()) {
                    stk.push(i);
                } else {
                    max = Math.max(max, i - stk.peek());
                }
            }
        }
        return max;
    }

}
