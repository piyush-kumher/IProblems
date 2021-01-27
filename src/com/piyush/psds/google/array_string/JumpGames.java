package com.piyush.psds.google.array_string;

public class JumpGames {

    public boolean canJump(int[] nums) {
        int n = nums.length;
        boolean possible = true;
        int maxJump = nums[0];
        for (int i = 1; i < n; i++) {
            if (i <= maxJump) {
                possible = true;
                maxJump = Math.max(maxJump, i + nums[i]);
            } else {
                possible = false;
            }
        }
        return possible;
    }

    public boolean canJump1(int[] nums) {
        int n = nums.length;
        boolean[] dp = new boolean[n];
        int maxJump = nums[0];
        dp[0] = true;
        for (int i = 1; i < n; i++) {
            if (i <= maxJump) {
                dp[i] = true;
                maxJump = Math.max(maxJump, i + nums[i]);
            }
        }
        return dp[n - 1];
    }

}
