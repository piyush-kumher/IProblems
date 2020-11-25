package com.piyush.psds.aug_6_2020_streak.leetcode.random;

import java.util.PriorityQueue;

public class MedianFinder {
    
    PriorityQueue<Integer> upperHalf;
    PriorityQueue<Integer> lowHalf;

    /** initialize your data structure here. */
    public MedianFinder() {
        lowHalf = new PriorityQueue<>((a,b) -> b-a);
        upperHalf = new PriorityQueue<>((a,b) -> a-b);
    }
    
    public void addNum(int num) {
        lowHalf.offer(num);
        upperHalf.offer(lowHalf.peek());
        lowHalf.remove();
        if(lowHalf.size() < upperHalf.size()) {
            lowHalf.offer(upperHalf.peek());
            upperHalf.remove();
        }
        
    }
    
    public double findMedian() {
        return lowHalf.size() > upperHalf.size() ? lowHalf.peek() : ((double) lowHalf.peek() + upperHalf.peek()) * 0.5;
    }
}