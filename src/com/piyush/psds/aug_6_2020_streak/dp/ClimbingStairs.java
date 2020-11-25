package com.piyush.psds.aug_6_2020_streak.dp;

// https://leetcode.com/problems/climbing-stairs/submissions/
public class ClimbingStairs {

    public int minCostClimbingStairs(int[] cost) {
        if(cost.length == 1){
            return 0;
        }
        int cost1 = 0;
        int cost2 = cost[0];
        for(int i=1; i < cost.length; i++) {
            int temp = Math.min(cost1, cost2) + cost[i];
            cost1 = cost2;
            cost2 = temp;
        }
        return Math.min(cost1, cost2);
    }

}
