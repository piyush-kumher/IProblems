package com.piyush.psds.facebook.searching_and_sorting;

import java.util.*;

public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
        if(intervals.length == 0) {
            return new int[0][0];
        }
        List<int[]> ans = new ArrayList<>();
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        int[] curr = intervals[0];
        for(int i=1; i < intervals.length; i++) {
            if(intervals[i][0] >= curr[0] && intervals[i][0] <= curr[1]) {
                curr[1] = Math.max(curr[1], intervals[i][1]);
            } else {
                ans.add(new int[] {curr[0], curr[1]});
                curr[0] = intervals[i][0];
                curr[1] = intervals[i][1];
            }
        }
        ans.add(new int[] {curr[0], curr[1]});
        return ans.toArray(new int[ans.size()][]);
    }


    public int[][] merge1(int[][] intervals) {
        if(intervals.length == 0) {
            return new int[0][0];
        }
        List<int[]> ans = new ArrayList<>();
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        for(int[] interval : intervals) {
            if(ans.size() == 0 || ans.get(ans.size()-1)[1] < interval[0]) {
                ans.add(interval);
            } else {
                ans.get(ans.size()-1)[1] = Math.max(ans.get(ans.size()-1)[1], interval[1]);
            }
        }
        return ans.toArray(new int[ans.size()][]);
    }

}
