package com.piyush.psds.gfg;

import java.util.Scanner;

/**
 * https://www.geeksforgeeks.org/maximum-difference-between-two-elements/
 */
public final class MaximumDiffBetweenTwoElements {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int size = s.nextInt();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = s.nextInt();
        }
        System.out.println(method1(arr));
        System.out.println(method2(arr));
    }

    private static int method1(int[] arr) {
        int arrSize = arr.length;
        int maxDiff = arr[1] - arr[0];
        int minNum = arr[0];
        for (int i = 1; i < arrSize; i++) {
            if (arr[i] - minNum > maxDiff) {
                maxDiff = arr[i] - minNum;
            }
            if (minNum > arr[i]) {
                minNum = arr[i];
            }
        }
        return maxDiff;
    }

    private static int method2(int[] arr) {
        int[] diff = new int[arr.length - 1];
        for(int i=1; i < arr.length; i++) {
            diff[i-1] = arr[i] - arr[i-1];
        }
        return MaximumSubArraySum.sum(diff);
    }

}
