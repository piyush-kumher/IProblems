package com.piyush.psds.google.trees_and_graph;

import java.util.*;

public class WordLadder {

    public static void main(String[] args) {
        WordLadder w = new WordLadder();
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
        System.out.println(w.ladderLength(beginWord, endWord, wordList));
    }

    public int ladderLength1(String beginWord, String endWord, List<String> wordList) {

        if(beginWord.equals(endWord)) {
            return 0;
        }
        Map<String, List<String>> mem = new HashMap<>();
        for(String word : wordList) {
            for(int i=0; i < word.length(); i++) {
                String newWord = word.substring(0, i) + '*' + word.substring(i+1);
                mem.putIfAbsent(newWord, new ArrayList<>());
                mem.get(newWord).add(word);
            }
        }

        Queue<Node> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        q.add(new Node(beginWord, 1));

        while(!q.isEmpty()) {
            Node n = q.remove();
            String word = n.word;
            int level  = n.level;

            for(int i=0; i < word.length(); i++) {
                String newWord = word.substring(0, i) + '*' + word.substring(i+1);
                for(String dict : mem.getOrDefault(newWord, new ArrayList<>())) {
                    if(dict.equals(endWord)) {
                        return level + 1;
                    }
                    if(!visited.contains(dict)) {
                        visited.add(dict);
                        q.add(new Node(dict, level + 1));
                    }
                }
            }
        }
        return 0;
    }


    class Node {
        String word;
        int level;
        public Node(String word, int level) {
            this.word = word;
            this.level = level;
        }
    }


    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Map<String, List<String>> map = new HashMap<>();
        for(String word: wordList) {
            for(int i=0; i < word.length(); i++) {
                String newWord = word.substring(0, i) + "*" + word.substring(i+1);
                map.putIfAbsent(newWord, new ArrayList<>());
                map.get(newWord).add(word);
            }
        }
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        q.add(beginWord);
        visited.add(beginWord);
        int ans = 1;
        while(!q.isEmpty()) {
            int size = q.size();
            for(int j=0; j < size; j++) {
                String w = q.remove();
                for(int i=0; i < w.length(); i++) {
                    String newW = w.substring(0, i) + "*" + w.substring(i+1);
                    for(String transfW : map.getOrDefault(newW, new ArrayList<>())) {
                        if(endWord.equals(transfW)) {
                            return ans+1;
                        }
                        if(!visited.contains(transfW)) {
                            q.add(transfW);
                            visited.add(transfW);
                        }
                    }
                }
            }
            ans++;
        }
        return 0;
    }



}
