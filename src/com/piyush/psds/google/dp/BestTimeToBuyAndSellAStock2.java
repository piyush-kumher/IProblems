package com.piyush.psds.google.dp;

public class BestTimeToBuyAndSellAStock2 {

    public int maxProfit(int[] prices) {
        int profit = 0;
        if(prices.length == 0) {
            return profit;
        }
        int min = prices[0];
        for(int i=1; i < prices.length; i++) {
            if(prices[i] - min > 0) {
                profit += prices[i] - min;
            }
            min = prices[i];
        }
        return profit;
    }

}
