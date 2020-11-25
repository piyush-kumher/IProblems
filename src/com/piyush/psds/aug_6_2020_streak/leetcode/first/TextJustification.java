package com.piyush.psds.aug_6_2020_streak.leetcode.first;

import java.util.ArrayList;
import java.util.List;

public class TextJustification {

    public static void main(String[] args) {
        String[] arr = {"Science","is","what","we","understand","well","enough",
                "to","explain","to","a","computer.","Art","is","everything","else","we","do"};
        TextJustification t = new TextJustification();
        System.out.println(t.fullJustify(arr, 20));
    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            int start = i;
            int sum = words[i].length();
            while (i + 1 < words.length) {
                if (sum + words[i + 1].length() + 1 <= maxWidth) {
                    sum = sum + words[i + 1].length() + 1;
                    i++;
                } else {
                    break;
                }
            }
            int extraSpaces = maxWidth - sum;
            int noOfStrings = i - start + 1;
            if (noOfStrings == 1) {
                list.add(words[i] + new String(new char[extraSpaces]).replace("\0", " "));
            } else {
                int eqDivided = extraSpaces / (noOfStrings - 1);
                int extra = extraSpaces % (noOfStrings - 1);
                StringBuilder sb = new StringBuilder();
                if(i == words.length-1) {
                    int k = start;
                    for (; k < i; k++) {
                        sb.append(words[k]).append(" ");
                    }
                    sb.append(words[k]).append(new String(new char[extraSpaces]).replace("\0", " "));
                } else {
                    int k = start;
                    for (; k < i; k++) {
                        int spacesToAdd = eqDivided + 1 + (--extra >= 0 ? 1 : 0);
                        sb.append(words[k]).append(new String(new char[spacesToAdd]).replace("\0", " "));
                    }
                    sb.append(words[k]);
                }
                list.add(sb.toString());
            }
        }
        return list;
    }

    public List<String> fullJustify_not_better(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int i=0;
        while(i < words.length) {
            int count = 0;
            int j = i;
            while(j < words.length && (count + words[j].length() + j - i) <= maxWidth) {
                count += words[j++].length();
            }
            int gaps = j - i - 1;
            int exactSpaces = gaps == 0 ? 0 : (maxWidth - count) / gaps;
            int extraSpaces = gaps == 0 ? 0 : (maxWidth - count) % gaps;
            String temp = words[i];
            for(int k=i+1; k < j; k++) {
                if(j < words.length) {
                    int totalSpaces = exactSpaces;
                    if(extraSpaces > 0) {
                        totalSpaces++;
                        extraSpaces--;
                    }
                    temp = temp + new String(new char[totalSpaces]).replace("\0", " ") + words[k];
                } else {
                    temp = temp + " " + words[k];
                }
            }
            result.add(temp + new String(new char[maxWidth - temp.length()]).replace("\0", " "));
            i = j;
        }
        return result;
    }

}
