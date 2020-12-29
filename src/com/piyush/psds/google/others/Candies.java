package com.piyush.psds.google.others;

import java.util.Arrays;

public class Candies {

    public static void main(String[] args) {
        Candies c = new Candies();
        int[] arr = {1,3,4,5,2};
        System.out.println(c.candy(arr));
    }

    public int candy(int[] ratings) {
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);
        for(int i=1; i < ratings.length; i++) {
            if(ratings[i] > ratings[i-1]) {
                candies[i] = candies[i-1] + 1;
            }
        }
        for(int j=ratings.length-2; j >= 0; j--) {
            if(ratings[j] > ratings[j+1] && candies[j] <= candies[j+1]) {
                candies[j] = candies[j+1] + 1;
            }
        }
        int minCandies = 0;
        for(int i=0; i < candies.length; i++) {
            minCandies += candies[i];
        }
        return minCandies;
    }

}
