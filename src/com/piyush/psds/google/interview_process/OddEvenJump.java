package com.piyush.psds.google.interview_process;

import java.util.TreeMap;

//https://leetcode.com/problems/odd-even-jump/submissions/
public class OddEvenJump {

    public int oddEvenJumps(int[] A) {
        int length = A.length;
        if(length == 0) {
            return 0;
        }
        // so that we can get the ceiling and flooring for an element.
        TreeMap<Integer, Integer> map = new TreeMap<>();
        // tells if higher element jump is possible
        boolean[] higher = new boolean[length];
        // tells if lower element jump is possible
        boolean[] lower = new boolean[length];
        // even jump from the element
        higher[length-1] = true;
        // odd jump from the element
        lower[length-1] = true;
        map.put(A[length-1], length-1);

        for(int i=length-2; i >= 0; i--) {
            // if element is same, it's the right candidate for both odd and lower jump
            if(map.containsKey(A[i])) {
                higher[i] = lower[map.get(A[i])];
                lower[i] = higher[map.get(A[i])];
            } else {
                Integer higherElement = map.higherKey(A[i]);
                Integer lowerElement = map.lowerKey(A[i]);
                if(higherElement != null) {
                    // Looking for odd jump: check if even jump possible from next higher element .
                    lower[i] = higher[map.get(higherElement)];
                }
                if(lowerElement != null) {
                    // Looking for even jump: check if odd jump possible from next higher element .
                    higher[i] = lower[map.get(lowerElement)];
                }
            }
            map.put(A[i], i);
        }
        int ans = 0;
        // check which index reaches to the end if started with an odd jump.
        for(boolean b : lower) {
            if(b) {
                ans++;
            }
        }
        return ans;
    }

}
