package com.piyush.psds.aug_6_2020_streak.dp;

import java.util.*;

// https://leetcode.com/problems/concatenated-words/
public class ConcatenatedWords {

    public static void main(String[] args) {
        ConcatenatedWords cw = new ConcatenatedWords();
        String[] arr = {"a","aa","aaa","aaaa"};
        System.out.println(cw.findAllConcatenatedWordsInADict(arr));
    }

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(String::length));
        List<String> result = new ArrayList<>();
        Set<String> dict = new HashSet<>();
        for (int i = 0; i < Math.min(2, words.length); i++) {
            dict.add(words[i]);
        }
        for (int i = 2; i < words.length; i++) {
            if (checkIfWordBreakPossibleDP(words[i], dict)) {
                result.add(words[i]);
            }
            dict.add(words[i]);
        }
        return result;
    }

    private boolean checkIfWordBreakPossibleDP(String word, Set<String> dict) {
        boolean[] mem = new boolean[word.length()+1];
        mem[0] = true;
        for(int i=1; i <= word.length(); i++) {
            for(int j=0; j < i; j++) {
                if(mem[j] && dict.contains(word.substring(j, i))) {
                    mem[i] = true;
                    break;
                }
            }
        }
        return mem[word.length()];
    }


    Node root = new Node();

    public List<String> findAllConcatenatedWordsInADict_TRIE_DFS(String[] words) {
        List<String> result = new ArrayList<>();
        populateRootNode(words);
        for(String word : words) {
            if(checkCombination(word, 0, 0)) {
                result.add(word);
            }
        }
        return result;
    }

    private void populateRootNode(String[] words) {
        Node current = null;
        for(String word : words) {
            if(word.isEmpty()) {
                continue;
            }
            current = root;
            for(int i=0; i < word.length(); i++) {
                int ele = word.charAt(i) - 'a';
                if(current.exists(ele)) {
                    current = current.get(ele);
                } else {
                    current = current.add(ele);
                }
            }
            current.wordEnd = true;
        }
    }

    private boolean checkCombination(String word, int start, int count) {
        Node current = root;
        for(int i=start; i < word.length(); i++) {
            int ele = word.charAt(i) - 'a';
            if(current.exists(ele)) {
                if(current.get(ele).wordEnd) {
                    if(i == word.length() -1 && count + 1 >= 2) {
                        return true;
                    }
                    if (checkCombination(word, i + 1, count + 1)) {
                        return true;
                    }
                }
                current = current.get(ele);
            } else {
                return false;
            }
        }
        return false;
    }

    class Node {
        Node[] children = new Node[26];
        boolean wordEnd;

        public Node() {
        }

        public Node add(int c) {
            Node n = new Node();
            children[c] = n;
            return n;
        }

        public boolean exists(int c) {
            return children[c] != null;
        }

        public Node get(int c) {
            return children[c];
        }
    }

}
