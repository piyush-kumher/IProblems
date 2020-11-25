package com.piyush.psds.aug_6_2020_streak.leetcode.trie;


public class WordDictionary {

    TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public WordDictionary() {
        root = new TrieNode();
    }

    public static void main(String[] args) {
        WordDictionary obj = new WordDictionary();
        obj.addWord("bad");
        obj.addWord("dad");
        obj.addWord("mad");
        obj.search("bad");
        obj.search(".ad");
        obj.search("b..");
    }

    /**
     * Adds a word into the data structure.
     */
    public void addWord(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (curr.children[ch - 'a'] == null) {
                curr.children[ch - 'a'] = new TrieNode();
            }
            curr = curr.children[ch - 'a'];
        }
        curr.end = true;
    }

    /**
     * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
     */
    public boolean search(String word) {
        return search(word, root, 0);
    }

    private boolean search(String word, TrieNode curr, int index) {
        if (index == word.length() && curr.end) {
            return true;
        } else if (word.charAt(index) == '.') {
            for (int i = 0; i < curr.children.length; i++) {
                if (curr.children[i] != null) {
                    if (search(word, curr.children[i], index + 1)) {
                        return true;
                    }
                }
            }
        } else if (curr.children[word.charAt(index) - 'a'] != null) {
            return search(word, curr.children[word.charAt(index) - 'a'], index + 1);
        }
        return false;
    }

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean end;
    }
}
