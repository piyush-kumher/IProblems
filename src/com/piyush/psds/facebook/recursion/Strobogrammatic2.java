package com.piyush.psds.facebook.recursion;

import java.util.ArrayList;
import java.util.List;

public class Strobogrammatic2 {

    public static void main(String[] args) {
        Strobogrammatic2 s = new Strobogrammatic2();
        System.out.println(s.findStrobogrammatic(2));
    }

    public List<String> findStrobogrammatic(int n) {
        if(n == 0) {
            return new ArrayList<>();
        }
        List<String> ans = new ArrayList<>();
        boolean odd = n % 2 == 1;
        if(odd) {
            doIt(n, 1, "0", ans);
            doIt(n, 1, "1", ans);
            doIt(n, 1, "8", ans);
        } else {
            doIt(n, 0, "", ans);
        }
        return ans;
    }

    private void doIt(int n, int size, String s, List<String> ans) {
        if(n == size) {
            ans.add(s);
            return;
        }
        if(size + 2 != n) {
            doIt(n, size+2, "0" + s + "0", ans);
        }
        doIt(n, size+2, "1" + s + "1", ans);
        doIt(n, size+2, "6" + s + "9", ans);
        doIt(n, size+2, "9" + s + "6", ans);
        doIt(n, size+2, "8" + s + "8", ans);
    }
}
