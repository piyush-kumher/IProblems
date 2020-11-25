package com.piyush.psds.google.array_string;

public class ContainerWithMostWater {

    public int maxArea(int[] height) {
        int start = 0;
        int end = height.length - 1;
        int max = 0;
        while (start < end) {
            int cal = Math.min(height[end], height[start]) * (end - start);
            max = Math.max(max, cal);
            if (height[start] < height[end]) {
                start++;
            } else {
                end--;
            }
        }
        return max;
    }

}
