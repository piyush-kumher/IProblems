package com.piyush.psds.aug_6_2020_streak.leetcode.random;

public class Search2DMatrix {

    public static void main(String[] args) {
        Search2DMatrix s = new Search2DMatrix();
        int[][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,50}};
        System.out.println(s.searchMatrix(matrix, 3));
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length == 0) {
            return false;
        }
        int rows = matrix.length;
        int columns = matrix[0].length;
        int left = 0;
        int right = rows * columns - 1;
        while(left <= right) {
            int mid = left + (right- left) / 2;
            int midR = mid / columns;
            int midC = mid % columns;
            if(matrix[midR][midC] == target) {
                return true;
            } else if(matrix[midR][midC] > target) {
                right = mid - 1;
            } else if(matrix[midR][midC] < target) {
                left = mid + 1;
            }
        }
        return false;
    }

}
