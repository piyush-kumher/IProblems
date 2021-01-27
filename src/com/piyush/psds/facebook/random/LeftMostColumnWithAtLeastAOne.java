package com.piyush.psds.facebook.random;


import java.util.ArrayList;
import java.util.List;

class BinaryMatrix {

    int[][] matrix;

    public BinaryMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public int get(int r, int c) {
        return matrix[r][c];
    }

    public List<Integer> dimensions() {
        List<Integer> arr = new ArrayList<>();
        arr.add(matrix.length);
        arr.add(matrix[0].length);
        return arr;
    }

}

// https://leetcode.com/problems/leftmost-column-with-at-least-a-one/
public class LeftMostColumnWithAtLeastAOne {

    public static void main(String[] args) {
        LeftMostColumnWithAtLeastAOne l = new LeftMostColumnWithAtLeastAOne();
        int[][] arr = {{0,0}, {0,1}};
        BinaryMatrix bm = new BinaryMatrix(arr);
        System.out.println(l.leftMostColumnWithOne(bm));
    }

    // O(m+n) where 0 <= m,n <= 100, we have to call binaryMatrix.get(r,c) at most 100 times. This algo will call (200) times
    public int leftMostColumnWithOne_1(BinaryMatrix binaryMatrix) {
        List<Integer> dimensions = binaryMatrix.dimensions();
        int rows = dimensions.get(0);
        int cols = dimensions.get(1);
        int ans = cols;
        int r = 0;
        int c = cols-1;
        while(r < rows && c >= 0) {
            if(binaryMatrix.get(r, c) == 1) {
                ans = Math.min(ans, c);
                c--;
            } else {
                r++;
            }
        }
        return ans == cols ? -1 : ans;
    }

    // O(mlogn)  -> 1000, can also be improved by reducing the search space ..
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dimensions = binaryMatrix.dimensions();
        int rows = dimensions.get(0);
        int cols = dimensions.get(1);
        int ans = cols;
        for(int i=0; i < rows; i++) {
            int left = 0;
            int right = cols-1;
            while(left <= right) {
                int mid = left + (right-left) / 2;
                int val = binaryMatrix.get(i, mid);
                if(val == 1) {
                    ans = Math.min(mid, ans);
                    right = mid-1;
                } else {
                    left = mid+1;
                }
            }
        }
        return ans == cols ? -1 : ans;
    }

}
