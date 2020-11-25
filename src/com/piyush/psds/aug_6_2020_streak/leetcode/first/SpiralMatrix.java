package com.piyush.psds.aug_6_2020_streak.leetcode.first;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

    public static void main(String[] args) {
        //int[][] m = {{1, 2, 3, 4, 51}, {5, 6, 7, 8, 52}, {9, 10, 11, 12, 53}, {13, 14, 15, 16, 54}};
        //int[][] m = {{1},{2},{3},{4},{5},{6},{7},{8},{9},{10}};
        int[][] m = {{1,11},{2,12},{3,13},{4,14},{5,15},{6,16},{7,17},{8,18},{9,19},{10,20}};
        SpiralMatrix sm = new SpiralMatrix();
        System.out.println(sm.spiralOrder(m));
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return list;
        }
        int row = matrix.length;
        int column = matrix[0].length;
        for (int i = 0; i < (row + 1) / 2; i++) {
            for (int j = i; j < column - i; j++) {
                list.add(matrix[i][j]);
            }
            for (int j = i + 1; j < (row - i) && (column - i - 1) >= 0; j++) {
                list.add(matrix[j][column - i - 1]);
            }
            for (int j = column - 2 - i; (j >= i) && (row - i - 1 > i); j--) {
                list.add(matrix[row - i - 1][j]);
            }
            for (int j = row - 2 - i; (j > i) && (i < column - i - 1); j--) {
                list.add(matrix[j][i]);
            }
        }
        return list;
    }

    public List<Integer> spiralOrder_1(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if (matrix.length == 0)
            return ans;
        int r1 = 0;
        int r2 = matrix.length - 1;
        int c1 = 0;
        int c2 = matrix[0].length - 1;
        while (r1 <= r2 && c1 <= c2) {
            for (int c = c1; c <= c2; c++) ans.add(matrix[r1][c]);
            for (int r = r1 + 1; r <= r2; r++) ans.add(matrix[r][c2]);
            if (r1 < r2 && c1 < c2) {
                for (int c = c2 - 1; c > c1; c--) ans.add(matrix[r2][c]);
                for (int r = r2; r > r1; r--) ans.add(matrix[r][c1]);
            }
            r1++;
            r2--;
            c1++;
            c2--;
        }
        return ans;
    }
}
