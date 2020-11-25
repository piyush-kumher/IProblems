package com.piyush.psds.aug_6_2020_streak.leetcode.random;

public class SearchIn2DMatrix2 {

    public static void main(String[] args) {
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length == 0) {
            return false;
        }
        int rows = matrix.length;
        int columns = matrix[0].length;
        int i = 0;
        int j = columns-1;
        while(i < rows && j >= 0) {
            if(matrix[i][j] == target) {
                return true;
            } else if(matrix[i][j] > target) {
                j--;
            } else if(matrix[i][j] < target) {
                i++;
            }
        }
        return false;
    }


}
