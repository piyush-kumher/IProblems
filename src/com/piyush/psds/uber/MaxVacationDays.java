package com.piyush.psds.uber;

import java.util.Arrays;

public class MaxVacationDays {

    public int maxVacationDays(int[][] flights, int[][] days) {
        int[][] dp = new int[flights.length][days[0].length];
        for (int[] mem : dp) {
            Arrays.fill(mem, Integer.MIN_VALUE);
        }
        return doADfs(flights, days, dp, 0, 0);
    }

    private int doADfs(int[][] flights, int[][] days, int[][] dp, int city, int week) {
        if (week == days[0].length) {
            return 0;
        }
        if (dp[city][week] != Integer.MIN_VALUE) {
            return dp[city][week];
        }
        int maxVac = 0;
        for (int i = 0; i < flights.length; i++) {
            if (flights[city][i] == 1 || i == city) {
                int vac = days[i][week] + doADfs(flights, days, dp, i, week + 1);
                maxVac = Math.max(maxVac, vac);
            }
        }
        dp[city][week] = maxVac;
        return maxVac;
    }

}
