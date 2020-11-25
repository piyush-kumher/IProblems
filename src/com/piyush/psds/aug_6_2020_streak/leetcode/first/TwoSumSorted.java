package com.piyush.psds.aug_6_2020_streak.leetcode.first;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 */
public class TwoSumSorted {

    public static void main(String[] args) {
        int[] numbers = {2, 7, 11, 15};
        TwoSumSorted tss = new TwoSumSorted();
        System.out.println(Arrays.toString(tss.twoSum(numbers, 9)));
    }

    public int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;
        int[] result = new int[2];
        while (i < j) {
            if (numbers[i] + numbers[j] == target) {
                result[0] = i + 1;
                result[1] = j + 1;
                break;
            } else if (numbers[i] + numbers[j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return result;
    }

}
