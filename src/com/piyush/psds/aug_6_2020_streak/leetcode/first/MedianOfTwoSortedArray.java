package com.piyush.psds.aug_6_2020_streak.leetcode.first;

public class MedianOfTwoSortedArray {

    public static void main(String[] args) {
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        MedianOfTwoSortedArray m = new MedianOfTwoSortedArray();
        System.out.println(m.findMedianSortedArrays(nums1, nums2));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        int l1 = nums1.length;
        int l2 = nums2.length;
        int mid = (l1 + l2 + 1) / 2;
        int min1 = 0;
        int max1 = l1;
        while (min1 <= max1) {
            int p1 = ((min1 + max1) / 2);
            int p2 = mid - p1;
            if (p1 < max1 && nums1[p1] < nums2[p2 - 1]) {
                min1 = p1 + 1;
            } else if (p1 > min1 && nums1[p1 - 1] > nums2[p2]) {
                max1 = p1 - 1;
            } else {
                int maxLeft = 0;
                if (p1 == 0) maxLeft = nums2[p2 - 1];
                else if (p2 == 0) maxLeft = nums1[p1 - 1];
                else maxLeft = Math.max(nums2[p2 - 1], nums1[p1 - 1]);
                if ((l1 + l2) % 2 == 1) return maxLeft;

                int maxRight = 0;
                if(p1 == l1) maxRight = nums2[p2];
                else if(p2 == l2) maxRight = nums1[p1];
                else maxRight = Math.min(nums2[p2], nums1[p1]);

                return (1.0 * maxLeft + maxRight) / 2;
            }
        }
        return  0.0;
    }

}
