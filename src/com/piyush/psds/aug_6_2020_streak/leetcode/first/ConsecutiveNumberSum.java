package com.piyush.psds.aug_6_2020_streak.leetcode.first;

public class ConsecutiveNumberSum {

    public static void main(String[] args) {
        ConsecutiveNumberSum c = new ConsecutiveNumberSum();
        System.out.println(c.consecutiveNumbersSum(9));
    }

    public int consecutiveNumbersSum(int N) {
        int j = 1;
        int i = 1;
        int sum = 0;
        int ans = 0;
        while (i <= N) {
            if (sum < N) {
                sum += i;
                i++;
            }
            if (sum > N) {
                sum = sum - j;
                j++;
            }
            if (sum == N) {
                ans++;
                if(j != N) {
                    sum = sum - j;
                    j++;
                } else {
                    break;
                }
            }
        }
        return ans;
    }
}
