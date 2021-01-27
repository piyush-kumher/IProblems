package com.piyush.psds.facebook.random;

import java.util.*;

public class AlienDictionary {

    public static void main(String[] args) {
        AlienDictionary a = new AlienDictionary();
        //String[] words = {"wrt","wrf","er","ett","rftt"};
        String[] words = {"zxfa"};
        System.out.println(a.alienOrder(words));
    }

    public String alienOrder(String[] words) {
        Graph g = new Graph();
        for(int i=0; i < words.length-1; i++) {
            String w1 = words[i];
            String w2 = words[i+1];
            int j=0;
            for(; j < Math.min(w1.length(), w2.length()); j++) {
                char c1 = w1.charAt(j);
                char c2 = w2.charAt(j);
                if(c1 != c2) {
                    g.addEdge(c1, c2);
                    break;
                }
            }
            if(j == Math.min(w1.length(), w2.length()) && w1.length() > w2.length()) {
                return "";
            }
        }
        Stack<Character> stk = new Stack<>();
        int[] visited = new int[26];
        for(String word : words) {
            for(char c : word.toCharArray()) {
                if(visited[c-'a'] == 0) {
                    if(!dfs(g, c, stk, visited)) {
                        return "";
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!stk.isEmpty()) {
            sb.append(stk.pop());
        }
        return sb.toString();
    }

    private boolean dfs(Graph g, char c, Stack<Character> stk, int[] visited) {
        visited[c-'a'] = 1;
        if(g.map.containsKey(c)) {
            for(char next : g.map.get(c)) {
                if(visited[next-'a'] == 0) {
                    if(!dfs(g, next, stk, visited)) {
                        return false;
                    }
                } else if(visited[next-'a'] == 1) {
                    return false;
                }
            }
        }
        visited[c-'a'] = 2;
        stk.push(c);
        return true;
    }

    class Graph {
        Map<Character, List<Character>> map = new HashMap<>();
        public void addEdge(char c1, char c2) {
            map.putIfAbsent(c1, new ArrayList<>());
            map.get(c1).add(c2);
        }
    }
}
