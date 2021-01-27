package com.piyush.psds.google.others;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class HandOfStraight {

    public boolean isNStraightHand2(int[] hand, int W) {
        int n = hand.length;
        if(W == 0 || n == 0 || n % W != 0) {
            return false;
        }
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int i : hand) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        while(map.size() > 0) {
            int first = map.firstKey();
            for(int i=first; i < first + W; i++) {
                if(!map.containsKey(i)) {
                    return false;
                }
                if(map.get(i) == 1) {
                    map.remove(i);
                } else {
                    map.put(i, map.get(i)-1);
                }
            }
        }
        return true;
    }

    public boolean isNStraightHand1(int[] hand, int W) {
        Map<Integer, Integer> c = new TreeMap<>();
        for (int i : hand) c.put(i, c.getOrDefault(i, 0)+1);
        for (int it : c.keySet())
            if (c.get(it) > 0)
                for (int i = W - 1; i >= 0; --i) {
                    if (c.getOrDefault(it + i, 0) < c.get(it)) return false;
                    c.put(it + i, c.get(it + i) - c.get(it));
                }
        return true;
    }

    public boolean isNStraightHand(int[] hand, int W) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int i : hand){
            minHeap.add(i);
        }
        while(minHeap.size() != 0) {
            int start = minHeap.poll();
            for(int j = 1; j < W; j++){
                if(minHeap.remove(start + j)) {
                    continue;
                }
                else {
                    return false;
                }
            }
        }
        return true;
    }
}
