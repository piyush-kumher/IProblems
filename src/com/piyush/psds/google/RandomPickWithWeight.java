package com.piyush.psds.google;

import java.util.Arrays;
import java.util.Random;

// https://leetcode.com/problems/random-pick-with-weight
public class RandomPickWithWeight {

    int[] prefixes;
    Random random;

    public RandomPickWithWeight(int[] w) {
        prefixes = new int[w.length];
        for(int i=0; i < w.length; i++) {
            prefixes[i] = (i==0 ? 0 : prefixes[i-1]) + w[i];
        }
        random = new Random();
    }

    public int pickIndex() {
        int randNum = random.nextInt(prefixes[prefixes.length-1]) + 1;
        int idx = Arrays.binarySearch(prefixes, randNum);
        if(idx < 0) {
            return Math.abs(idx) - 1;
        }
        return idx;
    }

}
