package com.piyush.psds.google.recursion;

import java.util.*;

public class WordSearch2 {

    TrieNode root;
    List<String> result = new ArrayList<>();

    class TrieNode {
        Map<Character, TrieNode> map = new HashMap<>();
        String word = null;
        public TrieNode() {
        }
    }

    public List<String> findWords(char[][] board, String[] words) {
        buildTrie(words);
        for(int i=0; i < board.length; i++) {
            for(int j=0; j < board[0].length; j++) {
                if(root.map.containsKey(board[i][j])) {
                    recurse(board, i, j, root);
                }
            }
        }
        return result;
    }

    private void recurse(char[][] board, int row, int col, TrieNode parent) {
        char letter = board[row][col];
        TrieNode currNode = parent.map.get(letter);
        if(currNode.word != null) {
            result.add(currNode.word);
            currNode.word = null;
        }
        board[row][col] = '#';
        int[] rowOffset = {-1, 0, 1, 0};
        int[] colOffset = {0, 1, 0, -1};
        for(int i=0; i < 4; i++) {
            int newRow = row + rowOffset[i];
            int newCol = col + colOffset[i];
            if(newRow < 0 || newCol < 0 || newRow >= board.length || newCol >= board[0].length) {
                continue;
            }
            if(currNode.map.containsKey(board[newRow][newCol])) {
                recurse(board, newRow, newCol, currNode);
            }
        }
        board[row][col] = letter;
        // Optimization: incrementally remove the leaf nodes
        if (currNode.map.isEmpty()) {
            parent.map.remove(letter);
        }
    }

    private void buildTrie(String[] words) {
        root = new TrieNode();
        for(String word : words) {
            TrieNode node = root;
            for(char c : word.toCharArray()) {
                if(!node.map.containsKey(c)) {
                    node.map.put(c, new TrieNode());
                }
                node = node.map.get(c);
            }
            node.word = word;
        }
    }

}
