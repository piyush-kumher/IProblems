package com.piyush.psds.facebook.design;

import java.util.HashMap;
import java.util.Map;

public class AddAndSearchWord {


    TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public AddAndSearchWord() {
        root = new TrieNode();
    }

    /**
     * Adds a word into the data structure.
     */
    public void addWord(String word) {
        TrieNode curr = root;
        for (char ch : word.toCharArray()) {
            if (!curr.children.containsKey(ch)) {
                curr.children.put(ch, new TrieNode());
            }
            curr = curr.children.get(ch);
        }
        curr.endOfWord = true;
    }

    /**
     * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
     */
    public boolean search(String word) {
        return search(word, root);
    }

    private boolean search(String word, TrieNode curr) {
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!curr.children.containsKey(ch)) {
                if (ch == '.') {
                    for (char c : curr.children.keySet()) {
                        if (search(word.substring(i + 1), curr.children.get(c))) {
                            return true;
                        }
                    }
                }
                return false;
            }
            curr = curr.children.get(ch);
        }
        return curr.endOfWord;
    }

    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean endOfWord;
    }
}
