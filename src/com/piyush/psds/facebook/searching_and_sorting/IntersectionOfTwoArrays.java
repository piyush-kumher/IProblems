package com.piyush.psds.facebook.searching_and_sorting;

import java.util.HashSet;
import java.util.Set;

public class IntersectionOfTwoArrays {

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> mem = new HashSet<>();
        for(int i=0; i < nums1.length; i++) {
            mem.add(nums1[i]);
        }
        Set<Integer> ans = new HashSet<>();
        for(int i=0; i < nums2.length; i++) {
            if(mem.contains(nums2[i])) {
                ans.add(nums2[i]);
            }
        }
        int[] res = new int[ans.size()];
        int id = 0;
        for(int i : ans) {
            res[id++] = i;
        }
        return res;
    }

}
