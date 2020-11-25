package com.piyush.psds.aug_6_2020_streak.leetcode.first;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.ThreadLocalRandom;

public class KLargestElement {

    public static void main(String[] args) {
        //int[] arr = {5, 3, 6, 1, 8, 23, 7, 2};
        int[] arr = {3, 2, 1, 5, 6, 4};
        KLargestElement k = new KLargestElement();
        System.out.println(k.findKthLargest(arr, 2));
    }

    public int findKthLargest_1(int[] nums, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>(Comparator.comparingInt(n -> n));
        for (int i = 0; i < nums.length; i++) {
            q.add(nums[i]);
            if (q.size() > k) {
                q.poll();
            }
        }
        return q.poll();
    }

    public int findKthLargest(int[] nums, int k) {
        int l = 0;
        int r = nums.length - 1;
        return quickSelect(nums, l, r, nums.length - k);
    }

    private int quickSelect(int[] nums, int l, int r, int k) {
        if (l == r) {
            return nums[l];
        }
        int pivot = ThreadLocalRandom.current().nextInt(l, r + 1);
        pivot = partition(nums, l, r, pivot);
        if (pivot == k) {
            return nums[pivot];
        } else if (pivot > k) {
            return quickSelect(nums, l, pivot - 1, k);
        } else {
            return quickSelect(nums, pivot + 1, r, k);
        }
    }

    private int partition(int[] nums, int l, int r, int pivot) {
        int pivotVal = nums[pivot];
        swap(nums, pivot, r);
        int pivotPos = l;
        for (int i = l; i <= r; i++) {
            if (nums[i] < pivotVal) {
                if (pivotPos != i) {
                    swap(nums, i, pivotPos);
                }
                pivotPos++;
            }
        }
        swap(nums, pivotPos, r);
        return pivotPos;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
