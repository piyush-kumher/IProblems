package com.piyush.psds.google.array_string;

import java.util.Arrays;

// https://leetcode.com/problems/candy/submissions/
public class Candies {

    // 2 array
    public int candy_two_array(int[] ratings) {
        int n = ratings.length;
        int[] left2Right = new int[n];
        int[] right2Left = new int[n];
        Arrays.fill(left2Right, 1);
        Arrays.fill(right2Left, 1);
        for(int i=1; i < n; i++) {
            if(ratings[i-1] < ratings[i]) {
                left2Right[i] = left2Right[i-1] + 1;
            }
        }
        for(int i=n-2; i>=0; i--) {
            if(ratings[i] > ratings[i+1]) {
                right2Left[i] = right2Left[i+1] + 1;
            }
        }
        int sum = 0;
        for(int i=0; i < n; i++) {
            sum += Math.max(left2Right[i], right2Left[i]);
        }
        return sum;
    }

    // 1 array
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];
        Arrays.fill(candies, 1);
        for(int i=1; i < n; i++) {
            if(ratings[i-1] < ratings[i]) {
                candies[i] = candies[i-1] + 1;
            }
        }
        for(int i=n-2; i >= 0; i--) {
            if(ratings[i] > ratings[i+1] && candies[i] <= candies[i+1]) {
                candies[i] = candies[i+1] + 1;
            }
        }
        int sum = 0;
        for(int i=0; i < n; i++) {
            sum += candies[i];
        }
        return sum;
    }

}
