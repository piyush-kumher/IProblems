package com.piyush.psds.google.recursion;

import java.util.ArrayList;
import java.util.List;

public class GenerateParanthesis {

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<String>();
        recurse(n, ans, 0, 0, "");
        return ans;
    }

    private void recurse(int max, List<String> result, int open, int closed, String curr) {
        if(curr.length() == max * 2) {
            result.add(curr);
            return;
        }
        if(open < max) {
            recurse(max, result, open + 1, closed, curr + '(');
        }
        if(closed < open) {
            recurse(max, result, open, closed + 1, curr + ')');
        }
    }

}
