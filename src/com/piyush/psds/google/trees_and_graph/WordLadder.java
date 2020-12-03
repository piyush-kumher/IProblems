package com.piyush.psds.google.trees_and_graph;

import java.util.*;

public class WordLadder {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

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



}
