package com.piyush.psds.aug_6_2020_streak.leetcode.first;

import java.util.Stack;

public class LargestRectangleInHistogram {

    //BRUTE_FORCE - > LEFT POINT, RIGHT POINTER, MIN IN BETWEEN, THAT'S IT
    public int largestRectangleArea_1(int[] heights) {
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = i; j < heights.length; j++) {
                min = Math.min(min, heights[j]);
                maxArea = Math.max((j - i + 1) * min, maxArea);
            }
        }
        return maxArea;
    }


    //BASIC DIVIDE AND CONQUER
    public int largestRectangleArea_2(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        return dac(heights, 0, heights.length - 1);
    }

    private int dac(int[] heights, int start, int end) {
        if (start > end) {
            return 0;
        }
        int minIndex = start;
        // Find minimum index
        for (int i = start; i <= end; i++) {
            if (heights[minIndex] > heights[i]) {
                minIndex = i;
            }
        }
        // from (start to end, the minimum element limits)
        int max1 = (end - start + 1) * heights[minIndex];
        // call for left half
        int max2 = dac(heights, start, minIndex - 1);
        // call for right half
        int max3 = dac(heights, minIndex + 1, end);
        return Math.max(Math.max(max1, max2), max3);
    }

    public int largestRectangleArea(int[] heights) {
        Stack<Integer> s = new Stack<>();
        s.push(-1);
        int maxArea = 0;
        // keep pushing into stack until you get a lesser element than the previous.
        // If you get lesser element, then you pop, and calculate the max area that popped out element can give.
        for (int i = 0; i < heights.length; i++) {
            while (s.peek() != -1 && heights[s.peek()] >= heights[i]) {
                maxArea = Math.max(maxArea, heights[s.pop()] * (i - s.peek() - 1));
            }
            s.push(i);
        }
        while (s.peek() != -1) {
            maxArea = Math.max(maxArea, heights[s.pop()] * (heights.length - s.peek() - 1));
        }
        return maxArea;
    }


}
