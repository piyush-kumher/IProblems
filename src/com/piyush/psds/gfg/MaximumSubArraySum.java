package com.piyush.psds.gfg;

public final class MaximumSubArraySum {

    public static int sum(int[] arr) {
        int maxSum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > 0) {
                arr[i] += arr[i - 1];
            }
            if (arr[i] > maxSum) {
                maxSum = arr[i];
            }
        }
        return maxSum;
    }
}
