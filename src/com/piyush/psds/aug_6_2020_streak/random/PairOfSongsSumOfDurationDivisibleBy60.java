package com.piyush.psds.aug_6_2020_streak.random;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/submissions/
public class PairOfSongsSumOfDurationDivisibleBy60 {

    // (a + b) % 60 == 0. --> ((a % 60) + (b % 60)) % 60 == 0
    public int numPairsDivisibleBy60_1(int[] time) {
        Map<Integer, Integer> mem = new HashMap<>();
        int ans = 0;
        for(int t : time) {
            if(t % 60 == 0) {
                ans += mem.getOrDefault(0, 0);
            } else {
                ans += mem.getOrDefault(60 - (t%60), 0);
            }
            mem.put(t%60, mem.getOrDefault(t%60, 0) + 1);
        }
        return ans;
    }

    // (a + b) % 60 == 0. --> ((a % 60) + (b % 60)) % 60 == 0
    public int numPairsDivisibleBy60(int[] time) {
        int[] mem = new int[60];
        int ans = 0;
        for(int t : time) {
            if(t % 60 == 0) {
                ans += mem[0];
            } else {
                ans += mem[60 - (t%60)];
            }
            mem[t%60]++;
        }
        return ans;
    }

}
