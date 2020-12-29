package com.piyush.psds.facebook.others;

public class VerifyingAlienDictionary {

    public boolean isAlienSorted(String[] words, String order) {
        int[] arr = new int[26];
        for (int i = 0; i < order.length(); i++) {
            arr[order.charAt(i) - 'a'] = i;
        }
        for (int i = 0; i < words.length - 1; i++) {
            int j = 0;
            String word1 = words[i];
            String word2 = words[i + 1];
            for (; j < Math.min(word1.length(), word2.length()); j++) {
                if (word1.charAt(j) != word2.charAt(j)) {
                    if (arr[word1.charAt(j) - 'a'] > arr[word2.charAt(j) - 'a']) {
                        return false;
                    }
                    break;
                }
            }
            if (j == Math.min(word1.length(), word2.length()) && word1.length() > word2.length()) {
                return false;
            }
        }
        return true;
    }

}
