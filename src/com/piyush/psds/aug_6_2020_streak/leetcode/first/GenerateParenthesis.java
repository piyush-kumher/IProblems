package com.piyush.psds.aug_6_2020_streak.leetcode.first;

import java.util.*;

public class GenerateParenthesis {

    public static void main(String[] args) {
        GenerateParenthesis p = new GenerateParenthesis();
        List<String> l = p.generateParenthesis(3);
        l.forEach(System.out::println);
    }

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        backTrack(ans, "", 0, 0, n);
        return ans;
    }

    private void backTrack(List<String> ans, String s, int opened, int closed, int max) {
        if (s.length() == 2 * max) {
            ans.add(s);
            return;
        }
        if (opened < max) {
            backTrack(ans, s + "(", opened + 1, closed, max);
        }
        if (closed < opened) {
            backTrack(ans, s + ")", opened, closed + 1, max);
        }
    }

    public List<String> generateParenthesis_1(int n) {
        List<String> ans = new ArrayList<>();
        if (n == 0) {
            ans.add("");
        } else {
            for (int c = 0; c < n; c++) {
                for (String left : generateParenthesis(c)) {
                    for (String right : generateParenthesis(n - 1 - c)) {
                        ans.add("(" + left + ")" + right);
                    }
                }
            }
        }
        return ans;
    }

    public List<String> generateParenthesis_2(int n) {
        List<List<String>> lists = new ArrayList<>();
        lists.add(Collections.singletonList(""));
        for (int i = 1; i <= n; ++i) {
            final List<String> list = new ArrayList<>();

            for (int j = 0; j < i; ++j) {
                for (final String first : lists.get(j)) {
                    for (final String second : lists.get(i - 1 - j)) {
                        list.add("(" + first + ")" + second);
                    }
                }
            }
            lists.add(list);
        }
        return lists.get(lists.size() - 1);
    }

}
