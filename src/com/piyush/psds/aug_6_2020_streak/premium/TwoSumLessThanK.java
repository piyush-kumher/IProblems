package com.piyush.psds.aug_6_2020_streak.premium;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/two-sum-less-than-k/submissions/
 */
public class TwoSumLessThanK {

    public int twoSumLessThanK(int[] A, int K) {
        Arrays.sort(A);
        int max = -1;
        int i = 0;
        int j = A.length - 1;
        while(i < j) {
            if(A[i] + A[j] < K) {
                max = Math.max(max, A[i] + A[j]);
                i++;
            } else {
                j--;
            }
        }
        return max;
    }

}
