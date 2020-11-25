package com.piyush.psds.aug_6_2020_streak.dp;

import java.util.HashMap;
import java.util.Map;

public class PerfectSquares {

    public static void main(String[] args) {
        PerfectSquares ps = new PerfectSquares();
        System.out.println(ps.numSquares(12));
    }

    // Legendre's theorem: According to this theorem, any natual number can be represented with the sum of square sum of square of 4 integers. i.e. N = a*a + b*b + c*c + d*d
    // According to this theoren, a number can also be represented by square of 3 numbers N = a*a+ b*b * c*c, if and only if, N is not of the form pow(4, a) * (8*b+7).
    // Hence according to this theorem, our answer can be max 4.
    // Steps:
    //step 1: if number if perfect square -> answer is 1
    //step 2: number can be represented by pow(4, a) * (8*b+7), answer is 4
    //step 3: check for 2
    // step 4: return 3
    public int numSquares(int n) {
        if(Math.ceil(Math.sqrt(n)) == Math.floor(Math.sqrt(n))) {
            return 1;
        }
        int temp = n;
        while(temp%4 == 0) {
            temp /= 4;
        }
        if(temp % 8 == 7) {
            return 4;
        }
        for(int i=0; i*i <= n; i++) {
            int base = (int) Math.sqrt(n-i*i);
            if(base*base == n-i*i) {
                return 2;
            }
        }
        return 3;
    }


    public int numSquares_dp(int n) {
        int maxNum = (int) Math.sqrt(n);
        int[] squareNums = new int[maxNum];
        for(int i=0; i < maxNum; i++) {
            squareNums[i] = (i+1) * (i+1);
        }
        return checkMinNumDP(squareNums, n);
    }

    private int checkMinNumDP(int[] squareNums, int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for(int i=1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            for(int square : squareNums) {
                if(square <= i) {
                    dp[i] = Math.min(dp[i-square] + 1, dp[i]);
                }
            }
        }
        return dp[n];
    }


    public int numSquares_recWithMem(int n) {
        int maxNum = (int) Math.sqrt(n);
        int[] squareNums = new int[maxNum];
        for(int i=0; i < maxNum; i++) {
            squareNums[i] = (i+1) * (i+1);
        }
        Map<Integer, Integer> mem = new HashMap<>();
        return checkMinNumRECWITHMEM(squareNums, n, mem);
    }

    private int checkMinNumRECWITHMEM(int[] squareNums, int rem, Map<Integer, Integer> mem) {
        if(rem <= 3) {
            return rem;
        }
        int minValue = Integer.MAX_VALUE;
        for(int square : squareNums) {
            if(rem >= square) {
                if(mem.containsKey(rem - square)) {
                    minValue = Math.min(minValue, 1 + mem.get(rem-square));
                } else {
                    minValue = Math.min(minValue, 1 + checkMinNumRECWITHMEM(squareNums, rem - square, mem));
                }
            }
        }
        mem.put(rem, minValue);
        return minValue;
    }

    // time limit exceeds ..
    public int numSquares_rec(int n) {
        int maxNum = (int) Math.sqrt(n);
        int[] squareNums = new int[maxNum];
        for(int i=0; i < maxNum; i++) {
            squareNums[i] = (i+1) * (i+1);
        }
        return checkMinNumREC(squareNums, n);
    }

    private int checkMinNumREC(int[] squareNums, int rem) {
        if(rem <= 3) {
            return rem;
        }
        int minValue = Integer.MAX_VALUE;
        for(int square : squareNums) {
            if(rem >= square) {
                minValue = Math.min(minValue, 1 + checkMinNumREC(squareNums, rem - square));
            }
        }
        return minValue;
    }

}
