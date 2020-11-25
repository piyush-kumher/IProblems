package com.piyush.psds.aug_6_2020_streak.dp;


// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/submissions/
public class BestTimeToBukAndSell2 {


    public int maxProfit(int[] prices) {
        if(prices == null || prices.length < 1) {
            return 0;
        }
        int profit = 0;
        int minTilNow = prices[0];
        for(int i=0; i < prices.length; i++) {
            if(prices[i] - minTilNow > 0) {
                profit += prices[i] - minTilNow;
                minTilNow = prices[i];
            }
            minTilNow = Math.min(minTilNow, prices[i]);
        }
        return profit;
    }

}
