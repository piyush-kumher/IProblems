package com.piyush.psds.google.design;

import java.util.Stack;

public class MinStack {

    public static void main(String[] args) {
        MinStack m = new MinStack();
        m.push(-2);
        m.push(0);
        m.push(-3);
        System.out.println(m.getMin());
        m.pop();
        System.out.println(m.top());
        System.out.println(m.getMin());
    }

    Stack<Integer> stk;
    Stack<Integer> minStk;

    /** initialize your data structure here. */
    public MinStack() {
        stk = new Stack<>();
        minStk = new Stack<>();
    }

    public void push(int x) {
        stk.push(x);
        int min = x;
        if(!minStk.isEmpty()) {
            min = Math.min(x, minStk.peek());
        }
        minStk.push(min);
    }

    public void pop() {
        minStk.pop();
        stk.pop();
    }

    public int top() {
        return stk.peek();
    }

    public int getMin() {
        return minStk.peek();
    }
}


class MinStack1 {

    Stack<Integer> stk;
    Stack<Integer> minStk;

    /** initialize your data structure here. */
    public MinStack1() {
        stk = new Stack<>();
        minStk = new Stack<>();
    }

    public void push(int x) {
        stk.push(x);
        if(minStk.isEmpty() || x <= minStk.peek()) {
            minStk.push(x);
        }
    }

    public void pop() {
        if(minStk.peek().equals(stk.peek())) {
            minStk.pop();
        }
        stk.pop();
    }

    public int top() {
        return stk.peek();
    }

    public int getMin() {
        return minStk.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */