package com.piyush.psds.google.dp;

public class BestTimeToBuySellAStock {

    public int maxProfit(int[] prices) {
        int profit = 0;
        if(prices.length == 0) {
            return profit;
        }
        int min = prices[0];
        for(int i=1; i < prices.length; i++) {
            profit = Math.max(prices[i] - min, profit);
            min = Math.min(prices[i],  min);
        }
        return profit;
    }

}
