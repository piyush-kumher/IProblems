package com.piyush.psds.uber;

import java.util.List;
import java.util.Stack;

public class ExecutionTimeOfFunctions {

    public int[] exclusiveTime(int n, List<String> logs) {
        int[] result = new int[n];
        Stack<Integer> stk = new Stack<>();
        String[] s = logs.get(0).split(":");
        stk.push(Integer.parseInt(s[0]));
        int prev = Integer.parseInt(s[2]);
        for(int i=1; i < logs.size(); i++) {
            String[] details = logs.get(i).split(":");
            int jobId = Integer.parseInt(details[0]);
            int timestamp = Integer.parseInt(details[2]);
            if("start".equals(details[1])) {
                if(!stk.isEmpty()) {
                    result[stk.peek()] += timestamp - prev;
                }
                stk.push(jobId);
                prev = timestamp;
            } else {
                result[stk.peek()] += timestamp - prev + 1;
                stk.pop();
                prev = timestamp + 1;
            }
        }
        return result;
    }

}
