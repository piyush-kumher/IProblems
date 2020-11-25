package com.piyush.psds.uber;

import java.util.Arrays;

public class RussianDollEnvelope {

    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (e1, e2) -> {
            if(e1[0] == e2[0]) {
                return e2[1] - e1[1];
            } else {
                return e1[0] - e2[0];
            }
        });
        int[] arr = new int[envelopes.length];
        for(int i=0; i < envelopes.length; i++) {
            arr[i] = envelopes[i][1];
        }
        return lis2(arr);
    }

    // n*n : dp LongestIncreasingSubsequence
    private int lis1(int[] arr) {
        int[] dp = new int[arr.length];
        Arrays.fill(dp, 1);
        for(int i=1; i < arr.length; i++) {
            for(int j=0; j < i; j++) {
                if(arr[i] > arr[j] && dp[i] <= dp[j]) {
                    dp[i] = dp[j]+1;
                }
            }
        }
        int max = 0;
        for(int i=0; i < dp.length; i++) {
            if(max < dp[i]) {
                max = dp[i];
            }
        }
        return max;
    }

    // n*logn :  LongestIncreasingSubsequence
    private int lis2(int[] arr) {
        if(arr.length == 0) {
            return 0;
        }
        int[] dp = new int[arr.length];
        int lastIndex = 0;
        dp[0] = arr[0];
        for(int i=1; i < arr.length; i++) {
            if(arr[i] > dp[lastIndex]) {
                dp[++lastIndex] = arr[i];
            } else {
                int indexToBeReplaced = binarySearch(dp, 0, lastIndex, arr[i]);
                dp[indexToBeReplaced] = arr[i];
            }
        }
        return lastIndex+1;
    }

    private int binarySearch(int[] dp, int i, int j, int num) {
        while(i <= j) {
            int mid = i + (j-i) / 2;
            if(dp[mid] == num) {
                return mid;
            } else if(dp[mid] < num) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        return i;
    }

}
