package com.piyush.psds.aug_6_2020_streak.graph;

import java.util.*;

public class Test {

    public static void main(String[] args) {
        Test t= new Test();
        int[][]  i = {{13,15},{1,13}};
        System.out.println(t.minMeetingRooms(i));
    }

    public int minMeetingRooms(int[][] intervals) {
        if(intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));
        PriorityQueue<Integer> q = new PriorityQueue<>(Comparator.comparingInt(a -> a));
        q.add(intervals[0][1]);
        for(int i=1; i < intervals.length; i++) {
            if(q.peek() < intervals[i][0]) {
                q.poll();
            }
            q.add(intervals[i][1]);
        }
        return q.size();
    }

}
