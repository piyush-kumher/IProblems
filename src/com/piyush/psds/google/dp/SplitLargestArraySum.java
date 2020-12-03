package com.piyush.psds.google.dp;

import java.util.HashMap;
import java.util.Map;

public class SplitLargestArraySum {

    public static void main(String[] args) {
        SplitLargestArraySum s = new SplitLargestArraySum();
        int[] arr = {7,2,5,10,8};
        int m =3;
        System.out.println(s.splitArray(arr, m));
    }


    // f(i, j) = max(f(k, j-1), nums[k+1]+...+nums[i])
    // f(i, j) denotes : Min largest sum of subarray [0, i] with j splits ..
    // 7, 2, 5, 10, 8  -- m = 3
    // n/m  0  1  2  3
    //      0  m  m  m
    //      m  7  m  m
    //      m  9  7  m
    //      m  14 7  7
    //      m  24 14 10
    //      m  32 18 14
    public int splitArray_2(int[] nums, int m) {
        int n = nums.length;
        int[] sum = new int[n+1];
        int[][] mem = new int[n+1][m+1];

        // sum[i] denotes the sum of array [0, i-1] ..
        for(int i=0; i < n; i++) {
            sum[i+1] = sum[i] + nums[i];
        }

        for(int i=0; i <= n; i++) {
            for(int j=0; j <= m; j++) {
                mem[i][j] = Integer.MAX_VALUE;
            }
        }
        mem[0][0] = 0;

        for(int i=1; i <= n; i++) {
            for(int j=1; j <= m; j++) {
                // i=2, j=1 : k = 0 (mem[2][1] = Math.min(mem[2][1] -> {m}, Math.max(mem[0][0] -> {0}, 9-0))),
                // k = 1 (mem[2][1] = Math.min(mem[2][1] -> {9}, Math.max(mem[1][0] -> {m}, 9-2)))
                for(int k=0; k < i; k++) {
                    mem[i][j] = Math.min(mem[i][j], Math.max(mem[k][j-1], sum[i] - sum[k]));
                }
            }
        }
        return mem[n][m];
    }




    // best way ..

    /**
     * Set the search range between min=(largest single value) and max=(sum of all values).
     * The min starts there because we're looking for the sum of the largest group in the final set of groups. And no matter what groups you create, the largest value has to be in it, so the largest group can't be smaller than that. (This assumes no negative numbers.)
     *
     * Calculate the midpoint between min and max. This midpoint is the group size we're going to try out to see how well it performs.
     *
     * Split the nums list into groups such that no group has a value larger than the chosen midpoint.
     * Note that we may end up with too many or too few groups. That's fine.
     *
     * Compare the number of groups we created against the target m. If we created too many groups, we know the final answer must be between mid+1 and max. That's because we need fewer groups and the way to achieve fewer groups is to increase the allowed maximum sum in each group.
     *
     * On the other hand, if the number of groups is too small, we know the final answer is between min and mid-1 because we need to increase the number of groups which means the target sum is something smaller than the one we used. This is actually also a possible answer assuming m is valid because you can always take any group and split it up to make more groups, so the mid value you targeted is at worst, higher than the real value.
     *
     * On the third hand, if the number of groups is just right, we have a possible answer, so remember that answer. However, we should keep searching just in case there is a better answer. We're ultimately looking for smaller maximum sums, so the potentially better answer is between min and mid-1.
     *
     * Repeat the process until there is nothing else to search. Then use the minimum value we found during the above process.
     */
    public int splitArray(int[] nums, int m) {
        long max = 0; long sum = 0;
        for(int i=0; i < nums.length; i++) {
            sum += nums[i];
            max = Math.max(max, nums[i]);
        }

        long l=max; long r = sum;
        long ans = r;

        while(l <= r) {
            long mid = (l+r)/2;
            if(valid(nums, m, mid)) {
                ans = Math.min(mid, ans);
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return (int)ans;
    }

    private boolean valid(int[] nums, int m, long target) {
        long count = 1;
        long sum = 0;
        for(int i=0; i < nums.length; i++) {
            sum = sum + nums[i];
            if(sum > target) {
                sum = nums[i];
                count++;
                if(count > m) {
                    return false;
                }
            }
        }
        return true;
    }







    // results into time limit exceeding
    public int splitArray1(int[] nums, int m) {
        Map<String, Integer> mem = new HashMap<>();
        int[] sums = new int[nums.length+1];
        for(int i=1; i <= nums.length; i++) {
            sums[i] = nums[i-1] + sums[i-1];
        }
        return minLongestSum(nums, sums, mem, 0, nums.length-1, m);
    }

    // nums = 1,4,4
    // sums = 0,1,5,9, m=3
    // start = 0, end = 2
    // 2-2+1=1
    private int minLongestSum(int[] nums, int[] sums, Map<String, Integer> mem, int start, int end, int m) {
        if(m == 1) {
            return sums[end+1] - sums[start];
        }
        int min = Integer.MAX_VALUE;
        for(int i=start; i < end - m + 2; i++) {
            int first = sums[i+1] - sums[start];
            String key = (i+1) + "-" + end + "-" + (m-1);
            int res = 0;
            if(mem.containsKey(key)) {
                res = mem.get(key);
            } else {
                res = minLongestSum(nums, sums, mem, i+1, end, m-1);
            }
            min = Math.min(min, Math.max(first, res));
        }
        String key = start + "-" + end + "-" + m;
        mem.put(key, min);
        return min;
    }

}
