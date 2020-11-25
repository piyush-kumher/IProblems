package com.piyush.psds.aug_6_2020_streak.leetcode.first;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/partition-labels/
 */
public class PartitionLabels {

    public static void main(String[] args) {
        PartitionLabels l = new PartitionLabels();
        String s = "ababcbacadefegdehijhklij";
        System.out.println(l.partitionLabels(s));
    }

    public List<Integer> partitionLabels(String S) {
        int[] last = new int[26];
        for (int i = 0; i < S.length(); i++) {
            last[S.charAt(i) - 'a'] = i;
        }
        int j = 0;
        int anchor = 0;
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < S.length(); i++) {
            j = Math.max(j, last[S.charAt(i) - 'a']);
            if (i == j) {
                l.add(i - anchor + 1);
                anchor = i + 1;
            }
        }
        return l;
    }


}
