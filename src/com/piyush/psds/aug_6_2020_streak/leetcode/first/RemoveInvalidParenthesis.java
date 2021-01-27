package com.piyush.psds.aug_6_2020_streak.leetcode.first;

import java.util.*;

/**
 * https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
 */
public class RemoveInvalidParenthesis {

    public String minRemoveToMakeValid(String s) {
        Set<Integer> indexesToRemove = new HashSet<>();
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                st.push(i);
            } else if (s.charAt(i) == ')') {
                if (st.isEmpty()) {
                    indexesToRemove.add(i);
                } else {
                    st.pop();
                }
            }
        }
        while(!st.isEmpty()) {
            indexesToRemove.add(st.pop());
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!indexesToRemove.contains(i)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    public String minRemoveToMakeValid_1(String s) {
        StringBuilder sb = new StringBuilder();
        Set<Integer> indexesToRemove = new HashSet<>();
        int left = 0;
        int right = 0;
        for(int i=0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '(') {
                left++;
            } else if(c == ')') {
                if(left == right) {
                    indexesToRemove.add(i);
                } else {
                    right++;
                }
            }
        }
        left = 0;
        right = 0;
        for(int i=s.length()-1; i >= 0; i--) {
            char c = s.charAt(i);
            if(c == ')' && !indexesToRemove.contains(i)) {
                right++;
            } else if(c == '(') {
                if(left == right) {
                    indexesToRemove.add(i);
                } else {
                    left++;
                }
            }
        }
        for(int i=0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(!indexesToRemove.contains(i)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

}
