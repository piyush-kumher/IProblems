package com.piyush.psds.aug_6_2020_streak.leetcode.first;

public class TrappingRainWater {

    public static void main(String[] args) {
        int[] data = {2, 0, 2};
        TrappingRainWater t = new TrappingRainWater();
        System.out.println(t.trap_approach2(data));
    }

    public int trap(int[] height) {
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];
        int result = 0;
        if (height.length > 0) {
            leftMax[0] = height[0];
            rightMax[height.length - 1] = height[height.length - 1];
            for (int i = 1; i < height.length; i++) {
                leftMax[i] = Math.max(leftMax[i - 1], height[i]);
            }
            for (int i = height.length - 2; i >= 0; i--) {
                rightMax[i] = Math.max(rightMax[i + 1], height[i]);
            }
            for (int i = 0; i < height.length; i++) {
                result += Math.min(leftMax[i], rightMax[i]) - height[i];
            }
        }
        return result;
    }

    public int trap_approach2(int[] height) {
        int l = 0, r = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int answer = 0;
        while (l < r) {
            if (height[l] < height[r]) {
                if (height[l] > leftMax) {
                    leftMax = height[l];
                } else {
                    answer += leftMax - height[l];
                }
                l++;
            } else {
                if (height[r] > rightMax) {
                    rightMax = height[r];
                } else {
                    answer += rightMax - height[r];
                }
                r--;
            }
        }
        return answer;
    }


}
