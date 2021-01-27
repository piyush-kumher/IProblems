package com.piyush.psds.google.others;

import java.util.Stack;

public class ValidateStackSequence {

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int j = 0;
        int n = pushed.length;
        Stack<Integer> stk = new Stack<>();
        for(int x : pushed) {
            stk.push(x);
            while(!stk.isEmpty() && j < n && stk.peek() == popped[j]) {
                stk.pop();
                j++;
            }
        }
        return j == n;
    }

}
