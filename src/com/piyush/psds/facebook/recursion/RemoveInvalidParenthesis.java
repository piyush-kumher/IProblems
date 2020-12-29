package com.piyush.psds.facebook.recursion;

import java.util.*;

public class RemoveInvalidParenthesis {

    public static void main(String[] args) {
        RemoveInvalidParenthesis r = new RemoveInvalidParenthesis();
        String s = "()())()";
        System.out.println(r.removeInvalidParentheses(s));
    }

    Set<String> set = new HashSet<>();
    public List<String> removeInvalidParentheses(String s) {
        // addition left bracket count and additiona right bracker count
        int alc = 0; int arc = 0;
        for(int i=0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                alc++;
            } else if(s.charAt(i) == ')') {
                arc = (alc == 0) ? arc+1 : arc;
                alc = (alc > 0) ? alc-1 : alc;
            }
        }
        this.recurse(s, 0, 0, 0, alc, arc, new StringBuilder());
        return new ArrayList<>(set);
    }

    private void recurse(String s, int i, int lc, int rc, int alc, int arc, StringBuilder sb) {
        if(i == s.length()) {
            if(alc == 0 && arc == 0) {
                set.add(sb.toString());
            }
        } else {
            int length = sb.length();
            char c = s.charAt(i);

            if((c == '(' && alc > 0) || (c == ')' && arc > 0)) {
                recurse(s, i+1, lc, rc,
                        (c=='(' ? alc-1 : alc),
                        (c==')' ? arc-1 : arc),
                        sb);
            }

            sb.append(c);
            if(c != '(' && c != ')') {
                recurse(s, i+1, lc, rc, alc, arc, sb);
            } else if(c == '(') {
                recurse(s, i+1, lc+1, rc, alc, arc, sb);
            } else if(rc < lc) {
                recurse(s, i+1, lc, rc+1, alc, arc, sb);
            }
            sb.deleteCharAt(length);
        }
    }
}
