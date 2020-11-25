package com.piyush.psds.aug_6_2020_streak.leetcode.first;

public class CoinChange {

    //Top Down
    public int coinChange_1(int[] coins, int amount) {
        if (amount < 1) {
            return 0;
        }
        return doIt(coins, amount, new int[amount]);
    }

    private int doIt(int[] coins, int rem, int[] count) {
        if (rem < 0) {
            return -1;
        } else if (rem == 0) {
            return 0;
        }
        if (count[rem - 1] != 0) {
            return count[rem - 1];
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int res = doIt(coins, rem - coins[i], count);
            if (res >= 0 && min > res) {
                min = 1 + res;
            }
        }
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }

    //Bottoms up Down
    public int coinChange(int[] coins, int amount) {
        if (amount < 1) {
            return 0;
        }
        int[] dp = new int[amount+1];
        for(int i=1; i < dp.length; i++) {
            int min = Integer.MAX_VALUE;
            for(int j=0; j < coins.length; j++) {
                int diff = i - coins[j];
                if(diff >= 0 && dp[diff] != -1 && dp[diff] + 1 < min) {
                    min = dp[diff] + 1;
                }
                if(min != Integer.MAX_VALUE) {
                    dp[i] = min;
                } else {
                    dp[i] = -1;
                }
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = {2};
        CoinChange cc = new CoinChange();
        System.out.println(cc.coinChange(coins, 3));
    }

}
