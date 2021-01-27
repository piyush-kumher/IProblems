package com.piyush.psds.google.array_string;

import java.util.Arrays;

public class FindAndReplaceSubstring {

    public static void main(String[] args) {
        FindAndReplaceSubstring  f = new FindAndReplaceSubstring();
        String S = "abcd";
        int[] indexes = {0, 2};
        String[] sources = {"a", "cd"};
        String[] targets = {"eee", "ffff"};
        System.out.println(f.findReplaceString(S, indexes, sources, targets));
    }

    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        int[] arr = new int[S.length()];
        Arrays.fill(arr, -1);
        for(int i=0; i < indexes.length; i++) {
            int ix = indexes[i];
            if(S.substring(ix, ix + sources[i].length()).equals(sources[i])) {
                arr[ix] = i;
            }
        }
        StringBuilder sb = new StringBuilder();
        int ix = 0;
        while(ix < S.length()) {
            if(arr[ix] >= 0) {
                sb.append(targets[arr[ix]]);
                ix += sources[arr[ix]].length();
            } else {
                sb.append(S.charAt(ix++));
            }
        }
        return sb.toString();
    }

}
