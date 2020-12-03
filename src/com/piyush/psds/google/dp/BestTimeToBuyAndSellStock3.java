package com.piyush.psds.google.dp;

public class BestTimeToBuyAndSellStock3 {

    // First approach with 2 array, easy to understand
    public int maxProfit_1(int[] prices) {
        int[] l = new int[prices.length];
        int[] r = new int[prices.length];
        if(prices.length == 0) {
            return 0;
        }
        int min = prices[0];
        for(int i=1; i < prices.length; i++) {
            l[i] = Math.max(l[i-1], prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        int max = prices[prices.length-1];
        for(int i = prices.length-2; i >=0; i--) {
            r[i] = Math.max(r[i+1], max - prices[i]);
            max = Math.max(prices[i], max);
        }
        int profit = 0;
        for(int i=0; i < prices.length; i++) {
            profit = Math.max(l[i] + r[i], profit);
        }
        return profit;
    }

    // With one pass, without any extra space
    public int maxProfit(int[] prices) {
        int t1Cost = Integer.MAX_VALUE;
        int t2Cost = Integer.MAX_VALUE;
        int t1Profit = 0;
        int profit = 0;
        for(int i=0; i < prices.length; i++) {
            // cost of txn t1
            t1Cost = Math.min(t1Cost, prices[i]);
            // txn1 profit ..
            t1Profit = Math.max(t1Profit, prices[i] - t1Cost);
            // cost of txn t2, considering the profit of txn1, the cost should be min .. (We have extra money considering profit of t1, check how much the next txn buy cost us).. A lower value would always be considered..
            t2Cost = Math.min(t2Cost, prices[i] - t1Profit);
            // know that, t1Profit might have increased but we are considering the profit which gave us minimum reinvestment .. (i.e. t2Cost) ..
            profit = Math.max(profit, prices[i] - t2Cost);
        }
        return profit;
    }

}
