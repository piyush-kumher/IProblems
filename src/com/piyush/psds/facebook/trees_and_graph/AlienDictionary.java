package com.piyush.psds.facebook.trees_and_graph;

import java.util.*;

public class AlienDictionary {


    public String alienOrder(String[] words) {
        if(words.length == 0) {
            return "";
        }
        Map<Character, Set<Character>> g = new HashMap<>();
        Map<Character, Integer> indegree = new HashMap<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                indegree.put(c, 0);
            }
        }
        for(int i=0; i < words.length-1; i++) {
            String w1 = words[i];
            String w2 = words[i+1];
            if (w1.length() > w2.length() && w1.startsWith(w2)) {
                return "";
            }
            int m=0;
            while(m < w1.length() && m < w2.length()) {
                char cur = w1.charAt(m);
                char next = w2.charAt(m);
                if(cur != next) {
                    g.putIfAbsent(cur, new HashSet<>());
                    Set<Character> set  = g.get(cur);
                    if(!set.contains(next)) {
                        set.add(next);
                        indegree.put(next, indegree.get(next) + 1);
                    }
                    break;
                }
                m++;
            }
        }
        Queue<Character> q = new LinkedList<>();
        for(Map.Entry<Character, Integer> e : indegree.entrySet()) {
            if(e.getValue() == 0) {
                q.offer(e.getKey());
            }
        }
        StringBuilder res = new StringBuilder();
        while(!q.isEmpty()) {
            char ch = q.poll();
            res.append(ch);
            if(g.containsKey(ch)) {
                for(char next : g.get(ch)) {
                    indegree.put(next, indegree.get(next) - 1);
                    if(indegree.get(next) == 0) {
                        q.offer(next);
                    }
                }
            }
        }
        if(res.length() != indegree.size()) {
            return "";
        }
        return res.toString();
    }

    public String alienOrder_1(String[] words) {
        if(words.length == 0) {
            return "";
        }
        Graph g = new Graph();
        for(int i=0; i < words.length-1; i++) {
            String w1 = words[i];
            String w2 = words[i+1];
            if (w1.length() > w2.length() && w1.startsWith(w2)) {
                return "";
            }
            int m=0;
            while(m < w1.length() && m < w2.length()) {
                if(w1.charAt(m) != w2.charAt(m)) {
                    g.addEdge(w1.charAt(m), w2.charAt(m));
                    break;
                }
                m++;
            }
        }
        Map<Character, Integer> visited = new HashMap<>();
        for(String word : words) {
            for(char c : word.toCharArray()) {
                visited.put(c, 0);
            }
        }
        Stack<Character> stk = new Stack<>();
        for(char c : visited.keySet()) {
            if(visited.get(c) == 0 && !topoSort(g, c, stk, visited)) {
                return "";
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!stk.isEmpty()) {
            sb.append(stk.pop());
        }
        return sb.toString();
    }

    private boolean topoSort(Graph g, char c, Stack<Character> s, Map<Character, Integer> visited) {
        visited.put(c, 1);
        if(g.al.containsKey(c)) {
            for(char next : g.al.get(c)) {
                if(visited.get(next) == 0) {
                    if(!topoSort(g, next, s, visited)) {
                        return false;
                    }
                } else if(visited.get(next) == 1) {
                    return false;
                }
            }
        }
        visited.put(c, 2);
        s.push(c);
        return true;
    }

    class Graph {
        Map<Character, List<Character>> al = new HashMap<>();
        public void addEdge(char ch1, char ch2) {
            al.putIfAbsent(ch1, new ArrayList<>());
            al.get(ch1).add(ch2);
        }
    }
}
