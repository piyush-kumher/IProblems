package com.piyush.psds.facebook.dp;

public class BestTimeToBuyAndSellAStock {

    public int maxProfit(int[] prices) {
        if(prices.length == 0) {
            return 0;
        }
        int maxProfit = 0;
        int min = prices[0];
        for(int i=0; i < prices.length; i++) {
            maxProfit = Math.max(maxProfit, prices[i]-min);
            min = Math.min(min, prices[i]);
        }
        return maxProfit;
    }

}
