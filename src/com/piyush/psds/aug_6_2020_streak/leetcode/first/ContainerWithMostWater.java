package com.piyush.psds.aug_6_2020_streak.leetcode.first;

/**
 * https://leetcode.com/problems/container-with-most-water
 */
public class ContainerWithMostWater {

    public static void main(String[] args) {
        ContainerWithMostWater w = new ContainerWithMostWater();
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(w.maxArea(height));
    }

    public int maxArea(int[] height) {
        int start = 0;
        int end = height.length - 1;
        int max = 0;
        while (start < end) {
            int cal = Math.min(height[start], height[end]) * (end - start);
            if (cal > max) {
                max = cal;
            }
            if (height[start] < height[end]) {
                start++;
            } else {
                end--;
            }
        }
        return max;
    }

}
