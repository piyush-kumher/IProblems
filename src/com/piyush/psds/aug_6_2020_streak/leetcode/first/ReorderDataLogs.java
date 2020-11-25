package com.piyush.psds.aug_6_2020_streak.leetcode.first;

import java.util.Arrays;

public class ReorderDataLogs {

    public static void main(String[] args) {
        ReorderDataLogs l = new ReorderDataLogs();
        String[] logs = {"dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero"};
        System.out.println(Arrays.toString(l.reorderLogFiles(logs)));
    }

    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (l1, l2) -> {
            String[] l1Split = l1.split(" ", 2);
            String[] l2Split = l2.split(" ", 2);
            final boolean isL1DigitLogs = Character.isDigit(l1Split[1].charAt(0));
            final boolean isL2DigitLogs = Character.isDigit(l2Split[1].charAt(0));
            if (!isL1DigitLogs && !isL2DigitLogs) {
                int cmp = l1Split[1].compareTo(l2Split[1]);
                if(cmp != 0) {
                    return cmp;
                }
                return l1.compareTo(l2);
            }
            return isL1DigitLogs ? (isL2DigitLogs ? 0 : 1) : -1;
        });
        return logs;
    }

}
