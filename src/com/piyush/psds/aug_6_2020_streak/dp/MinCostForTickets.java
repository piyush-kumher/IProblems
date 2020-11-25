package com.piyush.psds.aug_6_2020_streak.dp;

public class MinCostForTickets {

    public static void main(String[] args) {
        int[] days = {1, 4, 6, 7, 8, 20};
        int[] costs = {2, 7, 15};
        MinCostForTickets m = new MinCostForTickets();
        System.out.println(m.mincostTickets(days, costs));
    }

    public int mincostTickets(int[] days, int[] costs) {
        int[] dp = new int[days[days.length - 1]];
        for (int i = 0; i < days.length; i++) {
            int oneDayFair = days[i] > 1 ? dp[days[i] - 2] + costs[0] : costs[0];
            int seventhDayFair = days[i] <= 7 ? costs[1] : dp[days[i] - 8] + costs[1];
            int thirtyDayFair = days[i] <= 30 ? costs[2] : dp[days[i] - 31] + costs[2];
            dp[days[i]-1] = Math.min(oneDayFair, Math.min(seventhDayFair, thirtyDayFair));
            int j = days[i];
            while (i < days.length - 1 && j < dp.length && j < days[i + 1]-1) {
                dp[j++] = dp[days[i]-1];
            }
        }
        return dp[dp.length-1];
    }

}
