package com.piyush.psds.google.recursion;

import java.util.*;

// https://leetcode.com/problems/word-squares/submissions/
public class WordSquares {

    Map<String, List<String>> prefixMap = new HashMap<>();

    public List<List<String>> wordSquares(String[] words) {
        int n = words[0].length();
        List<List<String>> results = new ArrayList<>();
        buildPrefixMap(words, n);
        for(String word : words) {
            LinkedList<String> list = new LinkedList<>();
            list.add(word);
            backTrack(1, list, results, n);
        }
        return results;
    }

    private void backTrack(int step, LinkedList<String> list, List<List<String>> results, int n) {
        if(list.size() == n) {
            results.add(new ArrayList<>(list));
            return;
        }
        StringBuilder sb = new StringBuilder();
        for(String word: list) {
            sb.append(word.charAt(step));
        }
        String prefix = sb.toString();
        for(String word : prefixMap.getOrDefault(prefix, new ArrayList<>())) {
            list.add(word);
            backTrack(step+1, list, results, n);
            list.removeLast();
        }
    }

    private void buildPrefixMap(String[] words, int n) {
        for(String word : words) {
            for(int i=1; i < n; i++) {
                String prefix = word.substring(0, i);
                prefixMap.putIfAbsent(prefix, new ArrayList<>());
                prefixMap.get(prefix).add(word);
            }
        }
    }

}
