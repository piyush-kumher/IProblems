package com.piyush.psds.google;

import java.util.*;

public class Test {

    public static void main(String[] args) {
        Test t = new Test();
        int[] s = {-1, 0, 1, 2, -1, -4};
        System.out.println(t.threeSum(s));
    }


    static Map<String, Set<String>> map = new HashMap<>();

    static {
        map.put("+", new HashSet<>(Arrays.asList("*", "/")));
        map.put("-", new HashSet<>(Arrays.asList("*", "/")));
        map.put("*", new HashSet<>(Arrays.asList("/")));
        map.put("/", new HashSet<>(Arrays.asList("")));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Set<Integer> dups = new HashSet<>();
        Set<List<Integer>> list = new HashSet<>();
        Map<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int complement = -nums[i] - nums[j];
                if (seen.containsKey(complement) && seen.get(complement) == i) {
                    List<Integer> l = Arrays.asList(nums[i], nums[j], complement);
                    Collections.sort(l);
                    list.add(l);
                }
                seen.put(nums[j], i);
            }
        }
        return new ArrayList<>(list);
    }
}

