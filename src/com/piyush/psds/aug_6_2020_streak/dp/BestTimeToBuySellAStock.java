package com.piyush.psds.aug_6_2020_streak.dp;

public class BestTimeToBuySellAStock {

    public int maxProfit(int[] prices) {
        if(prices == null || prices.length < 1) {
            return 0;
        }
        int profit = 0;
        int minTilNow = prices[0];
        for(int i=1; i < prices.length; i++) {
            if(prices[i] > minTilNow && prices[i] - minTilNow > profit) {
                profit = prices[i] - minTilNow;
            }
            minTilNow = Math.min(prices[i], minTilNow);
        }
        return profit;
    }

}
