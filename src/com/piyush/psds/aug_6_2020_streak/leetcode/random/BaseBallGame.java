package com.piyush.psds.aug_6_2020_streak.leetcode.random;

public class BaseBallGame {

    public int calPoints(String[] ops) {
        if(ops.length == 0) {
            return 0;
        }
        int[] lastValidRound = new int[ops.length];
        int lastValid = 0;
        lastValidRound[0] = -1;
        int sum = Integer.parseInt(ops[0]);
        for(int i=1; i < ops.length; i++) {
            if(ops[i].equals("C")) {
                sum -= Integer.parseInt(ops[lastValid]);
                lastValid = lastValidRound[lastValid];
            } else if(ops[i].equals("D")) {
                int val = 2 * Integer.parseInt(ops[lastValid]);
                sum += val;
                ops[i] = Integer.toString(val);
                lastValidRound[i] = lastValid;
                lastValid = i;
            } else if(ops[i].equals("+")) {
                int val = Integer.parseInt(ops[lastValid]) + Integer.parseInt(ops[lastValidRound[lastValid]]);
                sum += val;
                ops[i] = Integer.toString(val);
                lastValidRound[i] = lastValid;
                lastValid = i;
            } else {
                sum += Integer.parseInt(ops[i]);
                lastValidRound[i] = lastValid;
                lastValid = i;
            }
        }
        return sum;
    }

}
