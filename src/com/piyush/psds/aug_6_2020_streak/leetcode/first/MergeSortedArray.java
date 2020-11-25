package com.piyush.psds.aug_6_2020_streak.leetcode.first;

public class MergeSortedArray {

    public static void main(String[] args) {
        MergeSortedArray m = new MergeSortedArray();
        int[] nums1 = {0};
        int[] nums2 = {1};
        m.merge(nums1, 0, nums2, 1);
        System.out.println(nums1);
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        while (i >= 0 && j >= 0) {
            if (nums2[j] >= nums1[i]) {
                nums1[k--] = nums2[j--];
            } else {
                nums1[k--] = nums1[i--];
            }
        }
        while(i >= 0){
            nums1[k--]=nums1[i--];
        }
        while(j >= 0){
            nums1[k--]=nums2[j--];
        }
    }

}
