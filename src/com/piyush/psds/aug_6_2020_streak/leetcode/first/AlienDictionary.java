package com.piyush.psds.aug_6_2020_streak.leetcode.first;

import java.util.*;

public class AlienDictionary {

    public static void main(String[] args) {
        AlienDictionary a = new AlienDictionary();
        String[] words = {
                "abc",
                "ab"
        };
        System.out.println(a.alienOrder(words));
    }

    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            if (word1.length() > word2.length() && word1.startsWith(word2)) {
                return "";
            }
            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                if (word1.charAt(j) != word2.charAt(j)) {
                    graph.putIfAbsent(word1.charAt(j), new HashSet<>());
                    graph.get(word1.charAt(j)).add(word2.charAt(j));
                    break;
                }
            }
        }
        Stack<Character> stc = new Stack<>();
        Map<Character, Integer> visited = new HashMap<>();
        for (String word : words) {
            for (char i : word.toCharArray()) {
                visited.put(i, 0);
            }
        }
        for (char key : visited.keySet()) {
            if (visited.get(key) == 0 && !topologicalSortUtil(graph, key, visited, stc)) {
                return "";
            }
        }
        StringBuilder sb = new StringBuilder("");
        while (!stc.isEmpty()) {
            sb.append(stc.pop());
        }
        return sb.toString();
    }

    private boolean topologicalSortUtil(Map<Character, Set<Character>> graph, Character key,
                                        Map<Character, Integer> visited, Stack<Character> stc) {
        visited.put(key, 1);
        if (graph.containsKey(key)) {
            for (Character next : graph.get(key)) {
                if (visited.get(next) == 0) {
                    if (!topologicalSortUtil(graph, next, visited, stc)) {
                        return false;
                    }
                } else if (visited.get(next) == 1) {
                    return false;
                }
            }
        }
        visited.put(key, 2);
        stc.push(key);
        return true;
    }

}