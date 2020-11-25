package com.piyush.psds.aug_6_2020_streak.leetcode.first;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/maximal-rectangle/solution/
 */
public class MaximumRectangle {

    LargestRectangleInHistogram largestRectangleInHistogram = new LargestRectangleInHistogram();

    public static void main(String[] args) {
        char[][] arr = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        MaximumRectangle m = new MaximumRectangle();
        m.maximalRectangle(arr);
    }

    /**
     * DP: for each DP scan column
     * {1, 0, 1, 0, 0},
     * {1, 0, 1, 2, 3},
     * {1, 2, 3, 4, 5},
     * {1, 0, 0, 1, 0}
     */
    public int maximalRectangle_1(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int[][] dp = new int[matrix.length][matrix[0].length];
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = j == 0 ? 1 : dp[i][j - 1] + 1;
                    int width = dp[i][j];
                    for (int k = i; k >= 0; k--) {
                        width = Math.min(width, dp[k][j]);
                        max = Math.max(max, width * (i - k + 1));
                    }
                }
            }
        }
        return max;
    }

    /**
     * DP: for each DP scan column
     * {1, 0, 1, 0, 0},
     * {1, 0, 1, 2, 3},
     * {1, 2, 3, 4, 5},
     * {1, 0, 0, 1, 0}
     */
    public int maximalRectangle_2(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int maxarea = 0;
        int[] dp = new int[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                dp[j] = matrix[i][j] == '1' ? dp[j] + 1 : 0;
            }
            maxarea = Math.max(maxarea, largestRectangleInHistogram.largestRectangleArea(dp));
        }
        return maxarea;
    }


    public int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;

        /* height counts the number of successive '1's above (plus the current one).
           The value of left & right means the boundaries of the rectangle which contains the current point with a
           height of value height
         */
        int[] height = new int[n];
        int[] left = new int[n];
        int[] right = new int[n];

        Arrays.fill(right, n);

        int maxArea = 0;

        for(int i = 0; i < m; i++) {
            int curLeft = 0, curRight = n;

            //compute height
            for(int j = 0; j < n; j++) {
                if(matrix[i][j] == '0') {
                    height[j] = 0;
                } else {
                    height[j]++;
                }
            }

            //compute left boundary
            for(int j = 0; j < n; j++) {
                if(matrix[i][j] == '1') {
                    left[j] = Math.max(left[j], curLeft);
                } else {
                    left[j] = 0;
                    curLeft = j + 1;
                }
            }

            //compute right boundary
            for(int j = n - 1; j >= 0; j--) {
                if(matrix[i][j] == '1') {
                    right[j] = Math.min(right[j], curRight);
                } else {
                    right[j] = n;
                    curRight = j;
                }
            }

            /* Let the maximal rectangle area at row i and column j be computed by [right(i,j) - left(i,j)]*height(i,j).
               Actually, that single expression cannot guarantee it is the largest maximal rectangle containing (i, j)
               and up to row i. It is actually, the tallest rectangle containing (i, j) and up to row i while making as
               wide as possible. And the key point is that the global maximal rectangle must be a such tallest rectangle
               somewhere at (i, j)
             */
            for(int j = 0; j < n; j++) {
                maxArea = Math.max(height[j] * (right[j] - left[j]), maxArea);
            }
        }
        return maxArea;
    }

}
