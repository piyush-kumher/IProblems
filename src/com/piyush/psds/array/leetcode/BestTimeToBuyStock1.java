package com.piyush.psds.array.leetcode;

public class BestTimeToBuyStock1 {

    public int maxProfit(int[] arr) {
        int arrSize = arr.length;
        int maxDiff = 0;
        if (arr.length > 0) {
            int minNum = arr[0];
            for (int i = 1; i < arrSize; i++) {
                if (arr[i] - minNum > maxDiff) {
                    maxDiff = arr[i] - minNum;
                }
                if (minNum > arr[i]) {
                    minNum = arr[i];
                }
            }
        }
        return maxDiff;
    }

}
