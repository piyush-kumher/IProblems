package com.piyush.psds.aug_6_2020_streak.dp;

//https://leetcode.com/problems/coin-change/submissions/
public class CoinChange {

    public static void main(String[] args) {
        CoinChange c = new CoinChange();
        int[] arr = {1, 2, 5};
        System.out.println(c.coinChange(arr, 11));
    }

    //b// bottom up
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for(int i = 1; i <= amount; i++) {
            dp[i] = amount + 1;
        }
        for(int i=1; i <= amount; i++) {
            for(int coin : coins) {
                if(coin <= i) {
                    dp[i] = Math.min(dp[i-coin] + 1, dp[i]);
                }
            }
        }
        return dp[amount] >= amount + 1 ? -1 : dp[amount];
    }

    // Coins change problem !!! Time limit exceeds !!
    public int coinChange_2(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return 0;
        }
        return recurse(coins, 0, coins.length - 1, amount, 0);
    }

    public int recurse(int[] coins, int start, int end, int amount, int change) {
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return change;
        }
        if (start > end) {
            return -1;
        }
        int change1 = recurse(coins, start, end, amount - coins[start], change + 1);
        int change2 = recurse(coins, start + 1, end, amount, change);
        return change1 == -1 ? (change2) : (change2 == -1 ? change1 : Math.min(change1, change2));
    }

    // top down approach ..
    public int coinChange_1(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return 0;
        }
        return topdown(coins, amount, new int[amount]);
    }

    public int topdown(int[] coins, int rem, int[] count) {
        if (rem == 0) {
            return 0;
        }
        if (rem < 0) {
            return -1;
        }
        if (count[rem - 1] != 0) {
            return count[rem - 1];
        }
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = topdown(coins, rem - coin, count);
            if (res >= 0 && res < min) {
                min = res + 1;
            }
        }
        count[rem - 1] = min == Integer.MAX_VALUE ? -1 : min;
        return count[rem - 1];
    }


    // bottom up
    public int coinChange_3(int[] coins, int amount) {
        int[][] mem = new int[coins.length + 1][amount + 1];
        for (int i = 1; i <= amount; i++) {
            mem[0][i] = -1;
        }
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                if (coins[i - 1] > j) {
                    mem[i][j] = mem[i - 1][j];
                } else {
                    mem[i][j] = (mem[i - 1][j] == -1 && mem[i][j - coins[i - 1]] == -1) ? -1 :
                            mem[i - 1][j] == -1 ? (mem[i][j - coins[i - 1]] + 1) :
                                    mem[i][j - coins[i - 1]] == -1 ? mem[i - 1][j] : Math.min(mem[i][j - coins[i - 1]] + 1, mem[i - 1][j]);
                }
            }
        }
        return mem[coins.length][amount];
    }

}
