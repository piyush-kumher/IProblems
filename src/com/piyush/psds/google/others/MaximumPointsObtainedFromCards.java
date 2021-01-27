package com.piyush.psds.google.others;

public class MaximumPointsObtainedFromCards {

    public static void main(String[] args) {
        MaximumPointsObtainedFromCards m = new MaximumPointsObtainedFromCards();
        int k = 7;
        int[] cardPoints = {9,7,7,9,7,7,9};
        System.out.println(m.maxScore1(cardPoints, k));
    }

    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int[] sum = new int[n];
        int temp = 0;
        for(int i=0; i < n; i++) {
            temp += cardPoints[i];
            sum[i] = temp;
        }
        if(k == n) {
            return sum[n-1];
        }
        int max = (sum[n-1] - sum[n-k-1]);
        for(int i=1; i < (k+1); i++) {
            max = Math.max(max, temp-sum[i+(n-k)-1]+sum[i-1]);
        }
        return max;
    }

    public int maxScore1(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int len = n-k; /// we need to find a subarray of minimum length n-k
        int sum = 0;
        for(int i=0; i < len; i++) {
            sum += cardPoints[i];
        }
        int min = sum;
        int totalSum = sum;
        for(int i=len; i < n; i++) {
            totalSum += cardPoints[i];
            sum = sum + cardPoints[i] - cardPoints[i-len];
            min = Math.min(min, sum);
        }
        return totalSum - min;
    }

}
