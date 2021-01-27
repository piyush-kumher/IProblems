package com.piyush.psds.google.others;

import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowMaximum {

    public int[] maxSlidingWindow_1(int[] nums, int k) {
        // first solution O(NK).. time limit exceeds
        int n = nums.length;
        if(n * k == 0) {
            return new int[0];
        }
        int[] result = new int[n-k+1];
        for(int i=0; i < n-k+1; i++) {
            int max = Integer.MIN_VALUE;
            for(int j=i; j < i+k; j++) {
                max = Math.max(max, nums[j]);
            }
            result[i] = max;
        }
        return result;
    }

    // using Deque : O(N)
    public int[] maxSlidingWindow_2(int[] nums, int k) {
        int n = nums.length;
        if(n * k == 0) {
            return new int[0];
        }
        int[] result = new int[n-k+1];
        Deque<Integer> q = new ArrayDeque<>();
        int max = Integer.MIN_VALUE;
        for(int i=0; i < k; i++) {
            clearDeque(nums, q, i, k);
            q.addLast(i);
            max = Math.max(max, nums[i]);
        }
        result[0] = max;
        for(int i=k; i < n; i++) {
            clearDeque(nums, q, i, k);
            q.addLast(i);
            result[i-k+1] = nums[q.getFirst()];
        }
        return result;
    }

    //Deque contains the elements in decreasing order for the window
    private void clearDeque(int[] nums, Deque<Integer> q, int i, int k) {
        // remove any element out of the sliding window
        if(!q.isEmpty() && q.getFirst() == i-k) {
            q.removeFirst();
        }
        // keep removing recently added element until you encouter a bigger element than the current being operated in sliding window.
        while(!q.isEmpty() && nums[q.getLast()] <= nums[i]) {
            q.removeLast();
        }
    }

    // using dp : O(N)
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if(n * k == 0) {
            return new int[0];
        }
        int[] result = new int[n-k+1];
        // idea is to break the array into multiple blocks of k elements.. last block could have lesser element [1,3,-1,3,5,3,6,7], k=3
        // blocks : [  1,3,-1,     3,5,3,     6,7]
        // an array: left[i] which contains the max element from start of the block till i in the block which it falls in. ----->
        // left :   [  1,3, 3,     3,5,5,     6,7]
        int[] left = new int[n];
        for(int i=0; i < n; i++) {
            if(i % k == 0) {
                left[i] = nums[i];
            } else {
                left[i] = Math.max(left[i-1], nums[i]);
            }
        }
        // an array: right[j] which contains the max element from end of the block till i in the block which it falls in. <-----
        // right :  [  3,3,-1,     5,5,3,     7,7]
        int[] right = new int[n];
        for(int i=n-1; i >= 0; i--) {
            if((i+1) % k == 0 || i == n-1) {
                right[i] = nums[i];
            } else {
                right[i] = Math.max(right[i+1], nums[i]);
            }
        }
        // answer would be for a window of i, j = Math.max(right[i], left[j])
        // right[i] would tell, what is the maximum towards right in the block where i lies.
        // left[i] would tell, what is the maximum towards left in the black where j lies.
        for(int i=0; i < n-k+1; i++) {
            result[i] = Math.max(right[i], left[i+k-1]);
        }
        return result;
    }

}
