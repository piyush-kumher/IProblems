package com.piyush.psds.aug_6_2020_streak.leetcode.random;

import java.util.LinkedList;
import java.util.Queue;

public class HitCounter {

    Queue<Integer> queue;

    /**
     * Initialize your data structure here.
     */
    public HitCounter() {
        queue = new LinkedList<>();
    }

    /**
     * Record a hit.
     *
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public void hit(int timestamp) {
        queue.add(timestamp);
    }

    /**
     * Return the number of hits in the past 5 minutes.
     *
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public int getHits(int timestamp) {
        while (!queue.isEmpty() && queue.peek() <= timestamp - 300) {
            queue.remove();
        }
        return queue.size();
    }

}
