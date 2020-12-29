package com.piyush.psds.facebook.array_and_string;

import java.util.ArrayList;
import java.util.List;

public class ReverseWordsInAString {

    public String reverseWords(String s) {
        int i = 0;
        int j = 0;
        List<String> words = new ArrayList<>();
        while(i <= j && j < s.length()) {
            if(i == j && s.charAt(i) == ' ' && s.charAt(j) == ' ') {
                i++; j++;
            } else {
                while(j < s.length() && s.charAt(j) != ' ') {
                    j++;
                }
                words.add(s.substring(i, j));
                i = j;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int k=words.size()-1; k >=0 ;k--) {
            sb.append(words.get(k));
            if(k != 0) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }


    public String reverseWords_1(String s) {
        int j = 0;
        List<String> words = new ArrayList<>();
        StringBuilder b = new StringBuilder();
        while(j < s.length()) {
            if(s.charAt(j) == ' ' && b.length() > 0) {
                words.add(b.toString());
                b = new StringBuilder();
            } else if(s.charAt(j) != ' ') {
                b.append(s.charAt(j));
            }
            j++;
        }
        if(b.length() > 0) {
            words.add(b.toString());
        }
        StringBuilder sb = new StringBuilder();
        for(int k=words.size()-1; k >=0 ;k--) {
            sb.append(words.get(k));
            if(k != 0) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

}
