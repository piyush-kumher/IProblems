package com.piyush.psds.google.recursion;

import java.util.*;

public class StrobogrammaticNumber2 {

    public List<String> findStrobogrammatic(int n) {
        return recurse(n, n);
    }

    private List<String> recurse(int m, int n) {
        if(m == 0) {
            return Arrays.asList("");
        }
        if(m == 1) {
            return Arrays.asList("0", "1", "8");
        }
        List<String> ir = recurse(m-2, n);
        List<String> result = new ArrayList<>();
        for(String s : ir) {
            if(n != m) {
                result.add('0' + s + '0');
            }
            result.add('1' + s + '1');
            result.add('6' + s + '9');
            result.add('8' + s + '8');
            result.add('9' + s + '6');
        }
        return result;
    }

}
