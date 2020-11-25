package com.piyush.psds.aug_6_2020_streak.leetcode.first;

import java.util.*;

/**
 * https://leetcode.com/problems/meeting-rooms-ii
 * <p>
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
 * <p>
 * Example 1:
 * <p>
 * Input: [[0, 30],[5, 10],[15, 20]]
 * Output: 2
 * <p>
 * Example 2:
 * <p>
 * Input: [[7,10],[2,4]]
 * Output: 1
 * <p>
 * NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */
public class MeetingRoom2 {

    public static void main(String[] args) {
        MeetingRoom2 m = new MeetingRoom2();
        int[][] intervals = {
                {0, 30},
                {5, 10},
                {2, 3}
        };
        System.out.println(m.minMeetingRooms(intervals));
        System.out.println(Arrays.deepToString(intervals));
    }

    public int minMeetingRooms_1(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));
        PriorityQueue<Integer> p = new PriorityQueue<>(intervals.length, Comparator.comparingInt(a -> a));
        for (int[] interval : intervals) {
            if (!p.isEmpty()) {
                final int peek = p.peek();
                if (peek <= interval[0]) {
                    p.poll();
                }
            }
            p.add(interval[1]);
        }
        return p.size();
    }

    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int start = 0;
        int end = 0;
        int usedRooms = 0;
        while (start < intervals.length) {
            if (starts[start] >= ends[end]) {
                usedRooms--;
                end++;
            }
            usedRooms++;
            start++;
        }
        return usedRooms;
    }
}
