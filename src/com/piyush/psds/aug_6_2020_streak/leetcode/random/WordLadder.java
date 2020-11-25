package com.piyush.psds.aug_6_2020_streak.leetcode.random;

import java.util.*;

public class WordLadder {

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        WordLadder wl = new WordLadder();
        List<String> l = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        System.out.println(wl.ladderLength(beginWord, endWord, l));
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Map<String, List<String>> transfs = new HashMap<>();
        for(String word: wordList) {
            for(int i=0; i < word.length(); i++) {
                String newWord = word.substring(0, i) + '*' + word.substring(i+1);
                transfs.putIfAbsent(newWord, new ArrayList<>());
                transfs.get(newWord).add(word);
            }
        }
        Set<String> visited = new HashSet<>();
        Queue<Pair> q = new LinkedList<>();
        visited.add(beginWord);
        q.add(new Pair(beginWord, 1));
        while(!q.isEmpty()) {
            Pair p = q.remove();
            for(int i=0; i < p.word.length(); i++) {
                String newWord = p.word.substring(0, i) + '*' + p.word.substring(i+1);
                if(transfs.containsKey(newWord)) {
                    List<String> words = transfs.get(newWord);
                    for(String word : words) {
                        if(word.equals(endWord)) {
                            return 1+p.dist;
                        }
                        if(!visited.contains(word)) {
                            visited.add(word);
                            q.add(new Pair(word, 1+p.dist));
                        }
                    }
                }
            }
        }
        return 0;
    }


    class Pair {
        String word;
        int dist;
        public Pair(String word, int dist) {
            this.word = word;
            this.dist = dist;
        }
    }

}
