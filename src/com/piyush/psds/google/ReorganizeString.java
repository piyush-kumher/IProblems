package com.piyush.psds.google;

import java.util.*;

/**
 * https://leetcode.com/problems/reorganize-string/
 */
public class ReorganizeString {

    // time complexity ( O(N log A)) : N length of the string, A is the size of alphabets.
    public String reorganizeString(String S) {
        int n = S.length();
        Map<Character, Integer> map = new HashMap<>();
        PriorityQueue<Character> queue = new PriorityQueue<>((a, b) -> map.get(b) - map.get(a));
        StringBuilder resultBuilder = new StringBuilder();

        // get the count of all characters
        for (int i = 0; i < n; i++) {
            map.put(S.charAt(i), map.getOrDefault(S.charAt(i), 0) + 1);
        }

        // if a single character is more than half od the string length, then it is not possible, return empty.
        // Add all characters to a priority queue (sorted based on the count of their occurrence)
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() > (n + 1) / 2) {
                return "";
            }
            queue.offer(entry.getKey());
        }
        while (queue.size() > 1) {
            // Get first two different characters and place them one after the other.
            Character c1 = queue.poll();
            Character c2 = queue.poll();
            resultBuilder.append(c1);
            resultBuilder.append(c2);
            map.put(c1, map.get(c1) - 1);
            map.put(c2, map.get(c2) - 1);
            if (map.get(c1) > 0) {
                queue.add(c1);
            }
            if (map.get(c2) > 0) {
                queue.add(c2);
            }
        }
        if (!queue.isEmpty()) {
            resultBuilder.append(queue.poll());
        }
        return resultBuilder.toString();
    }
}
