package com.piyush.psds.array.leetcode;

public class BestTimeToBuyStock2 {

    public static void main(String[] args) {
        BestTimeToBuyStock2 s = new BestTimeToBuyStock2();
        int[] arr = {7, 1, 5, 3, 6, 4};
        System.out.println(s.maxProfit(arr));
    }

    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int size = prices.length;
        if (size >= 2) {
            int i = 0;
            while (i < size - 1) {
                while (i + 1 < size && prices[i] >= prices[i + 1]) {
                    i++;
                }
                int j = i;
                while (i + 1 < size && prices[i] < prices[i + 1]) {
                    i++;
                }
                if (i > j) {
                    maxProfit += prices[i] - prices[j];
                }
                i++;
            }
        }
        return maxProfit;
    }

}
