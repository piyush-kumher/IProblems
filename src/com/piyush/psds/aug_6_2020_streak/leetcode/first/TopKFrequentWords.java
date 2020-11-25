package com.piyush.psds.aug_6_2020_streak.leetcode.first;

import java.util.*;

/**
 * https://leetcode.com/problems/top-k-frequent-words/solution/
 * //TODO: divide and conquer
 */
public class TopKFrequentWords {

    public static void main(String[] args) {
        TopKFrequentWords w = new TopKFrequentWords();
        String[] ar = {"i", "love", "leetcode", "i", "love", "coding"};
        System.out.println(w.topKFrequent(ar, 2));
    }

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> count = new HashMap<>();
        for(String word : words) {
            count.put(word, count.containsKey(word) ? count.get(word) + 1 : 1);
        }
        List<String> keys = new ArrayList<>(count.keySet());
        Collections.sort(keys, (k1, k2) -> count.get(k1).equals(count.get(k2)) ? k1.compareTo(k2) : count.get(k2) - count.get(k1));
        return keys.subList(0, k);
    }

    public List<String> topKFrequent_1(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<String> heap = new PriorityQueue<>((w1, w2) ->
                map.get(w1).equals(map.get(w2)) ? w2.compareTo(w1) : map.get(w1) - map.get(w2));
        for (String word : map.keySet()) {
            heap.offer(word);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        List<String> ans = new ArrayList<>();
        while (!heap.isEmpty()) {
            ans.add(heap.poll());
        }
        Collections.reverse(ans);
        return ans;
    }

}
