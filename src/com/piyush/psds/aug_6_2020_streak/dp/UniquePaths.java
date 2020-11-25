package com.piyush.psds.aug_6_2020_streak.dp;

import java.util.Arrays;

//https://leetcode.com/problems/unique-paths/submissions/
public class UniquePaths {

    public static void main(String[] args) {
        UniquePaths u = new UniquePaths();
        System.out.println(u.uniquePaths(3, 3));
    }

    public int uniquePaths_1(int m, int n) {
        if (m <= 0 || n <= 0) {
            return 0;
        }
        int[][] mat = new int[m][n];
        for (int i = 0; i < m; i++) {
            mat[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            mat[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                mat[i][j] = mat[i - 1][j] + mat[i][j - 1];
            }
        }
        return mat[m - 1][n - 1];
    }

    public int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) {
            return 0;
        }
        int[] mat = new int[n];
        Arrays.fill(mat, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                mat[j] = mat[j - 1] + mat[j];
            }
        }
        return mat[n - 1];
    }

}
