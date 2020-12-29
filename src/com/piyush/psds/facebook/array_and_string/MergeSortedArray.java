package com.piyush.psds.facebook.array_and_string;

public class MergeSortedArray {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int actualLength = m+n;
        int curPos = actualLength-1;
        int i = m-1;
        int j = n-1;
        while(j >= 0) {
            if(i >= 0 && nums1[i] > nums2[j]) {
                nums1[curPos--] = nums1[i--];
            } else {
                nums1[curPos--] = nums2[j--];
            }
        }
    }

}
