package com.piyush.psds.aug_6_2020_streak.leetcode.random;

import java.util.PriorityQueue;

public class MinimumCostToConnectSticks {

    public int connectSticks(int[] sticks) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for(int stick : sticks) {
            q.offer(stick);
        }
        int totalCost = 0;
        while(!q.isEmpty()) {
            int a = q.remove();
            if(!q.isEmpty()) {
                int b = q.remove();
                int cost = (a+b);
                totalCost += cost;
                q.offer(cost);
            }
        }
        return totalCost;
    }

}
